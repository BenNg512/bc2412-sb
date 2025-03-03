package com.mtr.mtr_station.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ttnt;
    private String valid;
    private String plat;
    private String time;
    private String source;
    private String dest;
    private String seq;
    private String direction;
    private String lineStation;
}

