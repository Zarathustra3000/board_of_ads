package com.board_of_ads.configs;

import com.board_of_ads.model.Category;
import com.board_of_ads.model.Role;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.KladrService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(null, "Транспорт", null, null));
        categoryList.add(new Category(null, "Недвижимость", null, null));
        categoryList.add(new Category(null, "Работа", null, null));
        categoryList.add(new Category(null, "Услуги", null, null));
        categoryList.add(new Category(null, "Личные вещи", null, null));
        categoryList.add(new Category(null, "Для дома и дачи", null, null));
        categoryList.add(new Category(null, "Бытовая электроника", null, null));
        categoryList.add(new Category(null, "Хобби и отдых", null, null));
        categoryList.add(new Category(null, "Животные", null, null));
        categoryList.add(new Category(null, "Для бизнеса", null, null));

        for (Category category : categoryList) {
            if (categoryService.getCategoryByName(category.getName()).isEmpty()) {
                categoryService.saveCategory(category);
            }
        }


        List<Category> subCategoryList = new ArrayList<>();
        subCategoryList.add(new Category(null, "Автомобили", categoryService.getCategoryByName("Транспорт").get(), null));
        subCategoryList.add(new Category(null, "Мотоциклы и мототехника", categoryService.getCategoryByName("Транспорт").get(), null));
        subCategoryList.add(new Category(null, "Грузовики и спецтранспорт", categoryService.getCategoryByName("Транспорт").get(), null));
        subCategoryList.add(new Category(null, "Водный транспорт", categoryService.getCategoryByName("Транспорт").get(), null));
        subCategoryList.add(new Category(null, "Запчасти и автоаксессуары", categoryService.getCategoryByName("Транспорт").get(), null));

        subCategoryList.add(new Category(null, "Квартиры", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Комнаты", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Дома, дачи, коттеджи", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Гаражи и машиноместа", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Земельные участки", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Коммерческая недвижимость", categoryService.getCategoryByName("Недвижимость").get(), null));
        subCategoryList.add(new Category(null, "Недвижимость за рубежом", categoryService.getCategoryByName("Недвижимость").get(), null));

        subCategoryList.add(new Category(null, "Вакансии", categoryService.getCategoryByName("Работа").get(), null));
        subCategoryList.add(new Category(null, "Резюме", categoryService.getCategoryByName("Работа").get(), null));

        subCategoryList.add(new Category(null, "Одежда, обувь, аксессуары", categoryService.getCategoryByName("Личные вещи").get(), null));
        subCategoryList.add(new Category(null, "Детская одежда и обувь", categoryService.getCategoryByName("Личные вещи").get(), null));
        subCategoryList.add(new Category(null, "Товары для детей и игрушки", categoryService.getCategoryByName("Личные вещи").get(), null));
        subCategoryList.add(new Category(null, "Часы и украшения", categoryService.getCategoryByName("Личные вещи").get(), null));
        subCategoryList.add(new Category(null, "Красота и здоровье", categoryService.getCategoryByName("Личные вещи").get(), null));

        subCategoryList.add(new Category(null, "Бытовая техника", categoryService.getCategoryByName("Для дома и дачи").get(), null));
        subCategoryList.add(new Category(null, "Мебель и интерьер", categoryService.getCategoryByName("Для дома и дачи").get(), null));
        subCategoryList.add(new Category(null, "Посуда и товары для кухни", categoryService.getCategoryByName("Для дома и дачи").get(), null));
        subCategoryList.add(new Category(null, "Продукты питания", categoryService.getCategoryByName("Для дома и дачи").get(), null));
        subCategoryList.add(new Category(null, "Ремонт и строительство", categoryService.getCategoryByName("Для дома и дачи").get(), null));
        subCategoryList.add(new Category(null, "Растения", categoryService.getCategoryByName("Для дома и дачи").get(), null));

        subCategoryList.add(new Category(null, "Аудио и видео", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Игры, приставки и программы", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Настольные компьютеры", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Ноутбуки", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Оргтехника и расходники", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Планшеты и электронные книги", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Телефоны", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Товары для компьютера", categoryService.getCategoryByName("Бытовая электроника").get(), null));
        subCategoryList.add(new Category(null, "Фототехника", categoryService.getCategoryByName("Бытовая электроника").get(), null));

        subCategoryList.add(new Category(null, "Билеты и путешествия", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Велосипеды", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Книги и журналы", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Коллекционирование", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Музыкальные инструменты", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Охота и рыбалка", categoryService.getCategoryByName("Хобби и отдых").get(), null));
        subCategoryList.add(new Category(null, "Спорт и отдых", categoryService.getCategoryByName("Хобби и отдых").get(), null));

        subCategoryList.add(new Category(null, "Собаки", categoryService.getCategoryByName("Животные").get(), null));
        subCategoryList.add(new Category(null, "Кошки", categoryService.getCategoryByName("Животные").get(), null));
        subCategoryList.add(new Category(null, "Птицы", categoryService.getCategoryByName("Животные").get(), null));
        subCategoryList.add(new Category(null, "Аквариум", categoryService.getCategoryByName("Животные").get(), null));
        subCategoryList.add(new Category(null, "Другие животные", categoryService.getCategoryByName("Животные").get(), null));
        subCategoryList.add(new Category(null, "Товары для животных", categoryService.getCategoryByName("Животные").get(), null));

        subCategoryList.add(new Category(null, "Готовый бизнес", categoryService.getCategoryByName("Для бизнеса").get(), null));
        subCategoryList.add(new Category(null, "Оборудование для бизнеса", categoryService.getCategoryByName("Для бизнеса").get(), null));

        for (Category category : subCategoryList) {
            if (categoryService.getCategoryByName(category.getName()).isEmpty()) {
                categoryService.saveCategory(category);
            }
        }
    }

    private void initKladr() throws IOException {
        kladrService.streamKladr();
    }

}
