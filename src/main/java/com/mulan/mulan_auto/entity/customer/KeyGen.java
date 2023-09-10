package com.mulan.mulan_auto.entity.customer;

import com.mulan.mulan_auto.entity.IdBasedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class KeyGen extends IdBasedEntity {
    private String codeKey;
    private Float price;
    private Date startDate;
    private Date endDate;

    private String computerName;
    private String ipAddress;
    private String macAddress;

    @ManyToOne
    private Customer customers;
}
