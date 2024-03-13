package com.mealtracker.service;

import com.mealtracker.domain.Role;
import com.mealtracker.domain.enums.RoleType;
import com.mealtracker.exception.ResourceNotFoundException;
import com.mealtracker.exception.message.ErrorMessage;
import com.mealtracker.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;


    public Role findByType(RoleType roleType){

       Role role =  roleMapper.findByType(roleType.getName()).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION, roleType.name())));


       return role;
    }

    public Role findById(int roleId){

       Role role =  roleMapper.findById(roleId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION, roleId)));


       return role;
    }





}
