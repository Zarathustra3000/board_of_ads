package com.board_of_ads.service.impl;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.AuthService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Data
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(User userAuth) {

        User user = userService.getUserByEmail(userAuth.getEmail());
        if (user == null) {
            return false;
        }
        if (!passwordEncoder.matches(userAuth.getPassword(), user.getPassword())) {
            return false;
        }
        return true;
    }

    /**
     * Метод для получения сессии пользователя
     */
    @Override
    public void login(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
