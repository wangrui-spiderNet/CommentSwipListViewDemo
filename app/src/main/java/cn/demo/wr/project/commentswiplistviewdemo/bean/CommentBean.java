package cn.demo.wr.project.commentswiplistviewdemo.bean;

import java.util.ArrayList;

/**
 * Created by wangrui on 2016/8/27.
 */
public class CommentBean {

    private String commentcontent;//评论内容	string
    private String commentfield;//评价方向	number	1.整体表现2.课堂表现3.作业表现4.成绩表现
    private String commentid;//评论id	number
    private ArrayList<ReplyBean> replylist;//回复列表 array<object>
    private String studentname;// 学生名字	string
    private long time;//	评价时间	number	时间戳
    private int teacherid;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public String getCommentfield() {
        return commentfield;
    }

    public void setCommentfield(String commentfield) {
        this.commentfield = commentfield;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public ArrayList<ReplyBean> getReplylist() {
        return replylist;
    }

    public void setReplylist(ArrayList<ReplyBean> replylist) {
        this.replylist = replylist;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}