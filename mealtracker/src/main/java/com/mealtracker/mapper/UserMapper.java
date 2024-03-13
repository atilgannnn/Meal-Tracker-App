package com.mealtracker.mapper;

import com.mealtracker.domain.User;
import com.mealtracker.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
@Mapper
public interface UserMapper {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    void updateUser(User user);
    void deleteUser(Long id);

    void createUser(User user);

    default UserDTO mapUserToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setBuiltIn(user.getBuiltIn());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    Optional<User> getUserById(Long userId);
}
