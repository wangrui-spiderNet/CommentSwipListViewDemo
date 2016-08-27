package cn.demo.wr.project.commentswiplistviewdemo.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.demo.wr.project.commentswiplistviewdemo.R;
import cn.demo.wr.project.commentswiplistviewdemo.bean.CommentBean;
import cn.demo.wr.project.commentswiplistviewdemo.bean.ReplyBean;
import cn.demo.wr.project.commentswiplistviewdemo.bean.emoji;


/**
 * Created by wangrui on 2016/7/27.
 */
public class ReplyAdapter extends BaseAdapter {
    private CommentBean commentBean;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private OnSetItemHeightListener itemHeightListener;
    private int itemHeight;

    public ReplyAdapter(Context context, CommentBean commentBean, OnSetItemHeightListener itemHeightListener) {
        this.mContext = context;
        this.commentBean = commentBean;
        this.itemHeightListener = itemHeightListener;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if(commentBean.getReplylist().size()>5&&!commentBean.isOpen()){
            return 6;
        }
        return commentBean.getReplylist().size();
    }

    @Override
    public Object getItem(int position) {
        return commentBean.getReplylist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_reply, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(commentBean.getReplylist().get(position).getReplytype()==1){
            viewHolder.reply_target.setText("我");
        }else{
            viewHolder.reply_target.setText(commentBean.getReplylist().get(position).getReplyusername());
        }

//        viewHolder.reply_content.setText(commentBean.getReplylist().get(position).getReplycontent());
        showTextContent(commentBean.getReplylist().get(position).getReplycontent(), viewHolder.reply_content);
        setItemVisibility(commentBean.getReplylist(),position,viewHolder);

        return convertView;
    }

    private void showTextContent(String content, TextView textView){
        // 判断接受到的是否有表情图片，有则替换
        if (content!=null && content.contains("<f") && content.contains(">")) {
            textView.setText("");
            String text = content;
            text =text.replace("[音频]"," ");
            text = text.replace("[图片]"," ");
            String message = text;
            List<Object> results = new ArrayList<Object>();
            List<String> ems = new ArrayList<String>();
            Pattern patter = Pattern.compile("<f[\\w]*>");
            Matcher matcher = patter.matcher(content);
            while (matcher.find()) {
                ems.add(matcher.group());
            }
            for (int i = 0; i < ems.size(); i++) {
                if (message.startsWith("<f")) {
                    results.add(message.substring(0, 6));
                    message = message.substring(6, message.length());
                    if (message.length() > 0 && !message.startsWith("<f")) {
                        if (message.contains("<f") && message.contains(">")) {
                            int emsIndex = message.indexOf("<");
                            String itemMes = message.substring(0, emsIndex);
                            results.add(itemMes);
                            message = message.substring(emsIndex,message.length());
                        } else {
                            results.add(message);
                        }
                    }
                } else {
                    int emsIndex = message.indexOf("<");
                    String itemMes = message.substring(0, emsIndex);
                    results.add(itemMes);
                    message = message.substring(emsIndex, message.length());
                    results.add(message.substring(0, 6));
                    message = message.substring(6, message.length());
                }
            }
            ArrayList<SpannableString> list = new ArrayList<SpannableString>();
            for (int i = 0; i < results.size(); i++) {
                list.add(null);
            }
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).toString().startsWith("<f")) {
                    String emPath = results.get(i).toString().replace("<", "");
                    emPath = emPath.replace(">", "");
                    emPath = emPath.substring(1, 4);
                    list.set(i, emoji.getImg(mContext, emPath));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    results.set(i, list.get(i));
                }
            }
            for (int i = 0; i < results.size(); i++) {
                textView.append((CharSequence) results.get(i));
            }
            int count = content.length();
            String sub = content.substring(count-1, count);
            if(sub.equalsIgnoreCase(">")){
                textView.append(" ");
            }

        } else {
            String text = content;
            if(text != null)
            {
                text =text.replace("[音频]"," ");
                text = text.replace("[图片]"," ");
                textView.setText(text);
            }
        }

    }

    private void setItemVisibility(final ArrayList<ReplyBean> replyBeens, final int position , final ViewHolder viewHolder){

        if(position==5&&!commentBean.isOpen()){

            viewHolder.reply_target.setText("加载更多");
            viewHolder.reply_content.setVisibility(View.GONE);
            viewHolder.reply_target.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentBean.setOpen(true);
                    itemHeight=measureHeight(viewHolder);
                    itemHeightListener.setHeight(itemHeight,replyBeens.size());
                    viewHolder.reply_target.setText(replyBeens.get(position).getReplyusername());
                    notifyDataSetChanged();
                }
            });

        }else {
            itemHeight=measureHeight(viewHolder);
            viewHolder.reply_content.setVisibility(View.VISIBLE);
            itemHeightListener.setHeight(itemHeight,replyBeens.size());
        }
    }

    /**
     * 获取item高度
     */

    private int measureHeight(final ViewHolder viewHolder){
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        viewHolder.reply_layout.measure(w, h);
        int height = viewHolder.reply_layout.getMeasuredHeight();

        return height;
    }

    class ViewHolder {
        LinearLayout reply_layout;
        TextView reply_target;
        TextView reply_content;

        ViewHolder(View view) {
            reply_layout = (LinearLayout) view.findViewById(R.id.reply_layout);
            reply_target = (TextView) view.findViewById(R.id.reply_target);
            reply_content = (TextView) view.findViewById(R.id.reply_content);
        }
    }

    public interface OnSetItemHeightListener {
        void setHeight(int itemheight, int size);
    }


}
