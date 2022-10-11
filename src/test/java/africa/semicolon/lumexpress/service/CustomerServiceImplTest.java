package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.service.customerService.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    private CustomerRegistrationRequest request;
    @BeforeEach
    void setUp() {
        request = CustomerRegistrationRequest
                .builder()
                .email("boyomichaelbidemi@gmail.com")
                .password("test Password")
                .country("Nigeria")
                .firstName("Michael")
                .lastName("Boyo")
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        CustomerRegistrationResponse customerRegistrationResponse=
                customerService.register(request);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage())
                .isNotNull();
        assertThat(customerRegistrationResponse.getUserId())
                .isGreaterThan(0);
        assertThat(customerRegistrationResponse.getCode())
                .isEqualTo(201);
    }

    @Test
    void login() {
    }

    @Test
    void completeProfile() {
    }
}