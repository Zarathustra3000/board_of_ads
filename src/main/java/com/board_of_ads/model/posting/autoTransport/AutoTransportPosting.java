package com.board_of_ads.model.posting.autoTransport;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.posting.Posting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Entity
public class AutoTransportPosting extends Posting {

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    private String condition;

    private String videoURL;

    private String meetingAddress;

    private String contactEmail;

}
