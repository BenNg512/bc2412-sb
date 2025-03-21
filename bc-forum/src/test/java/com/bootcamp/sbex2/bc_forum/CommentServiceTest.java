package com.bootcamp.sbex2.bc_forum;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.service.impl.CommentServiceImpl;

@WebMvcTest(MockitoExtension.class)
public class CommentServiceTest {

    //@MockBean
    private CommentServiceImpl commentService;

    @Value("${api.jsonplaceholder.domain}")
    private String domain = "jsonplaceholder.typicode.com";

    @Test
    void testGetAllComments() {
        List<CommentDto> expectedComments = Arrays.asList(
                new CommentDto(1L,1L,"A","a@gmail.com","aaa"),
                new CommentDto(2L,2L,"B","b@gmail.com","bbb")
        );

        Mockito.when(commentService.getAllComments()).thenReturn(expectedComments);

        // Test
        // verify result


    }
}
