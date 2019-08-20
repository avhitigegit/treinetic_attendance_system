package com.attend.demo.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class RequestAttendanceDto implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    private Date date;
    private LocalTime timeIn;
    private LocalTime timeOut;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }
}
