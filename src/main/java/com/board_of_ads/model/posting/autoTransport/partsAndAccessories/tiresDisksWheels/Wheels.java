package com.board_of_ads.model.posting.autoTransport.partsAndAccessories.tiresDisksWheels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wheels")
public class Wheels extends Disks {

    private String season;

    private Short profileWidth;

    private Short profileHeight;
}
