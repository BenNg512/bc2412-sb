package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;

public interface JPHService {
  List<UserPostComment> getUsers();
  UserPostComment getUserComments(Long userId);
}
