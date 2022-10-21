package africa.semicolon.lumexpress.service.customerService;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllCustomersRequest;
import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.request.UpdateCustomerDetails;
import africa.semicolon.lumexpress.data.dto.response.CompleteProfileResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerRegistrationResponse;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    CompleteProfileResponse completeProfile(UpdateCustomerDetails updateCustomerDetails);

    Page<CustomerResponse> getAllCustomers(GetAllCustomersRequest request);

    void deleteAll();
}
