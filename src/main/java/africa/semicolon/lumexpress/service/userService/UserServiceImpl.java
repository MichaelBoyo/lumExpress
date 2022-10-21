package africa.semicolon.lumexpress.service.userService;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.Admin;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.models.LumExpressUser;
import africa.semicolon.lumexpress.data.models.Vendor;
import africa.semicolon.lumexpress.data.repositories.AdminRepository;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.data.repositories.VendorRepository;
import africa.semicolon.lumexpress.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public LumExpressUser getUserByEmail(String email) throws UserNotFoundException {
        Optional<Admin> user = adminRepository.findByEmail(email);
        if (user.isPresent()) return user.get();
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) return customer.get();
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);
        if (vendor.isPresent()) return vendor.get();
        throw new UserNotFoundException("User not found");
    }
}
