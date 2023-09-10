package com.mulan.mulan_auto.service.customer;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.service.IGeneralService;

public interface CustomerService extends IGeneralService<Customer> {
    public Customer register(Customer customer);
}
