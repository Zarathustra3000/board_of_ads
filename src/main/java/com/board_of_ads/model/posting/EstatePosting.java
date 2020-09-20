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
 * Класс объявлений о недвижимости.
 *
 * Все основные поля наследуются от суперкласса Posting
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posting_estate")
public class EstatePosting extends Posting {

    @Column
    private String address;

    @Column
    private Boolean isOwner;

    @Column
    private String houseType;

    @Column
    private Byte floor;

    @Column
    private Byte floorsOnHouse;

    @Column
    private Byte amountRooms;

    @Column
    private Integer totalArea;

    @Column
    private Integer kitchenArea;

    @Column
    private Integer livingArea;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> photos;
}
