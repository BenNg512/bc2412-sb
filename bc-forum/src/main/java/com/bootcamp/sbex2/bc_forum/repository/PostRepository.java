package com.bootcamp.sbex2.bc_forum.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  List<PostEntity> findAllByOrderByIdAsc();
  Optional<PostEntity> findById(Integer id);
  List<PostEntity> findAllByUserId(Integer userId);
}
