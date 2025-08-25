package nl.benjamin.muziekmarktplaats.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.benjamin.muziekmarktplaats.dto.UserResponseDto;
import nl.benjamin.muziekmarktplaats.model.User;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldAddUser() throws Exception {

        String requestJson = """
                {
                    "username" : "Klaas",
                    "email" : "klaas@hotmail.com",
                    "password" :  "1234"
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String createdId = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        UserResponseDto user = objectMapper.readValue(createdId, UserResponseDto.class);

        // check location field in response header (using Hamcrest regex matcher)
        assertThat(result.getResponse().getHeader("Location"), matchesPattern("^.*/users/" + user.id));
    }
}