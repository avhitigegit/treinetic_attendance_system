package com.attend.demo.dto;

import java.io.Serializable;

public class RoleDto implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    private String id;
    private String roleType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
