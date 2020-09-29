package com.board_of_ads.models.posting;

import com.board_of_ads.models.Category;
import com.board_of_ads.models.Image;
import com.board_of_ads.models.User;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Суперкласс для объявлений
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "posting")
public class Posting {

    public Posting(User user, Category category, String title, String description, Long price, String contact) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.contact = contact;
        this.isActive = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Long price;

    @Column
    private String contact;

    @Column
    private Boolean isActive;

    @Column
    private LocalDateTime datePosting = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;
}
