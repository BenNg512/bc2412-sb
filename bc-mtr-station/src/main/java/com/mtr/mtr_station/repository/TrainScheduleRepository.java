package com.mtr.mtr_station.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mtr.mtr_station.entity.TrainScheduleEntity;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainScheduleEntity, Long> {
}
