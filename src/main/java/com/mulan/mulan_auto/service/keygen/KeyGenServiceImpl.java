package com.mulan.mulan_auto.service.keygen;

import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.repository.ICustomerRepo;
import com.mulan.mulan_auto.repository.IKeyGenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class KeyGenServiceImpl implements KeyGenService {
    @Autowired
    private IKeyGenRepo keyGenRepo;
    @Autowired
    private ICustomerRepo customerRepo;

    @Override
    public Iterable<KeyGen> findAll() {
        return null;
    }

    @Override
    public Optional<KeyGen> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public KeyGen save(KeyGen keyGen) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public KeyGen generateRandom(KeyGen keyGen, Long idCustomer) {
        Customer customer = customerRepo.findById(idCustomer).orElseThrow(() ->
                new EntityNotFoundException("Không tìm thất Customer với ID " + idCustomer));

        // kiểm tra tài khoản
        if (customer.getMoney() < 50000.0f) {
            throw new RuntimeException("Tài khoản của bạn không đủ để thuê key.");
        }

        KeyGenManager keyGenManager = new KeyGenManager();
        InfoCustomerKey infoCustomerKey = new InfoCustomerKey();

        keyGen.setCodeKey(keyGenManager.generateRandomKey(50));
        keyGen.setPrice(50000.0f);
        keyGen.setStartDate(new Date());

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();

        // Thêm 30 ngày
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        keyGen.setEndDate(calendar.getTime());
        keyGen.setComputerName(infoCustomerKey.getComputerName());
        keyGen.setIpAddress(infoCustomerKey.getIpAddress());
        keyGen.setMacAddress(infoCustomerKey.getMacAddress());
        keyGen.setCustomers(customer);

        // trừ tiền từ tài khoản của người dùng
        float newBalance = customer.getMoney() - keyGen.getPrice();
        customer.setMoney(newBalance);
        customerRepo.save(customer);

        return keyGenRepo.save(keyGen);
    }

    @Override
    public Long getIdCustomerByCustomerName(String customerName) {
        return customerRepo.findCustomerIdByUsername(customerName);
    }

    public KeyGen keyExpirationDate(Long idKey) {
        KeyGen keyGen = keyGenRepo.findById(idKey).orElseThrow(() ->
                new RuntimeException("Không tìm thấy key với id " + idKey));
        if (keyGen == null) {
            throw new RuntimeException("Không tìm thấy key với id " + idKey);
        } else {
            // lấy ngày hết hạn của key
            Date expirationDate = keyGen.getEndDate();

            // kiểm tra ngày hết hạn của key
            Date currentDate = new Date(); // ngày hiện tại
            if (expirationDate != null && currentDate.after(expirationDate)) {
                throw new RuntimeException("Key đã hết hạn.");
            }
            return keyGen;
        }
    }

    public boolean checkThePermissionToUseTheKey(Long idKey) {
        KeyGen keyGen = keyGenRepo.findById(idKey).orElseThrow(() ->
                new RuntimeException("Không tìm thấy key với id " + idKey));

        InfoCustomerKey infoCustomerKey = new InfoCustomerKey();

        if (keyGen.getComputerName().equals(infoCustomerKey.getComputerName()) &&
                keyGen.getIpAddress().equals(infoCustomerKey.getIpAddress())) {
            return true;
        } else {
            throw new SecurityException("Bạn không có quyền sử dụng key này");
        }
    }
}
