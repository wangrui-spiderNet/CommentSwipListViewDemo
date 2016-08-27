package cn.demo.wr.project.commentswiplistviewdemo.bean;

/**
 * Created by wangrui on 2016/8/27.
 */
public class ReplyBean {

    private String replycontent;//回复内容	string
    private long replytime;//	回复时间	number	时间戳
    private int replytype;//回复对象类型	number	1.我自己的 0.其他人（家长）
    private int replyuserid;//	回复者的id	number
    private String replyusername;//回复者的名字	string
    private boolean isvisible;

    public boolean isvisible() {
        return isvisible;
    }

    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

    public long getReplytime() {
        return replytime;
    }

    public void setReplytime(long replytime) {
        this.replytime = replytime;
    }

    public int getReplytype() {
        return replytype;
    }

    public void setReplytype(int replytype) {
        this.replytype = replytype;
    }

    public int getReplyuserid() {
        return replyuserid;
    }

    public void setReplyuserid(int replyuserid) {
        this.replyuserid = replyuserid;
    }

    public String getReplyusername() {
        return replyusername;
    }

    public void setReplyusername(String replyusername) {
        this.replyusername = replyusername;
    }
}
