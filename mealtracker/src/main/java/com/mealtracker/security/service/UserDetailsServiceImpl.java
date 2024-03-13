package com.mealtracker.security.service;

import com.mealtracker.domain.Role;
import com.mealtracker.domain.User;
import com.mealtracker.service.RoleService;
import com.mealtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.getUserByEmail(email);

        return build(user);
    }


    public UserDetailsImpl build(User user){
        List<Role> roleList = new ArrayList<>();
        final int roleId = user.getRole();
        Role userRole =  roleService.findById(roleId);

        roleList.add(userRole);
        List<SimpleGrantedAuthority> authorities = roleList.
                stream().map(role -> new SimpleGrantedAuthority(role.getType().name())).collect(Collectors.toList());

        return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities);
    }
}
