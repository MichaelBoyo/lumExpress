package africa.semicolon.lumexpress.service.customerService;

import africa.semicolon.lumexpress.data.dto.request.*;
import africa.semicolon.lumexpress.data.dto.response.CompleteProfileResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import africa.semicolon.lumexpress.service.notification.EmailNotificationService;
import africa.semicolon.lumexpress.service.verificationTokenService.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailNotificationService emailNotificationService;
    private final VerificationTokenService verificationTokenService;
    private final ModelMapper mapper;

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) {
        Customer customer = mapper.map(registerRequest, Customer.class);
        customer.setCart(new Cart());
        setCustomerAddress(registerRequest, customer);
        Customer savedCustomer = customerRepository.save(customer);
//        sendEmail(customer);
        return registrationResponseBuilder(savedCustomer);
    }

    private void sendEmail(Customer customer) {
        String message = "";
        var token = verificationTokenService.createToken(customer.getEmail());
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/welcome.txt"));
        ) {
            message = String.format(bufferedReader.lines().collect(Collectors.joining()),
                    customer.getFirstName() + " " + customer.getLastName(),
                    "http://localhost:8080/api/v1/customer/verify/" + token.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }

        emailNotificationService.sendHtmlEmail(new EmailDetails(
                customer.getEmail(), message));
    }

    private void setCustomerAddress(CustomerRegistrationRequest registerRequest, Customer customer) {
        Address customerAddress = new Address();
        customerAddress.setCountry(registerRequest.getCountry());
        customer.getAddresses().add(customerAddress);
    }

    private CustomerRegistrationResponse registrationResponseBuilder(
            Customer customer) {
        return CustomerRegistrationResponse.builder()
                .addressId(0L)
                .message("success")
                .userId(customer.getId())
                .code(201)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public CompleteProfileResponse completeProfile(UpdateCustomerDetails updateCustomerDetails) {
        Customer customer = customerRepository.findById(updateCustomerDetails.getCustomerId())
                .orElseThrow(UserNotFoundException::new);
        mapper.map(updateCustomerDetails, customer);
        customer.getAddresses().stream().findFirst().ifPresent(address -> mapper.map(updateCustomerDetails,address));
        customerRepository.save(customer);
        return new CompleteProfileResponse("customer updated successfully");
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(GetAllCustomersRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPageNumber() - 1, request.getNumberOfCustomersPerPage());
        return customerRepository.findAll(pageable).map(customer -> mapper.map(customer, CustomerResponse.class));
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
