package com.bootcamp.sbex2.bc_forum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.bc_forum.endpoint.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MockitoExtension.class)
public class CommentServiceTest {

    @MockBean
    private RestTemplate restTemplate;
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
