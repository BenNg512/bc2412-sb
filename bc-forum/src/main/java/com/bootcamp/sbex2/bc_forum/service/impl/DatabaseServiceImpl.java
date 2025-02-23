package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.AddressEntity;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.CompanyEntity;
import com.bootcamp.sbex2.bc_forum.entity.GeoEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.entity.map.EntityMapper;
import com.bootcamp.sbex2.bc_forum.repository.AddressRepository;
import com.bootcamp.sbex2.bc_forum.repository.CommentRepository;
import com.bootcamp.sbex2.bc_forum.repository.CompanyRepository;
import com.bootcamp.sbex2.bc_forum.repository.GeoRepository;
import com.bootcamp.sbex2.bc_forum.repository.PostRepository;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.DatabaseService;
import jakarta.transaction.Transactional;

@Service
public class DatabaseServiceImpl implements DatabaseService {
  @Autowired
  CommentRepository commentRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  AddressRepository addressRepository;
  @Autowired
  GeoRepository geoRepository;
  @Autowired
  CompanyRepository companyRepository;
  @Autowired
  EntityMapper entityMapper;
  
  @Override
  public List<CommentEntity> getAllComments(){
      return this.commentRepository.findAllByOrderByIdAsc();
    }
  @Override
  public List<CommentEntity> getCommentsByPostId(Long postId){
      return this.commentRepository.findAllByPostId(postId.intValue());
    }

  @Override
  public CommentEntity addCommentToPost(Long postId, CommentEntity comment) {
      comment.setPostId(postId);
      comment.setId(this.commentRepository.findMaxCommentId()+1);
    return this.commentRepository.save(comment);
  }

  @Override
  public CommentEntity patchComment(Long commentId, CommentEntity partialUpdate){
    if (commentRepository.findById(commentId).isPresent()) {
        CommentEntity comment = commentRepository.findById(commentId).get();
        if (partialUpdate.getName() != null) {
            comment.setName(partialUpdate.getName());
        }
        if (partialUpdate.getEmail() != null) {
            comment.setEmail(partialUpdate.getEmail());
        }
        if (partialUpdate.getBody() != null) {
            comment.setBody(partialUpdate.getBody());
        }
        return commentRepository.save(comment);
    } else {
        throw new RuntimeException("Comment with id " + commentId + " not found");
    }
  }

  @Override
  public List<PostEntity> getAllPosts(){
    return this.postRepository.findAllByOrderByIdAsc();
  }
  
  @Override
  public List<PostEntity> getPostsByUserId(Long userId) {
    return this.postRepository.findAllByUserId(userId.intValue());
    //return this.postRepository.findAllByOrderByIdAsc();
  }

  @Override
  public PostEntity addPostByUserId(Long userId, PostEntity postEntity){
    postEntity.setUserId(userId.longValue());
    postEntity.setId(this.postRepository.findMaxPostId()+1);
  return this.postRepository.save(postEntity);
  }

  @Override
  @Transactional
  public void deletePostAndComments(Long postId){
    this.postRepository.deleteById(postId);
    this.commentRepository.deleteByPostId(postId);
  }

  @Override
  public List<UserEntity> getAllUsers(){
    return this.userRepository.findAllByOrderByIdAsc();
  }

  @Override
  public List<UserDTO> getAllUserDTOs(){
    List<UserEntity> userEntities = this.userRepository.findAll();
    List<AddressEntity> addressEntities = this.addressRepository.findAll();
    List<CompanyEntity> companyEntities = this.companyRepository.findAll();
    List<GeoEntity> geoEntities = this.geoRepository.findAll();

      return this.entityMapper.map(
        userEntities, 
        addressEntities, 
        companyEntities, 
        geoEntities);
  }
  
}
