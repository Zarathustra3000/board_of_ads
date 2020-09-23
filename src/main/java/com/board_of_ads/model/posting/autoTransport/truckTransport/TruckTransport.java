package com.board_of_ads.model.posting.autoTransport.truckTransport;

import com.board_of_ads.model.posting.autoTransport.AutoTransport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "truck_transport")
public class TruckTransport extends AutoTransport {
}
