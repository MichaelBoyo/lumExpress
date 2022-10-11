package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllCustomersRequest;
import africa.semicolon.lumexpress.service.customerService.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CustomerRegistrationRequest request){
        return ResponseEntity.ok(customerService.register(request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(@RequestBody GetAllCustomersRequest request){
        return ResponseEntity.ok(customerService.getAllCustomers(request));
    }
}
