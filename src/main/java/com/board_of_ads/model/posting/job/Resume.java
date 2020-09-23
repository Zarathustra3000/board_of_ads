package com.board_of_ads.model.posting.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume extends JobPosting {

    @Column
    private String education;

    @Column
    private byte age;

    @Column
    private String readyForBusinessTrip;

    @Column
    private String citizenship;

    @Column
    private String gender;

    @Column
    private String aboutYourself;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Experience> experiences;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Language> languages;
}