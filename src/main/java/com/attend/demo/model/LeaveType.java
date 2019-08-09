package com.attend.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "leaveType")
public class LeaveType implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    @Id
    private String id;
    private String leaveType;

    public LeaveType(String id, String leaveType) {
        this.id = id;
        this.leaveType = leaveType;
    }

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
