package com.mtr.mtr_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stations", uniqueConstraints = {@UniqueConstraint(columnNames = "stationCode")})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3)
    private String line;
    @Column(length = 3)
    private String station;
    @Column(length = 7)
    private String stationCode;
}
