package com.attend.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "leave")
public class Leave implements Serializable {
    private static final long serialVersionUID = 4865903039190150223L;

    @Id
    private String id;
    private Date date;
    private Date createdAt;
    private String status;
    private LeaveType leaveTypeId;
    private Employee employeeId;
    private Employee approvedEmpId;

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

    public LeaveType getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(LeaveType leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getApprovedEmpId() {
        return approvedEmpId;
    }

    public void setApprovedEmpId(Employee approvedEmpId) {
        this.approvedEmpId = approvedEmpId;
    }
}
