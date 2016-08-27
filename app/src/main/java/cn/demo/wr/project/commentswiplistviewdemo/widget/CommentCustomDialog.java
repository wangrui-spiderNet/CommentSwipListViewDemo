package cn.demo.wr.project.commentswiplistviewdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import cn.demo.wr.project.commentswiplistviewdemo.R;


/**
 * 对话框工具类
 */
public class CommentCustomDialog extends Dialog {
    public static CommentCustomDialog instance;
    private Context mContext;
    private TextView tvTitle;
    private Button btnCancle;
    private Button btnSubmit;
    private RatingBar comment_rb_flower;
    private IOnClickListenerCallback listenerCallback;
    public CommentCustomDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    public CommentCustomDialog(Context context) {
        super(context);
        mContext = context;
    }

    public static CommentCustomDialog getInstance(Context context) {
        instance = new CommentCustomDialog(context, R.style.AlertDialog);
        return instance;
    }

    public CommentCustomDialog createDialog() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_give_flower_view, null);
        instance.addContentView(layout, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tvTitle = (TextView) layout.findViewById(R.id.comment_dialog_title);
        btnCancle = (Button) layout.findViewById(R.id.comment_dialog_cancel);
        btnSubmit = (Button) layout.findViewById(R.id.comment_dialog_sure);
        comment_rb_flower = (RatingBar) layout.findViewById(R.id.comment_rb_flower);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerCallback != null) {
                    listenerCallback.cancelListener();
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerCallback != null) {
                    listenerCallback.okListener(comment_rb_flower.getRating());
                }
            }
        });
        instance.setContentView(layout);
        return  instance;
    }

    public void show(String title, String cancel, String submit, IOnClickListenerCallback listener) {
        instance.setCancelable(false);
        instance.show();
        tvTitle.setText(title);
        btnCancle.setText(cancel);
        btnSubmit.setText(submit);
        listenerCallback = listener;
    }

    public void show(String title, IOnClickListenerCallback listener) {
        instance.setCancelable(false);
        instance.show();
        tvTitle.setText(title);
        listenerCallback = listener;
    }


    public void setCanceledOnTouchOutside(boolean b) {
        instance.setCanceledOnTouchOutside(b);
    }

    public interface IOnClickListenerCallback {
        public void cancelListener();

        public void okListener(float rate);
    }


}
