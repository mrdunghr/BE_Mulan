package com.mulan.mulan_auto.controller;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.entity.customer.dto.LoginCustomer;
import com.mulan.mulan_auto.service.customer.CustomerService;
import com.mulan.mulan_auto.service.keygen.KeyGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private KeyGenService keyGenService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.register(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/key-gen")
    public ResponseEntity<KeyGen> keyGen(@RequestBody KeyGen keyGen, @RequestParam Long idCustomer) {
        return new ResponseEntity<>(keyGenService.generateRandom(keyGen, idCustomer), HttpStatus.CREATED);
    }

    @GetMapping("/key-gen")
    public ResponseEntity<List<KeyGen>> keyGen(@RequestParam Long idCustomer) {
        List<KeyGen> keyGenList = customerService.ListOfRentedKeys(idCustomer);
        return new ResponseEntity<>(keyGenList, HttpStatus.OK);
    }
    @GetMapping("/key-expiration-date")
    public ResponseEntity<KeyGen> keyExpirationDate(@RequestParam Long idKey) {
        KeyGen keyGen = keyGenService.keyExpirationDate(idKey);
        return new ResponseEntity<>(keyGen, HttpStatus.OK);
    }

    @GetMapping("/permission-key")
    public ResponseEntity<Boolean> checkThePermissionToUseTheKey(@RequestParam Long idKey) {
        Boolean keyGen = keyGenService.checkThePermissionToUseTheKey(idKey);
        return new ResponseEntity<>(keyGen, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCustomer customer) {
        String token = customerService.login(customer.getUsername(), customer.getPassword());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/check-active/{username}")
    public boolean checkActiveCustomer(@PathVariable String username) {
        return customerService.checkActiveCustomer(username);
    }

    @GetMapping("/{username}")
    public Long getIdForCustomer(@PathVariable String username) {
        return keyGenService.getIdCustomerByCustomerName(username);
    }
}
