package com.board_of_ads.model.posting;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для простых объявлений, не попдающих под категории "Транспорт - Автомобили", "Недвижимость", "Работа".
 *
 * Все основные поля наследуются от суперкласса Posting
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posting_trade")
public class PostingTrade extends Posting {

    @Column
    private Boolean used;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;

    @Column
    private String youtubeLink;

    @Column
    private String address;
}
