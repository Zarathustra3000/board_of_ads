package com.board_of_ads.model.posting.autoTransport.partsAndAccessories.tiresDisksWheels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "disks")
public class Disks extends Caps {

    private String typeOfDisk;

    private Byte diskWidth;

    private Byte numberOfHoles;

    private Short diameterOfHolesPlacement;

    private Short offset;
}
