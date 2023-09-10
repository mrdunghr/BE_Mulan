package com.mulan.mulan_auto.service.keygen;

import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.service.IGeneralService;

public interface KeyGenService extends IGeneralService<KeyGen> {
    public KeyGen generateRandom(KeyGen keyGen, Long idCustomer);
    public KeyGen keyExpirationDate(Long idKey);
}
