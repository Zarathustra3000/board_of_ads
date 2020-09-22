package com.board_of_ads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "city_form_subject")
    String regionFormSubject;

    @ManyToOne
    Region region;

    public City(String name, Region region, String regionFormSubject) {
        this.regionFormSubject = regionFormSubject;
        this.name = name;
        this.region = region;
    }
}
