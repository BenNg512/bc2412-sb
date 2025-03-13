package com.xfin.bc_xfin_service.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Public_Holidays")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder
public class PublicHolidayEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String date;
  private String localName;
  private String name;
  private String countryCode;
  private boolean fixed;
  private boolean global;

  @ElementCollection
  private List<String> counties;

  private Integer launchYear;

  @ElementCollection
  private List<String> types;
}
