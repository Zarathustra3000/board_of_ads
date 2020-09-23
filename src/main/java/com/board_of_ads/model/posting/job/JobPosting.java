package com.board_of_ads.model.posting.job;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.posting.Posting;
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
@Table(name = "posting_job")
public class JobPosting extends Posting {

    @Column
    private String schedule;

    @Column
    private String experienceValue;

    @Column
    private String placeOfWork;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;
}