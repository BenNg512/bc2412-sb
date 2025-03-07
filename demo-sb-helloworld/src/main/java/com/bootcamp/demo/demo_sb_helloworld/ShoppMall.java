package com.bootcamp.demo.demo_sb_helloworld;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShoppMall {
  private String name;
  private int area;
  private Cinema cinema;
  private String[] shopCategory;

  @Getter
  @Builder
  public static class Cinema {
    private String name;
    private LocalDate openedDate;
    private Film[] releasedFilms;

    @Getter
    @Builder
    public static class Film {
      private String name;
      private LocalDate releaseDate;
    }
  }
}
