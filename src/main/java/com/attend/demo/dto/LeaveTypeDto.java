package com.attend.demo.dto;

import java.io.Serializable;

public class LeaveTypeDto implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    private String id;
    private String leaveType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}
