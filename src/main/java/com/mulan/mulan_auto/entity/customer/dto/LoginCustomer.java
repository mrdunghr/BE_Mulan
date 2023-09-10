package com.mulan.mulan_auto.entity.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCustomer {
    private String username;
    private String password;
}
