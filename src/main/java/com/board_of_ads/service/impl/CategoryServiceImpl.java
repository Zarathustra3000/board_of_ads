package com.board_of_ads.service.impl;

import com.board_of_ads.model.Category;
import com.board_of_ads.repository.CategoryRepository;
import com.board_of_ads.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return Optional.ofNullable(categoryRepository.findCategoryByName(name));
    }

    @Override
    public List<Category> getListCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(null, "Транспорт", null, null));
        categories.add(new Category(null, "Недвижимость", null, null));
        categories.add(new Category(null, "Работа", null, null));
        categories.add(new Category(null, "Услуги", null, null));
        categories.add(new Category(null, "Личные вещи", null, null));
        categories.add(new Category(null, "Для дома и дачи", null, null));
        categories.add(new Category(null, "Бытовая электроника", null, null));
        categories.add(new Category(null, "Хобби и отдых", null, null));
        categories.add(new Category(null, "Животные", null, null));
        categories.add(new Category(null, "Для бизнеса", null, null));
        return categories;
    }

    @Override
    public List<Category> getListSubCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(null, "Автомобили", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(null, "Мотоциклы и мототехника", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(null, "Грузовики и спецтранспорт", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(null, "Водный транспорт", categoryRepository.findCategoryByName("Транспорт"), null));
        categories.add(new Category(null, "Запчасти и автоаксессуары", categoryRepository.findCategoryByName("Транспорт"), null));

        categories.add(new Category(null, "Квартиры", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Комнаты", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Дома, дачи, коттеджи", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Гаражи и машиноместа", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Земельные участки", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Коммерческая недвижимость", categoryRepository.findCategoryByName("Недвижимость"), null));
        categories.add(new Category(null, "Недвижимость за рубежом", categoryRepository.findCategoryByName("Недвижимость"), null));

        categories.add(new Category(null, "Вакансии", categoryRepository.findCategoryByName("Работа"), null));
        categories.add(new Category(null, "Резюме", categoryRepository.findCategoryByName("Работа"), null));

        categories.add(new Category(null, "Одежда, обувь, аксессуары", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(null, "Детская одежда и обувь", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(null, "Товары для детей и игрушки", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(null, "Часы и украшения", categoryRepository.findCategoryByName("Личные вещи"), null));
        categories.add(new Category(null, "Красота и здоровье", categoryRepository.findCategoryByName("Личные вещи"), null));

        categories.add(new Category(null, "Бытовая техника", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(null, "Мебель и интерьер", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(null, "Посуда и товары для кухни", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(null, "Продукты питания", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(null, "Ремонт и строительство", categoryRepository.findCategoryByName("Для дома и дачи"), null));
        categories.add(new Category(null, "Растения", categoryRepository.findCategoryByName("Для дома и дачи"), null));

        categories.add(new Category(null, "Аудио и видео", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Игры, приставки и программы", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Настольные компьютеры", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Ноутбуки", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Оргтехника и расходники", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Планшеты и электронные книги", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Телефоны", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Товары для компьютера", categoryRepository.findCategoryByName("Бытовая электроника"), null));
        categories.add(new Category(null, "Фототехника", categoryRepository.findCategoryByName("Бытовая электроника"), null));

        categories.add(new Category(null, "Билеты и путешествия", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Велосипеды", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Книги и журналы", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Коллекционирование", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Музыкальные инструменты", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Охота и рыбалка", categoryRepository.findCategoryByName("Хобби и отдых"), null));
        categories.add(new Category(null, "Спорт и отдых", categoryRepository.findCategoryByName("Хобби и отдых"), null));

        categories.add(new Category(null, "Собаки", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(null, "Кошки", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(null, "Птицы", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(null, "Аквариум", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(null, "Другие животные", categoryRepository.findCategoryByName("Животные"), null));
        categories.add(new Category(null, "Товары для животных", categoryRepository.findCategoryByName("Животные"), null));

        categories.add(new Category(null, "Готовый бизнес", categoryRepository.findCategoryByName("Для бизнеса"), null));
        categories.add(new Category(null, "Оборудование для бизнеса", categoryRepository.findCategoryByName("Для бизнеса"), null));
        return categories;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
