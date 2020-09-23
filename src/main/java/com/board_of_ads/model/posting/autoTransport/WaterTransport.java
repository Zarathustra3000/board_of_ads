package com.board_of_ads.model.posting.autoTransport;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "water_transport")
public class WaterTransport extends AutoTransport {
}
