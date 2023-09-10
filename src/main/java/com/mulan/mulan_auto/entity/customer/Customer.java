package com.mulan.mulan_auto.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mulan.mulan_auto.entity.AuthenticationType;
import com.mulan.mulan_auto.entity.IdBasedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends IdBasedEntity {
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String phone;

    private String otp;
    private boolean enabled;
    private Date createdTime;
    private Float money;

    @Enumerated(EnumType.STRING)
    private AuthenticationType authenticationType;

    @OneToMany(mappedBy = "customers")
    @JsonIgnore
    private List<KeyGen> keyGens;

}
