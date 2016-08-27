package cn.demo.wr.project.commentswiplistviewdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import cn.demo.wr.project.commentswiplistviewdemo.adapter.CommentListAdapter;
import cn.demo.wr.project.commentswiplistviewdemo.bean.CommentPageBean;

public class MainActivity extends Activity implements CommentListAdapter.OnReplyRequestListener {

    private ListView listView;
    private CommentPageBean commentPageBean;
    private AlertDialog dialog;
    private CommentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listView);
        setData();
    }

    private void setData() {
        commentPageBean=new Gson().fromJson(ConstantSet.testData,CommentPageBean.class);
        mAdapter = new CommentListAdapter(this, commentPageBean.getCommentlist(),  this);
        listView.setAdapter(mAdapter);

    }

    /**
     * 赠送鲜花
     *
     * @param position
     * @param flowercount
     */
    public void giveFlower(int position, int flowercount) {
        Toast.makeText(this,"赠送鲜花"+flowercount+"朵给:"+commentPageBean.getCommentlist().get(position).getStudentname()+"号人物" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendReply(int position, String content) {
        Toast.makeText(this,"发送回复:"+content , Toast.LENGTH_SHORT).show();
    }
}
