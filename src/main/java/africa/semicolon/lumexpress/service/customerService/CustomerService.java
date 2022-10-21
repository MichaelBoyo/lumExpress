package africa.semicolon.lumexpress.service.customerService;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllCustomersRequest;
import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CompleteProfileResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest) throws UserNotFoundException;
    LoginResponse login(LoginRequest loginRequest);
    CompleteProfileResponse completeProfile(UpdateCustomerDetails updateCustomerDetails) throws UserNotFoundException;

    Page<CustomerResponse> getAllCustomers(GetAllCustomersRequest request);

    void deleteAll();
}
