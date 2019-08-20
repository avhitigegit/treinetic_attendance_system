package com.attend.demo.controller;

import com.attend.demo.dto.RoleDto;
import com.attend.demo.model.Role;
import com.attend.demo.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // Create A New Role
    @ResponseBody
    @RequestMapping(value = "/create-new-role", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        role = roleService.createRole(roleDto);
        return ResponseEntity.ok(role);
    }

    // Retrieve All Roles
    @ResponseBody
    @RequestMapping(value = "/all-roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleService.getAllRoles();
        return ResponseEntity.ok(roleList);
    }
}
