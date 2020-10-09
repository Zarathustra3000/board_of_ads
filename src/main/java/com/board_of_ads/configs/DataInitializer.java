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

    private void initPosting() {
        List<Posting> postingList = new ArrayList<>();
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Поглажу кота", "Очень качественно", 100L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Автомобили").get()
                , "Поддержу советом", "Не факт что полезным", 50L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Квартиры").get()
                , "Ремонт электроники", "Быстро, качественно", 1000L, "+79998887766", cityService.findCityByName("Ростов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Квартиры").get()
                , "Монтаж электросетей", "Любая сложность", 10_000L, "+79998887766", cityService.findCityByName("Азов").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Автомобили").get()
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
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Автомобили").get()
                , "Стоматолог", "Будет не больно", 5_000L, "+79998887766", cityService.findCityByName("Ростов-на-Дону").get()));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Квартиры").get()
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
