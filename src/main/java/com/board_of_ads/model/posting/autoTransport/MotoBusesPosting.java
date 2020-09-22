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
public class MotoBusesPosting extends CapsPosting {

    private Short profileWidth;

    private Short profileHeight;

    private String axis;
}
