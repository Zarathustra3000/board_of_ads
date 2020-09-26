package com.board_of_ads.service.impl;

import com.board_of_ads.model.posting.Posting;
import com.board_of_ads.repository.PostingRepository;
import com.board_of_ads.service.interfaces.CategoryService;
import com.board_of_ads.service.interfaces.PostingService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public void save(Posting posting) {
        postingRepository.save(posting);
    }

    @Override
    public Optional<Posting> getPostingByTitle(String title) {
        return Optional.ofNullable(postingRepository.findPostingByTitle(title));
    }

    public List<Posting> getTestPostingList() {
        List<Posting> postingList = new ArrayList<>();
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Поглажу кота", "Очень качественно", 100L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Поддержу советом", "Не факт что полезным", 50L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Ремонт электроники", "Быстро, качественно", 1000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Монтаж электросетей", "Любая сложность", 10_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Няня", "от 1 года", 2_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Посмотрю телевизор за Вас", "только 16к", 1_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Схожу за продуктами", "Могу в Ашан, могу в Пятерочку", 1_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Выгуляю собаку", "Ей понравится", 1_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Газовщик", "Любая сложность", 2_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Врач", "Терапевт", 3_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Стоматолог", "Будет не больно", 5_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Киллер", "Будет больно", 300_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Собутыльник", "Будет весело", 500L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Грузовые перевозки", "Трезвые грузчики", 10_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Сыграю в лото", "Я в этом хорош", 500L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Разобью сердце", "Только парням", 10_000L, "+79998887766"));
        postingList.add(new Posting(userService.getUserByEmail("admin@mail.ru"), categoryService.getCategoryByName("Услуги").get()
                , "Схожу в кино", "За компанию", 1_000L, "+79998887766"));
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

        return postingList;
    }
}
