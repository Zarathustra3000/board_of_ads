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
@Table(name = "posting_resume")
public class ResumePosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String position;

    @Column
    private String schedule;

    @Column
    private String workExperience;

    @Column
    private String education;

    @Column
    private String sex;

    @Column
    private Byte age;

    @Column
    private Boolean readyToBusinessTrip;

    @Column
    private Boolean readyToDislocation;

    @Column
    private String nationality;

    @Column
    private String description;

    @Column
    private Double salary;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> photos;

    @Column
    private String contact;

}
