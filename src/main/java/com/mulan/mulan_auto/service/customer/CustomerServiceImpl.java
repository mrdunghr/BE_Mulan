package com.mulan.mulan_auto.service.customer;

import com.mulan.mulan_auto.entity.AuthenticationType;
import com.mulan.mulan_auto.entity.customer.Customer;
import com.mulan.mulan_auto.entity.customer.KeyGen;
import com.mulan.mulan_auto.repository.ICustomerRepo;
import com.mulan.mulan_auto.security.customer.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    public Customer register(Customer customer) {
        ValidationUtil.validateUsername(customer.getUsername());
        ValidationUtil.validatePassword(customer.getPassword());
        ValidationUtil.validatePhone(customer.getPhone());
        ValidationUtil.validateEmail(customer.getEmail());
        Customer userNameExists = customerRepo.findCustomerByUsername(customer.getUsername());
        if (userNameExists != null) {
            throw new RuntimeException("Tên người dùng đã tồn tại: " + userNameExists.getUsername());
        }

        Customer emailExists = customerRepo.findCustomerByEmail(customer.getEmail());
        if (emailExists != null) {
            throw new RuntimeException("Email này đã tồn tại: " + emailExists.getEmail());
        }

        Customer phoneExists = customerRepo.findCustomerByPhone(customer.getPhone());
        if (phoneExists != null) {
            throw new RuntimeException("Số điện thoại này đã tồn tại: " + phoneExists.getPhone());
        }

        customer.setOtp("");
        customer.setEnabled(false);
        customer.setCreatedTime(new Date());
        customer.setMoney(0.0f);
        customer.setAuthenticationType(AuthenticationType.DATABASE);
        customer.setKeyGens(null);
        return customerRepo.save(customer);
    }

    @Override
    public List<KeyGen> ListOfRentedKeys(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Không thấy khách hàng này với id " + id));
        return customer.getKeyGens();
    }

    public String login(String username, String password) {
        // Kiểm tra tên đăng nhập và mật khẩu của khách hàng ở đây.
        // Nếu thông tin đúng, tạo mã JWT và trả về.

        Customer customer = customerRepo.findCustomerByUsername(username);

        if (customer != null && password.equals(customer.getPassword())) {
            return jwtTokenProvider.createToken(customer); // Tạo mã JWT
        } else {
            throw new RuntimeException("Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }
}
