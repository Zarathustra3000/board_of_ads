package com.board_of_ads.configs;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Image;
import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.models.posting.Posting;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.KladrService;
import com.board_of_ads.service.interfaces.PostingService;
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
    private final PostingService postingService;
    private final CityService cityService;

    @PostConstruct
    private void init() throws IOException {
        initUsers();
        initKladr();
        initCategories();
        initPosting();
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

    private void initCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(null, "Транспорт", null, null, 1));
        categoryList.add(new Category(null, "Недвижимость", null, null, 2));
        categoryList.add(new Category(null, "Работа", null, null, 1));
        categoryList.add(new Category(null, "Услуги", null, null, 1));
        categoryList.add(new Category(null, "Личные вещи", null, null, 1));
        categoryList.add(new Category(null, "Для дома и дачи", null, null, 1));
        categoryList.add(new Category(null, "Бытовая электроника", null, null, 1));
        categoryList.add(new Category(null, "Хобби и отдых", null, null, 1));
        categoryList.add(new Category(null, "Животные", null, null, 1));
        categoryList.add(new Category(null, "Для бизнеса", null, null, 1));

        for (Category category : categoryList) {
            if (categoryService.getCategoryByName(category.getName()).isEmpty()) {
                categoryService.saveCategory(category);
            }
        }


        List<Category> subCategoryList = new ArrayList<>();
        subCategoryList.add(new Category(null, "Автомобили", categoryService.getCategoryByName("Транспорт").get(), null, 2));
        subCategoryList.add(new Category(null, "Мотоциклы и мототехника", categoryService.getCategoryByName("Транспорт").get(), null, 2));
        subCategoryList.add(new Category(null, "Грузовики и спецтранспорт", categoryService.getCategoryByName("Транспорт").get(), null, 2));
        subCategoryList.add(new Category(null, "Водный транспорт", categoryService.getCategoryByName("Транспорт").get(), null, 2));
        subCategoryList.add(new Category(null, "Запчасти и автоаксессуары", categoryService.getCategoryByName("Транспорт").get(), null, 2));

        subCategoryList.add(new Category(null, "Квартиры", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Комнаты", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Дома, дачи, коттеджи", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Гаражи и машиноместа", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Земельные участки", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Коммерческая недвижимость", categoryService.getCategoryByName("Недвижимость").get(), null, 2));
        subCategoryList.add(new Category(null, "Недвижимость за рубежом", categoryService.getCategoryByName("Недвижимость").get(), null, 2));

        subCategoryList.add(new Category(null, "Вакансии", categoryService.getCategoryByName("Работа").get(), null, 2));
        subCategoryList.add(new Category(null, "Резюме", categoryService.getCategoryByName("Работа").get(), null, 2));

        subCategoryList.add(new Category(null, "Одежда, обувь, аксессуары", categoryService.getCategoryByName("Личные вещи").get(), null, 2));
        subCategoryList.add(new Category(null, "Детская одежда и обувь", categoryService.getCategoryByName("Личные вещи").get(), null, 2));
        subCategoryList.add(new Category(null, "Товары для детей и игрушки", categoryService.getCategoryByName("Личные вещи").get(), null, 2));
        subCategoryList.add(new Category(null, "Часы и украшения", categoryService.getCategoryByName("Личные вещи").get(), null, 2));
        subCategoryList.add(new Category(null, "Красота и здоровье", categoryService.getCategoryByName("Личные вещи").get(), null, 2));

        subCategoryList.add(new Category(null, "Бытовая техника", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));
        subCategoryList.add(new Category(null, "Мебель и интерьер", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));
        subCategoryList.add(new Category(null, "Посуда и товары для кухни", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));
        subCategoryList.add(new Category(null, "Продукты питания", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));
        subCategoryList.add(new Category(null, "Ремонт и строительство", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));
        subCategoryList.add(new Category(null, "Растения", categoryService.getCategoryByName("Для дома и дачи").get(), null, 2));

        subCategoryList.add(new Category(null, "Аудио и видео", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Игры, приставки и программы", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Настольные компьютеры", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Ноутбуки", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Оргтехника и расходники", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Планшеты и электронные книги", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Телефоны", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Товары для компьютера", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Фототехника", categoryService.getCategoryByName("Бытовая электроника").get(), null, 2));
        subCategoryList.add(new Category(null, "Билеты и путешествия", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Велосипеды", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Книги и журналы", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Коллекционирование", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Музыкальные инструменты", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Охота и рыбалка", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));
        subCategoryList.add(new Category(null, "Спорт и отдых", categoryService.getCategoryByName("Хобби и отдых").get(), null, 2));

        subCategoryList.add(new Category(null, "Собаки", categoryService.getCategoryByName("Животные").get(), null, 2));
        subCategoryList.add(new Category(null, "Кошки", categoryService.getCategoryByName("Животные").get(), null, 2));
        subCategoryList.add(new Category(null, "Птицы", categoryService.getCategoryByName("Животные").get(), null, 2));
        subCategoryList.add(new Category(null, "Аквариум", categoryService.getCategoryByName("Животные").get(), null, 2));
        subCategoryList.add(new Category(null, "Другие животные", categoryService.getCategoryByName("Животные").get(), null, 2));
        subCategoryList.add(new Category(null, "Товары для животных", categoryService.getCategoryByName("Животные").get(), null, 2));

        subCategoryList.add(new Category(null, "Готовый бизнес", categoryService.getCategoryByName("Для бизнеса").get(), null, 2));
        subCategoryList.add(new Category(null, "Оборудование для бизнеса", categoryService.getCategoryByName("Для бизнеса").get(), null, 2));

        for (Category category : subCategoryList) {
            if (categoryService.getCategoryByName(category.getName()).isEmpty()) {
                categoryService.saveCategory(category);
            }
        }



        List<Category> secondSubCategory = new ArrayList<>();
        secondSubCategory.add(new Category(null, "С пробегом", categoryService.getCategoryByName("Автомобили").get(), null, 3));
        secondSubCategory.add(new Category(null, "Новые", categoryService.getCategoryByName("Автомобили").get(), null, 3));

        secondSubCategory.add(new Category(null, "Багги", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Вездеходы", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Картинг", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Квадроциклы", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Мопеды и скутеры", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Мотоциклы", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Снегоходы", categoryService.getCategoryByName("Мотоциклы и мототехника").get(), null, 3));

        secondSubCategory.add(new Category(null, "Автобусы", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Автодома", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Автокраны", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Бульдозеры", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Грузовики", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Коммунальная техника", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Легкий транспорт", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Погрузчики", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Прицепы", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сельхозтехникам", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Строительная техника", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Техника для лесозаготовки", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Тягачи", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Эскаваторы", categoryService.getCategoryByName("Грузовики и спецтранспорт").get(), null, 3));

        secondSubCategory.add(new Category(null, "Вёсельные лодки", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Гидроциклы", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Катера и яхты", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Каяки и каноэ", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Моторные лодки", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));
        secondSubCategory.add(new Category(null, "Надувные лодки", categoryService.getCategoryByName("Водный транспорт").get(), null, 3));

        secondSubCategory.add(new Category(null, "Запчасти", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аксессуары", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "GPS-навигаторы", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Автокосметика и автохимия", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аудио и видеотехника", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Багажники и фаркопы", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Инструменты", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Прицепы", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Противоугонные устройства", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Тюнинг", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Шины, диски и колеса", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Экипировка", categoryService.getCategoryByName("Запчасти и автоаксессуары").get(), null, 3));


        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Квартиры").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Квартиры").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Квартиры").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Квартиры").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Комнаты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Комнаты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Комнаты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Комнаты").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Дома, дачи, коттеджи").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Дома, дачи, коттеджи").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Дома, дачи, коттеджи").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Дома, дачи, коттеджи").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Гаражи и машиноместа").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Гаражи и машиноместа").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Гаражи и машиноместа").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Гаражи и машиноместа").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Земельные участки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Земельные участки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Земельные участки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Земельные участки").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Коммерческая недвижимость").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Коммерческая недвижимость").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Коммерческая недвижимость").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Коммерческая недвижимость").get(), null, 3));

        secondSubCategory.add(new Category(null, "Продам", categoryService.getCategoryByName("Недвижимость за рубежом").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сдам", categoryService.getCategoryByName("Недвижимость за рубежом").get(), null, 3));
        secondSubCategory.add(new Category(null, "Куплю", categoryService.getCategoryByName("Недвижимость за рубежом").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сниму", categoryService.getCategoryByName("Недвижимость за рубежом").get(), null, 3));


        secondSubCategory.add(new Category(null, "IT, интернет, телеком", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Автомобильный бизнес", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Административная работа", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Банки, инвестиции", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Без опыта, студенты", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Бухгалтерия, финансы", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Высший менеджмент", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Госслужба, НКО", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Домашний персонал", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "ЖКХ, эксплуатация", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Исскуство, развлечения", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Консультирование", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Маркетинг, реклама, PR", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Медицина, фармацевтика", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Образование, наука", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Охрана, безопасность", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Продажи", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Производство, сырьё, с/х", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Страхование", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Строительство", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Транспорт, логистика", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Туризм, рестораны", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Управление персоналом", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Фитнес, салоны красоты", categoryService.getCategoryByName("Вакансии").get(), null, 3));
        secondSubCategory.add(new Category(null, "Юриспруденция", categoryService.getCategoryByName("Вакансии").get(), null, 3));

        secondSubCategory.add(new Category(null, "IT, интернет, телеком", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Автомобильный бизнес", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Административная работа", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Банки, инвестиции", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Без опыта, студенты", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Бухгалтерия, финансы", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Высший менеджмент", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Госслужба, НКО", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Домашний персонал", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "ЖКХ, эксплуатация", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Исскуство, развлечения", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Консультирование", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Маркетинг, реклама, PR", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Медицина, фармацевтика", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Образование, наука", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Охрана, безопасность", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Продажи", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Производство, сырьё, с/х", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Страхование", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Строительство", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Транспорт, логистика", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Туризм, рестораны", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Управление персоналом", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Фитнес, салоны красоты", categoryService.getCategoryByName("Резюме").get(), null, 3));
        secondSubCategory.add(new Category(null, "Юриспруденция", categoryService.getCategoryByName("Резюме").get(), null, 3));


        secondSubCategory.add(new Category(null, "Женская одежда", categoryService.getCategoryByName("Одежда, обувь, аксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Мужская одежда", categoryService.getCategoryByName("Одежда, обувь, аксессуары").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аксессуары", categoryService.getCategoryByName("Одежда, обувь, аксессуары").get(), null, 3));

        secondSubCategory.add(new Category(null, "Для девочек", categoryService.getCategoryByName("Детская одежда и обувь").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для мальчиков", categoryService.getCategoryByName("Детская одежда и обувь").get(), null, 3));

        secondSubCategory.add(new Category(null, "Автомобильные кресла", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Велосипеды и самокаты", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Детская мебель", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Детские коляски", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Игрушки", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Постельные принадлежности", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Товары для кормления", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Товары для купания", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));
        secondSubCategory.add(new Category(null, "Товары для школы", categoryService.getCategoryByName("Товары для детей и игрушки").get(), null, 3));

        secondSubCategory.add(new Category(null, "Бижутерия", categoryService.getCategoryByName("Часы и украшения").get(), null, 3));
        secondSubCategory.add(new Category(null, "Часы", categoryService.getCategoryByName("Часы и украшения").get(), null, 3));
        secondSubCategory.add(new Category(null, "Ювелирные изделия", categoryService.getCategoryByName("Часы и украшения").get(), null, 3));

        secondSubCategory.add(new Category(null, "Косметика", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Парфюмерия", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Приборы и аксессуары", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Средства гигиены", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Средства для волос", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Медицинские изделия", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Биологически активные добавки", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));
        secondSubCategory.add(new Category(null, "Услуги", categoryService.getCategoryByName("Красота и здоровье").get(), null, 3));


        secondSubCategory.add(new Category(null, "Для дома", categoryService.getCategoryByName("Бытовая техника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для индивидуального ухода", categoryService.getCategoryByName("Бытовая техника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для кухни", categoryService.getCategoryByName("Бытовая техника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Климатическое оборудование", categoryService.getCategoryByName("Бытовая техника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Бытовая техника").get(), null, 3));

        secondSubCategory.add(new Category(null, "Компьютерные столы и кресла", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Кровати, диваны и кресла", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Кухонные гарнитуры", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Освещение", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Подставки и тумбы", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Предметы интерьера, искусство", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Столы и стулья", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Текстиль и ковры", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Шкафы и комоды", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Мебель и интерьер").get(), null, 3));

        secondSubCategory.add(new Category(null, "Посуда", categoryService.getCategoryByName("Посуда и товары для кухни").get(), null, 3));
        secondSubCategory.add(new Category(null, "Товары для кухни", categoryService.getCategoryByName("Посуда и товары для кухни").get(), null, 3));

        secondSubCategory.add(new Category(null, "Двери", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Инструменты", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Камины и обогреватели", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Окна и балконы", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Потолки", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Садовая техника", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сантехника и сауна", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Стройматериалы", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));
        secondSubCategory.add(new Category(null, "Услуги", categoryService.getCategoryByName("Ремонт и строительство").get(), null, 3));


        secondSubCategory.add(new Category(null, "MP3 плееры", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Акустика, колонки, сабвуферы", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Видео, DVD и Blu-Ray плееры", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Видеокамеры", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Кабели и адаптеры", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Микрофоны", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Музыка и фильмы", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Музыкальные центры, магнитолы", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Наушники", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Телевизоры и проекторы", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Усилители и ресиверы", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аксессуары", categoryService.getCategoryByName("Аудио и видео").get(), null, 3));

        secondSubCategory.add(new Category(null, "Игры для приставок", categoryService.getCategoryByName("Игры, приставки и программы").get(), null, 3));
        secondSubCategory.add(new Category(null, "Игровые приставки", categoryService.getCategoryByName("Игры, приставки и программы").get(), null, 3));
        secondSubCategory.add(new Category(null, "Компьютерные игры", categoryService.getCategoryByName("Игры, приставки и программы").get(), null, 3));
        secondSubCategory.add(new Category(null, "Программы", categoryService.getCategoryByName("Игры, приставки и программы").get(), null, 3));

        secondSubCategory.add(new Category(null, "МФУ, копиры и сканнеры", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "Принтеры", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "Телефония", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "ИБП, сетевые фильтры", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "Уничтожители бумаг", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "Расходные материалы", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));
        secondSubCategory.add(new Category(null, "Канцелярия", categoryService.getCategoryByName("Оргтехника и расходники").get(), null, 3));

        secondSubCategory.add(new Category(null, "Планшеты", categoryService.getCategoryByName("Планшеты и электронные книги").get(), null, 3));
        secondSubCategory.add(new Category(null, "Электронные книги", categoryService.getCategoryByName("Планшеты и электронные книги").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аксессуары", categoryService.getCategoryByName("Планшеты и электронные книги").get(), null, 3));

        secondSubCategory.add(new Category(null, "Акустика", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Веб-камеры", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Джойстики и руль", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Клавиатуры и мыши", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Комплектующее", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Мониторы", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Переносные жёсткие диски", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сетевое оборудование", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "ТВ-тюнеры", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Флешки и карты памяти", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));
        secondSubCategory.add(new Category(null, "Акксессуары", categoryService.getCategoryByName("Товары для компьютера").get(), null, 3));

        secondSubCategory.add(new Category(null, "Компактные фотоаппараты", categoryService.getCategoryByName("Фототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Зеркальные фотоаппараты", categoryService.getCategoryByName("Фототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Пленочные фотоаппараты", categoryService.getCategoryByName("Фототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Бинокли и телескопы", categoryService.getCategoryByName("Фототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Объективы", categoryService.getCategoryByName("Фототехника").get(), null, 3));
        secondSubCategory.add(new Category(null, "Оборудование и аксессуары", categoryService.getCategoryByName("Фототехника").get(), null, 3));


        secondSubCategory.add(new Category(null, "Карты, купоны", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Концерты", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Путешествия", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Спорт", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Театр, опера, балет", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Цирк, кино", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));
        secondSubCategory.add(new Category(null, "Шоу, мюзикл", categoryService.getCategoryByName("Билеты и путешествия").get(), null, 3));

        secondSubCategory.add(new Category(null, "Горные", categoryService.getCategoryByName("Велосипеды").get(), null, 3));
        secondSubCategory.add(new Category(null, "Дорожные", categoryService.getCategoryByName("Велосипеды").get(), null, 3));
        secondSubCategory.add(new Category(null, "BMX", categoryService.getCategoryByName("Велосипеды").get(), null, 3));
        secondSubCategory.add(new Category(null, "Детские", categoryService.getCategoryByName("Велосипеды").get(), null, 3));
        secondSubCategory.add(new Category(null, "Запчасти и аксессуары", categoryService.getCategoryByName("Велосипеды").get(), null, 3));

        secondSubCategory.add(new Category(null, "Журналы, газеты, брошюры", categoryService.getCategoryByName("Книги и журналы").get(), null, 3));
        secondSubCategory.add(new Category(null, "Книги", categoryService.getCategoryByName("Книги и журналы").get(), null, 3));
        secondSubCategory.add(new Category(null, "Учебная литература", categoryService.getCategoryByName("Книги и журналы").get(), null, 3));

        secondSubCategory.add(new Category(null, "Банкноты", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Билеты", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Вещи знаменитостей, автографы", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Военные вещи", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Грампластинки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Документы", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Жетоны, медали, значки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Игры", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Календари", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Картины", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Киндер-Сюрприз", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Конверты и почтовые карточки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Макеты оружия", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Марки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Модели", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Монеты", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Открытки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Пепельницы, зажигалки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Пластиковые карточки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Спортивные карточки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Фотографии, письма", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Этикетки, бутылки, пробки", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Коллекционирование").get(), null, 3));

        secondSubCategory.add(new Category(null, "Аккордеоны, гармонии, баяны", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Гитары и другие струнные", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Духовные", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Пианино и другие клавишные", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Скрипки и другие смычковые", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Ударные", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для студии и концертов", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));
        secondSubCategory.add(new Category(null, "Аксессуары", categoryService.getCategoryByName("Музыкальные инструменты").get(), null, 3));

        secondSubCategory.add(new Category(null, "Бильярд и боулинг", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Дайвинг и водный спорт", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Единоборства", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Зимние виды спорта", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Игры с мячом", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Настольные игры", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Пейнтбол и страйкбол", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Ролики и скейтбординг", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Теннис, бадминтон, пинг-понг", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Туризм", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Финтес и тренажеры", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Спортивное питание", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Спорт и отдых").get(), null, 3));


        secondSubCategory.add(new Category(null, "Интернет-магазин", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Общественное питание", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Производство", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Развлечение", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сельское хозяйство", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Строительство", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Сфера услуг", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Торговля", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Готовый бизнес").get(), null, 3));

        secondSubCategory.add(new Category(null, "Для магазина", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для офиса", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для ресторана", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));
        secondSubCategory.add(new Category(null, "Для салона красоты", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));
        secondSubCategory.add(new Category(null, "Промышленное", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));
        secondSubCategory.add(new Category(null, "Другое", categoryService.getCategoryByName("Оборудование для бизнеса").get(), null, 3));

        for (Category category : secondSubCategory) {
            if (categoryService.getCategoryByName(category.getName()).isEmpty()) {
                categoryService.saveCategory(category);
            }
        }
    }

    private void initKladr() throws IOException {
        kladrService.streamKladr();
    }

    private void initPosting() {
        List<Posting> postingList = new ArrayList<>();
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Поглажу кота", "Очень качественно", 100L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Поддержу советом", "Не факт что полезным", 50L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Ремонт электроники", "Быстро, качественно", 1000L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Монтаж электросетей", "Любая сложность", 10_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Няня", "от 1 года", 2_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Посмотрю телевизор за Вас", "только 16к", 1_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Схожу за продуктами", "Могу в Ашан, могу в Пятерочку", 1_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Выгуляю собаку", "Ей понравится", 1_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Газовщик", "Любая сложность", 2_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Врач", "Терапевт", 3_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Стоматолог", "Будет не больно", 5_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Киллер", "Будет больно", 300_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Собутыльник", "Будет весело", 500L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Грузовые перевозки", "Трезвые грузчики", 10_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Сыграю в лото", "Я в этом хорош", 500L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Разобью сердце", "Только парням", 10_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Схожу в кино", "За компанию", 1_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Сдамся в рабство", "ненадолго", 50_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Уведу у Вас девушку", "Вдруг она вам надоела", 3_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Обижу обидчиков", "Не старше 18 лет", 5_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Отмажу от ментов", "У меня папка начальник", 10_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Вынесу мусор", "Небольшой", 400L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Научу играть на гитаре", "Учил самого Цоя", 10_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Научу играть в Warcraft", "PvP или зассал?", 10_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Продам средство для похудения", "Результат уже через 3 дня. Нужно всего лишь 1 ложка...", 10_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Продам ядерный реактор", "Самовывоз с Припяти", 500_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Стань программистом за 1 урок", "Урок №1: перезагрузка роутера", 20_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Свадебный фотограф", "Фоткаю на iPhone 7", 10_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Компьютерный мастер на дом", "Перезагружу ваш компьютер быстро и качественно", 4_000L, "+79896661488"));
        postingList.add(new Posting(userService.getUserByEmail("user@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Сесть на пенёк", "...Почему тут так мало?", 1_000L, "+79896661488"));

        for (Posting posting : postingList) {
            if (postingService.getPostingByTitle(posting.getTitle()).isEmpty()) {
                postingService.save(posting);
            }
        }
    }

}
