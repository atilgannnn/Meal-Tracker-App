package com.mealtracker.service;

import com.mealtracker.domain.Role;
import com.mealtracker.domain.User;
import com.mealtracker.domain.enums.RoleType;
import com.mealtracker.dto.UserDTO;
import com.mealtracker.dto.request.RegisterRequest;
import com.mealtracker.exception.ConflictException;
import com.mealtracker.exception.ResourceNotFoundException;
import com.mealtracker.exception.message.ErrorMessage;
import com.mealtracker.mapper.UserMapper;
import com.mealtracker.security.SecurityUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    public void createUser(RegisterRequest registerRequest) {

        if(userMapper.existsByEmail(registerRequest.getEmail())) {

            throw new ConflictException(
                String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE,registerRequest.getEmail()));


        }


        Role role = roleService.findByType(RoleType.ROLE_USER);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role.getId());

        userMapper.createUser(user);
    }


    public UserDTO getUserById(Long userId) {

        Optional<User> optionalUser = userMapper.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.mapUserToUserDTO(user);
        } else {
            throw new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION));
        }
    }

    public User getUserByEmail(String email) {

    User user = userMapper.findByEmail(email).orElseThrow(
            ()-> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));

    return user;

    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    public UserDTO getPrincipal() {

        User user = getCurrentUser();
        UserDTO userDTO = userMapper.mapUserToUserDTO(user);

        return userDTO;
    }

    public User getCurrentUser() {
       String email = SecurityUtils.getCurrentUserLogin().orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.PRINCIPAL_NOT_FOUND_EXCEPTION)));

        User user = getUserByEmail(email);

        return user;
    }


}
