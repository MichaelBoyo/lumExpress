package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
        Customer customer = Customer.builder()
                .cart(new Cart())
                .addresses(new HashSet<>())
                .build();
        customer.setEmail("test@mail.com");
        customerRepository.save(customer);
    }

    @Test
    void findByEmail() {
        assertThat(customerRepository.findByEmail("test@mail.com")).isNotNull();

    }
}