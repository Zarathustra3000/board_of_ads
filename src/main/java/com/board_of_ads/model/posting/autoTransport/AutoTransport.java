package com.board_of_ads.model.posting.autoTransport;

import com.board_of_ads.model.Image;
import com.board_of_ads.model.posting.Posting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posting_auto_transport")
public class AutoTransport extends Posting {

    @Column
    private String condition;

    @Column
    private String videoURL;

    @Column
    private String meetingAddress;

    @Column
    private String contactEmail;

}
