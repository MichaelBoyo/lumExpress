package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllCustomersRequest;
import africa.semicolon.lumexpress.service.customerService.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerRegistrationRequest request){
        log.info("body ---> {}",request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(@RequestBody GetAllCustomersRequest request){
        return ResponseEntity.ok(customerService.getAllCustomers(request));
    }


}
