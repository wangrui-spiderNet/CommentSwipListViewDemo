package cn.demo.wr.project.commentswiplistviewdemo.bean;

import java.io.Serializable;

/**
 * Created by wangrui on 2016/8/27.
 */


public class BaseResponse implements Serializable {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 474893052025693952L;
    /**
     * 整形，操作指令
     */
    protected String operationTime;
    protected int cmd;
    protected int state;  //1正常
    protected String msg;
    protected String dt;

    public BaseResponse() {
    }

    /*public void setBaseId(int baseId){
        this.baseId = baseId;
    }
    public int getBaseId(){
        return baseId;
    }*/
    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

