package com.board_of_ads.model.posting.job;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vacancy")
public class Vacancy extends JobPosting {
}