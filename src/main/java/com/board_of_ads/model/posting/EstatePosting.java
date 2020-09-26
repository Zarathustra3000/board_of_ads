package com.board_of_ads.model.posting;


import com.board_of_ads.model.Image;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

//Пост с недвижимостью

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posting_estate")
public class EstatePosting extends Posting {

    //Адресс
    @Column
    private String address;
    //Количество комнат
    @Column
    private Integer countRoom;
    //Является ли собственником
    @Column
    private boolean isProprietor;
    //У одного обьявления может быть много фоток
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Image> photos;
}
