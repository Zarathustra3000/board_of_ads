package com.board_of_ads.model.posting;

import com.board_of_ads.model.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс только для категории "Транспорт - Автомобили".
 * Для транспорта всех остальных категорий используется класс PostingTrade
 *
 * Все основные поля наследуются от суперкласса Posting
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posting_cars")
public class CarPosting extends Posting{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    @Column
    private Boolean used;

    @Column
    private String color;

    @Column
    private String youtubeLink;

    @Column
    private String vinNumber;

    @Column
    private String gosNumber;

    @Column
    private String mark;

    @Column
    private Integer mileage;

    @Column
    private Boolean isBroken;

    @Column
    private Integer amountOwner;

    @Column
    private String address;
}
