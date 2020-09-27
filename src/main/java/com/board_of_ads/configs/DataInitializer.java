package com.board_of_ads.configs;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.Role;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.KladrService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final KladrService kladrService;

    @PostConstruct
    private void init() throws IOException {
        initUsers();
        initKladr();
    }

    private void initUsers() {

        if (roleService.getRoleByName("ADMIN") == null) {
            roleService.saveRole(new Role("ADMIN"));
        }
        if (roleService.getRoleByName("USER") == null) {
            roleService.saveRole(new Role("USER"));
        }
        if (userService.getUserByEmail("admin@mail.ru") == null) {
            User admin = new User();
            admin.setEmail("admin@mail.ru");
            admin.setPassword("admin");
            admin.setAvatar(new Image(null, "https://example.com/admin.jpg"));
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("ADMIN"));
            admin.setRoles(roleAdmin);
            userService.saveUser(admin);
        }
        if (userService.getUserByEmail("user@mail.ru") == null) {
            User user = new User();
            user.setEmail("user@mail.ru");
            user.setPassword("user");
            user.setAvatar(new Image(null, "https://example.com/user.jpg"));
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("USER"));
            user.setRoles(roleAdmin);
            userService.saveUser(user);
        }
    }

    private void initKladr() throws IOException {
        kladrService.streamKladr();
    }

}
