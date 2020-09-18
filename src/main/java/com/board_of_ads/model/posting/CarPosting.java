package com.board_of_ads.model.posting;

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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posting_cars")
public class CarPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

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
    private String description;

    @Column
    private String address;

    @Column
    private Double price;

    @Column
    private String contacts;
}
