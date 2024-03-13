package com.mealtracker.mapper;

import com.mealtracker.domain.Role;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;
@Mapper
public interface RoleMapper {

    Optional<Role> findByType(String type);
    Optional<Role> findById(int id);

}
