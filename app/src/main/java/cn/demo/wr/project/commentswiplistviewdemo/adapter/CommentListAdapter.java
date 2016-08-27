package cn.demo.wr.project.commentswiplistviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.demo.wr.project.commentswiplistviewdemo.MainActivity;
import cn.demo.wr.project.commentswiplistviewdemo.R;
import cn.demo.wr.project.commentswiplistviewdemo.bean.CommentBean;
import cn.demo.wr.project.commentswiplistviewdemo.util.DateUtil;
import cn.demo.wr.project.commentswiplistviewdemo.widget.CommentCustomDialog;
import cn.demo.wr.project.commentswiplistviewdemo.widget.MyListView;


/**
 * @author wangrui
 * @ClassName CommentListAdapter
 * @date 2016年7月27日
 */
public class CommentListAdapter extends BaseAdapter implements ReplyAdapter.OnSetItemHeightListener{

    private List<CommentBean> commentBeanList;
    private Context mContext;
    private ForegroundColorSpan greenSpan;
    private CommentCustomDialog customDialog;
    private OnReplyRequestListener replyRequestListener;

    public CommentListAdapter(Context mContext, ArrayList<CommentBean> commentBeanList,
                              OnReplyRequestListener replyRequestListener) {
        this.mContext = mContext;
        this.commentBeanList = commentBeanList;
        greenSpan = new ForegroundColorSpan(Color.RED);
        this.replyRequestListener=replyRequestListener;
    }

    @Override
    public int getCount() {
        return commentBeanList.size();
    }

    @Override
    public CommentBean getItem(int item) {
        return commentBeanList.get(item);
    }

    @Override
    public long getItemId(int item) {
        return item;
    }

    public void setList(List<CommentBean> mchatTemp) {
        commentBeanList = mchatTemp;
    }

    private ViewHolder holder = null;

    @Override
    public View getView(final int position, View convertView, ViewGroup group) {

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CommentBean bean = commentBeanList.get(position);

        holder.comment_give_flower.setVisibility(View.VISIBLE);

        holder.comment_content.setText(commentBeanList.get(position).getCommentcontent());
        holder.comment_tip.setText( "在"+commentBeanList.get(position).getCommentfield()+"方面，对"+commentBeanList.get(position).getStudentname()+"评价:");
        holder.comment_date.setText(DateUtil.getMillisecondFormatDataAndTime(commentBeanList.get(position).getTime()));


        ReplyAdapter replyAdapter = new ReplyAdapter(mContext, bean, this);
        holder.comment_reply_list.setAdapter(replyAdapter);

        holder.comment_give_flower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                customDialog= CommentCustomDialog.getInstance(mContext).createDialog();
                customDialog.show("赠送鲜花数量:", new CommentCustomDialog.IOnClickListenerCallback() {
                    @Override
                    public void cancelListener() {
                        customDialog.dismiss();
                    }

                    @Override
                    public void okListener(float rate) {

                        ((MainActivity)mContext).giveFlower(position,(int)rate);
                        customDialog.dismiss();
                    }
                });
            }
        });

        return convertView;
    }


    @Override
    public void setHeight( int itemheight, int size) {

            ViewGroup.LayoutParams params = holder.comment_reply_list.getLayoutParams();

            params.height = itemheight * size
                    + (holder.comment_reply_list.getDividerHeight() * (size - 1));

            holder.comment_reply_list.setLayoutParams(params);

    }

    public class ViewHolder {

        TextView comment_tip;
        TextView comment_date;
        TextView comment_content;
        MyListView comment_reply_list;
        TextView comment_give_flower;

        ViewHolder(View view) {
            comment_tip = (TextView) view.findViewById(R.id.comment_tip);
            comment_date = (TextView) view.findViewById(R.id.comment_date);
            comment_content = (TextView) view.findViewById(R.id.comment_content);
            comment_reply_list = (MyListView) view.findViewById(R.id.comment_reply_list);
            comment_give_flower = (TextView) view.findViewById(R.id.comment_give_flower);
        }

    }

    public interface OnReplyRequestListener {
        void sendReply(int position, String content);
    }

}

