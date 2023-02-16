package com.mykolas.ignitismessagetask.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.mykolas.ignitismessagetask.user.LoginRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerSpecification extends Specification{

    @Autowired
    MockMvc mockMvc;

    def "Expect returned status 401 Unauthorized"() {
        expect: "Status is returned 401 Unauthorized"
        mockMvc.perform(MockMvcRequestBuilders.get("/message").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // TODO Check best practices to write the tests for this.
    @WithMockUser(roles = "USER")
    def "Expect returned 200 OK status when trying to "() {
        expect: "Status is returned 200 Ok"

        LoginRequest loginRequest = new LoginRequest("person@gmail.com","slaptazodis");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(loginRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/message").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
