package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
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
    CustomerRegistrationResponse customerRegistrationResponse;


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

        customerRegistrationResponse =
                customerService.register(request);
    }

    @AfterEach
    void tearDown() {
//        customerService.deleteAll();
    }



    @Test
    void register() {

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
    void updateProfile() {
        UpdateCustomerDetails updateCustomerDetails = UpdateCustomerDetails
                .builder()
                .customerId(customerRegistrationResponse.getUserId())
                .phoneNumber("08103297538")
                .imageUrl("https://cloudinaty.com/nonsense")
                .buildingNumber(10)
                .street("Emily Akinola")
                .city("Lagos")
                .state("Lagos")
                .build();
        var response = customerService.completeProfile(updateCustomerDetails);

        assertThat(response).isNotNull();
    }
}