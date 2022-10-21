package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private CustomerRegistrationRequest customerRegistrationRequest;

    @BeforeEach
    void setup(){
        customerRegistrationRequest = CustomerRegistrationRequest.builder()
                .firstName("Michael")
                .lastName("Boyo")
                .country("Nigeria")
                .email("boyomichaelbidemi@gmail.com")
                .password("12345")
                .build();
    }

    @Test
    void registerUser() throws Exception {
        ObjectMapper mapper  = new ObjectMapper();
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/customer")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(customerRegistrationRequest)))
                        .andExpect(MockMvcResultMatchers.status().is(201))
                        .andDo(print());
    }

    @Test
    void getAllCustomers() {
    }
}