package com.bootcamp.sbex2.bc_forum.dto.mapper;

import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO.Post;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO.Post.Comment;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserPostCommentDTOMapper {
    public List<UserPostCommentDTO> map(List<UserPostComment> users) {
        return users.stream().map(UserPostCommentDTOMapper::toUserPostCommentDTO).collect(Collectors.toList());
    }

    private static UserPostCommentDTO toUserPostCommentDTO(UserPostComment user) {
        UserPostCommentDTO userPostCommentDTO = UserPostCommentDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(toAddress(user.getAddress()))
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(toCompany(user.getCompany()))
                .posts(toPostList(user.getPosts()))
                .build();
        return userPostCommentDTO;
    }

    private static UserPostCommentDTO.Address toAddress(UserPostComment.Address address) {
        UserPostCommentDTO.Address addressDTO = new UserPostCommentDTO.Address();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setSuite(address.getSuite());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setGeo(toGeo(address.getGeo()));
        return addressDTO;
    }

    private static UserPostCommentDTO.Address.Geo toGeo(UserPostComment.Address.Geo geo) {
        UserPostCommentDTO.Address.Geo geoDTO = new UserPostCommentDTO.Address.Geo();
        geoDTO.setLat(geo.getLat());
        geoDTO.setLng(geo.getLng());
        return geoDTO;
    }

    private static UserPostCommentDTO.Company toCompany(UserPostComment.Company company) {
        UserPostCommentDTO.Company companyDTO = new UserPostCommentDTO.Company();
        companyDTO.setName(company.getName());
        companyDTO.setCatchPhrase(company.getCatchPhrase());
        companyDTO.setBs(company.getBs());
        return companyDTO;
    }

    private static List<UserPostCommentDTO.Post> toPostList(List<UserPostComment.Post> posts) {
        return posts.stream().map(UserPostCommentDTOMapper::toPost).collect(Collectors.toList());
    }

    private static UserPostCommentDTO.Post toPost(UserPostComment.Post post) {
        UserPostCommentDTO.Post postDTO = new Post();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(toCommentList(post.getComments()));
        return postDTO;
    }

    private static List<UserPostCommentDTO.Post.Comment> toCommentList(List<UserPostComment.Post.Comment> comments) {
        return comments.stream().map(UserPostCommentDTOMapper::toComment).collect(Collectors.toList());
    }

    private static UserPostCommentDTO.Post.Comment toComment(UserPostComment.Post.Comment comment) {
        UserPostCommentDTO.Post.Comment commentDTO = new Comment();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
