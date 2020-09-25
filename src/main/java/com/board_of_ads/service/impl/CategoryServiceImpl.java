package com.board_of_ads.service.impl;

import com.board_of_ads.model.Category;
import com.board_of_ads.repository.CategoryRepository;
import com.board_of_ads.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public List<Category> getListCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category()); //empty
        categories.add(new Category(0L, "Транспорт", null, null));
        categories.add(new Category(0L, "Недвижимость", null, null));
        categories.add(new Category(0L, "Работа", null, null));
        categories.add(new Category(0L, "Услуги", null, null));
        categories.add(new Category(0L, "Личные вещи", null, null));
        categories.add(new Category(0L, "Для дома и дачи", null, null));
        categories.add(new Category(0L, "Бытовая электроника", null, null));
        categories.add(new Category(0L, "Хобби и отдых", null, null));
        categories.add(new Category(0L, "Животные", null, null));
        categories.add(new Category(0L, "Для бизнеса", null, null));
        return categories;
    }

    @Override
    public List<Category> getListSubCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0L, "Автомобили", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(0L, "Мотоциклы и мототехника", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(0L, "Грузовики и спецтранспорт", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(0L, "Водный транспорт", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(0L, "Запчасти и автоаксессуары", categoryRepository.findCategoryByName("Транспорт"), null));

        categories.add(new Category(0L, "Квартиры", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Комнаты", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Дома, дачи, коттеджи", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Гаражи и машиноместа", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Земельные участки", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Коммерческая недвижимость", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(0L, "Недвижимость за рубежом", categoryRepository.findCategoryByName("Недвижимость"), null));

        categories.add(new Category(0L, "Вакансии", categoryRepository.findCategoryByName("Работа"), null));
        categories.add(new Category(0L, "Резюме", categoryRepository.findCategoryByName("Работа"), null));

        categories.add(new Category(0L, "Одежда, обувь, аксессуары", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(0L, "Детская одежда и обувь", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(0L, "Товары для детей и игрушки", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(0L, "Часы и украшения", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(0L, "Красота и здоровье", categoryRepository.findCategoryByName("Личные вещи"), null));

        categories.add(new Category(0L, "Бытовая техника", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(0L, "Мебель и интерьер", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(0L, "Посуда и товары для кухни", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(0L, "Продукты питания", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(0L, "Ремонт и строительство", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(0L, "Растения", categoryRepository.findCategoryByName("Для дома и дачи"), null));

        categories.add(new Category(0L, "Аудио и видео", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Игры, приставки и программы", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Настольные компьютеры", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Ноутбуки", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Оргтехника и расходники", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Планшеты и электронные книги", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Телефоны", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Товары для компьютера", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(0L, "Фототехника", categoryRepository.findCategoryByName("Бытовая электроника"), null));

        categories.add(new Category(0L, "Билеты и путешествия", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Велосипеды", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Книги и журналы", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Коллекционирование", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Музыкальные инструменты", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Охота и рыбалка", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(0L, "Спорт и отдых", categoryRepository.findCategoryByName("Хобби и отдых"), null));

        categories.add(new Category(0L, "Собаки", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(0L, "Кошки", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(0L, "Птицы", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(0L, "Аквариум", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(0L, "Другие животные", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(0L, "Товары для животных", categoryRepository.findCategoryByName("Животные"), null));

        categories.add(new Category(0L, "Готовый бизнес", categoryRepository.findCategoryByName("Для бизнеса"), null));
        categories.add(new Category(0L, "Оборудование для бизнеса", categoryRepository.findCategoryByName("Для бизнеса"), null));
        return categories;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
