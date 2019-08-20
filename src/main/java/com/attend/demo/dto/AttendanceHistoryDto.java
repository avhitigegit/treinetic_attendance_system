package com.attend.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class AttendanceHistoryDto implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    private Date from;
    private Date to;
    private String Status;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
