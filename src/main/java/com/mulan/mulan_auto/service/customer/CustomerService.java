package com.mulan.mulan_auto.service.customer;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.service.IGeneralService;

import java.util.List;

public interface CustomerService extends IGeneralService<Customer> {
    public Customer register(Customer customer);
    public List<KeyGen> ListOfRentedKeys (Long id);
    public String login(String username, String password);
}
