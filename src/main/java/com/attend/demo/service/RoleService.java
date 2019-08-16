package com.attend.demo.service;

import com.attend.demo.dto.RoleDto;
import com.attend.demo.model.Role;
import com.attend.demo.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    //Generate A New Role
    public Role createRole(RoleDto roleDto) {
        Role role = new Role();
        try {
            if (roleDto != null) {
                BeanUtils.copyProperties(roleDto, role);
                role = roleRepository.save(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    // Retrieve All Roles
    public List<Role> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

    //Find the role using role id
    public Role findRoleById(String roleId) {
        Role role = roleRepository.findRoleByID(roleId);
        return role;
    }
}
