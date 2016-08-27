package cn.demo.wr.project.commentswiplistviewdemo.bean;

import java.util.ArrayList;

    /**
     * Created by wangrui on 2016/8/5.
     */
    public class CommentPageBean extends BaseResponse{

        private ArrayList<CommentBean> commentlist;//评论列表	array<object>
        private ArrayList<GetFlowerBean> getflowerlist;//	老师获得鲜花记录列表	array<object>	返回最新5条
        private int getflowersumcount;//	老师获得的鲜花总数	number
        private int getheartcount;//	家长获得爱心	number
        private int remainflowercount;//	家长今天剩余的鲜花数
        private int todaygetflowercount;// 老师今天获得的鲜花数

        public ArrayList<CommentBean> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(ArrayList<CommentBean> commentlist) {
            this.commentlist = commentlist;
        }

        public ArrayList<GetFlowerBean> getGetflowerlist() {
            return getflowerlist;
        }

        public void setGetflowerlist(ArrayList<GetFlowerBean> getflowerlist) {
            this.getflowerlist = getflowerlist;
        }

        public int getGetflowersumcount() {
            return getflowersumcount;
        }

        public void setGetflowersumcount(int getflowersumcount) {
            this.getflowersumcount = getflowersumcount;
        }

        public int getGetheartcount() {
            return getheartcount;
        }

        public void setGetheartcount(int getheartcount) {
            this.getheartcount = getheartcount;
        }

        public int getRemainflowercount() {
            return remainflowercount;
        }

        public void setRemainflowercount(int remainflowercount) {
            this.remainflowercount = remainflowercount;
        }

        public int getTodaygetflowercount() {
            return todaygetflowercount;
        }

        public void setTodaygetflowercount(int todaygetflowercount) {
            this.todaygetflowercount = todaygetflowercount;
        }
    }

