package com.mulan.mulan_auto.repository;

import com.mulan.mulan_auto.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String username);

    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhone(String phone);

    @Query("SELECT c.id FROM Customer c WHERE c.username = :username")
    Long findCustomerIdByUsername(@Param("username") String username);
}
