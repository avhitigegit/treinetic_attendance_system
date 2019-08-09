package com.attend.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class LeaveDto implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    private String id;
    private Date date;
    private Date createdAt;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
