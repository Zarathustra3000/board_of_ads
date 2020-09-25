package com.board_of_ads.configs;

import com.board_of_ads.model.Category;
import com.board_of_ads.model.City;
import com.board_of_ads.model.Region;
import com.board_of_ads.model.Role;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.KladrService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final KladrService kladrService;
    private final CategoryService categoryService;

    @PostConstruct
    private void init() throws IOException {
        initUsers();
        initKladr();
        initCategories();
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
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("ADMIN"));
            admin.setRoles(roleAdmin);
            userService.saveUser(admin);
        }
        if (userService.getUserByEmail("user@mail.ru") == null) {
            User user = new User();
            user.setEmail("user@mail.ru");
            user.setPassword("user");
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("USER"));
            user.setRoles(roleAdmin);
            userService.saveUser(user);
        }
    }

    private void initCategories() {
        List<Category> categoryList = categoryService.getListCategories();
        for (Category category : categoryList) {
            categoryService.saveCategory(category);
        }
        List<Category> subCategoryList = categoryService.getListSubCategories();
        for (Category category : subCategoryList) {
            categoryService.saveCategory(category);
        }
    }

    private void initKladr() throws IOException {
        kladrService.streamKladr();
    }

}
