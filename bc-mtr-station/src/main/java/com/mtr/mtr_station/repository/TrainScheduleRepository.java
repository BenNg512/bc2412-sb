package com.mtr.mtr_station.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mtr.mtr_station.entity.TrainScheduleEntity;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainScheduleEntity, Long> {
  List<TrainScheduleEntity> findByStatus(String status);
}
