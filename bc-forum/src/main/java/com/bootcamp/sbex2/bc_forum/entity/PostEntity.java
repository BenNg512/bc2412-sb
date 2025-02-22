package com.bootcamp.sbex2.bc_forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostEntity {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    String title;
    String body;

}
