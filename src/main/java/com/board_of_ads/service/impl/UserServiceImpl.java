package com.board_of_ads.service.impl;

import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
import com.board_of_ads.repository.ImageRepository;
import com.board_of_ads.repository.UserRepository;
import com.board_of_ads.service.interfaces.AuthorizationService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import com.board_of_ads.util.BindingResultLogs;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final BindingResultLogs bindingResultLogs;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new RuntimeException("User identity " + id + "not found!"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User regUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar(new Image(null, "images/user.jpg"));
        user.setRoles(roleService.defaultRolesSet());
        return userRepository.save(user);
    }

    @Override
    public boolean checkUserDataBeforeReg(User user, BindingResult bindingResult, Logger logger) {
        if (getUserByEmail(user.getEmail()) == null
                && bindingResultLogs.checkUserFields(bindingResult, logger)) {
            regUser(user);
            return true;
        }
        return false;
    }


}
