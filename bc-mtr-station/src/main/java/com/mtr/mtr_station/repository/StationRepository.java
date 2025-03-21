package com.mtr.mtr_station.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mtr.mtr_station.entity.StationEntity;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {
  @SuppressWarnings("null")
  List<StationEntity> findAll();

  @Query(value = "delete from StationEntity s where s.stationCode = :stationCode", nativeQuery = false)
  public void deleteByStationCode(@Param("station_code") String stationCode);

  @Query(value = "delete from stations s where s.id = :id", nativeQuery = true)
  public void deleteById(@Param("id") Integer id);
  
}
