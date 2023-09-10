package com.mulan.mulan_auto.security.customer;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ICustomerRepo customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm người dùng theo tên người dùng
        Customer customer = customerRepository.findCustomerByUsername(username);
        
        if (customer == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với tên đăng nhập: " + username);
        }

        // Trả về một UserDetails được cấu hình dựa trên thông tin của người dùng
        return new User(customer.getUsername(), customer.getPassword(), Collections.emptyList()); // Bạn có thể tùy chỉnh quyền (roles) ở đây
    }
}