package nl.benjamin.muziekmarktplaats.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.benjamin.muziekmarktplaats.dto.ReviewResponseDto;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
class ReviewControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldAddReview() throws Exception {

        String requestJson = """
                {
                    "score" : 8,
                    "comment" : "Heel mooi hoor",
                    "beatId" : 1
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reviews")
                        .with(user("henk").roles("USER"))
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String createdId = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        ReviewResponseDto review = objectMapper.readValue(createdId, ReviewResponseDto.class);

        // check location field in response header (using Hamcrest regex matcher)
        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/reviews/" + review.id));
    }

}