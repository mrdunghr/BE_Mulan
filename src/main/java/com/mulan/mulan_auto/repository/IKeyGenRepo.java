package com.mulan.mulan_auto.repository;

import com.mulan.mulan_auto.entity.customer.KeyGen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKeyGenRepo extends JpaRepository<KeyGen, Long> {
}
