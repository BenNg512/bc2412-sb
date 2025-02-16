package com.bootcamp.sbex2.bc_forum.dto.mapper;

import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO.Post;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO.Post.Comment;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserPostCommentDTOMapper {
    public List<UserPostCommentDTO> map(List<UserDTO> users) {
        return users.stream().map(UserPostCommentDTOMapper::toUserPostCommentDTO).collect(Collectors.toList());
    }

    private static UserPostCommentDTO toUserPostCommentDTO(UserDTO user) {
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

    private static UserPostCommentDTO.Address toAddress(UserDTO.Address address) {
        UserPostCommentDTO.Address addressDTO = new UserPostCommentDTO.Address();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setSuite(address.getSuite());
        addressDTO.setCity(address.getCity());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setGeo(toGeo(address.getGeo()));
        return addressDTO;
    }

    private static UserPostCommentDTO.Address.Geo toGeo(UserDTO.Address.Geo geo) {
        UserPostCommentDTO.Address.Geo geoDTO = new UserPostCommentDTO.Address.Geo();
        geoDTO.setLat(geo.getLat());
        geoDTO.setLng(geo.getLng());
        return geoDTO;
    }

    private static UserPostCommentDTO.Company toCompany(UserDTO.Company company) {
        UserPostCommentDTO.Company companyDTO = new UserPostCommentDTO.Company();
        companyDTO.setName(company.getName());
        companyDTO.setCatchPhrase(company.getCatchPhrase());
        companyDTO.setBs(company.getBs());
        return companyDTO;
    }

    private static List<UserPostCommentDTO.Post> toPostList(List<UserDTO.Post> posts) {
        return posts.stream().map(UserPostCommentDTOMapper::toPost).collect(Collectors.toList());
    }

    private static UserPostCommentDTO.Post toPost(UserDTO.Post post) {
        UserPostCommentDTO.Post postDTO = new Post();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(toCommentList(post.getComments()));
        return postDTO;
    }

    private static List<UserPostCommentDTO.Post.Comment> toCommentList(List<UserDTO.Post.Comment> comments) {
        return comments.stream().map(UserPostCommentDTOMapper::toComment).collect(Collectors.toList());
    }

    private static UserPostCommentDTO.Post.Comment toComment(UserDTO.Post.Comment comment) {
        UserPostCommentDTO.Post.Comment commentDTO = new Comment();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
