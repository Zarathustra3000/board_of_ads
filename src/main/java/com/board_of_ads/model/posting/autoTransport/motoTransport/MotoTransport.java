package com.board_of_ads.model.posting.autoTransport.motoTransport;

import com.board_of_ads.model.posting.autoTransport.AutoTransport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "moto_transport")
public class MotoTransport extends AutoTransport {
}
