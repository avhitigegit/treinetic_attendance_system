package com.attend.demo.rabbitmq;

import java.io.Serializable;

public class CustomMessageDto implements Serializable {
    private static final long serialVersionUID = -5040129546598887262L;
    private String msg;
    private int priority;
    private boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
