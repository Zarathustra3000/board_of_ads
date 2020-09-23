package com.board_of_ads.model.posting.autoTransport.partsAndAccessories.tiresDisksWheels;

import com.board_of_ads.model.posting.autoTransport.PartAndAccessorie;
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
@Table(name = "caps")
public class Caps extends PartAndAccessorie {

    private Byte diameter;

}
