package com.board_of_ads.model.posting.autoTransport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WheelsPosting extends DisksPosting {

    private String season;

    private Short profileWidth;

    private Short profileHeight;
}
