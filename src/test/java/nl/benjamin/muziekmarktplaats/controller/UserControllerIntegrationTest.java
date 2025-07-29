//package nl.benjamin.muziekmarktplaats.controller;
//
//
//import nl.benjamin.muziekmarktplaats.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.assertj.MockMvcTester;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.text.MatchesPattern.matchesPattern;
//import static org.junit.jupiter.api.extension.MediaType.APPLICATION_JSON;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles("test")
//class UserControllerIntegrationTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void shouldCreateCorrectOrder() throws Exception {
//
//        String requestJson = """
//                {
//                    "email": "henk@hotmail.com",
//                    "password" : "1234",
//                    "username" : "henk"
//                }
//                """;
//
//        MvcResult result = this.mockMvc
//                .perform(MockMvcRequestBuilders.post("/users").contentType(APPLICATION_JSON).content(requestJson)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//
//        String createdId = result.getResponse().getContentAsString();
//        //User user = result.getResponse().getContentType();
//
//        assertThat(result.getResponse().getHeader("Location"), matchesPattern("ˆ.*/users/" + createdId));
//    }
//}