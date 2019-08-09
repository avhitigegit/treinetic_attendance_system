package com.attend.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 4865903039190150223L;

    @Id
    private String id;
    private String roleType;

    public Role(String id, String roleType) {
        this.id = id;
        this.roleType = roleType;
    }

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
