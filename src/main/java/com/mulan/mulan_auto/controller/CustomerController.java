package com.mulan.mulan_auto.controller;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.service.customer.CustomerService;
import com.mulan.mulan_auto.service.keygen.KeyGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<KeyGen> registerCustomer(@RequestBody KeyGen keyGen, @RequestParam Long idCustomer) {
        return new ResponseEntity<>(keyGenService.generateRandom(keyGen, idCustomer), HttpStatus.CREATED);
    }
}
