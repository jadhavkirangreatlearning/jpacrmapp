package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepo customerRepoImpl;

    public Customer save(Customer customer) {
        return customerRepoImpl.save(customer);
    }

    public boolean signIn(String custEmailId, String custPassword) {
        boolean flag = false;

        Customer customer = customerRepoImpl.findByCustEmailIdAndCustPassword(custEmailId, custPassword);

        if (customer.getCustEmailId().equals(custEmailId) && customer.getCustPassword().equals(custPassword)) {
            flag = true;
        }

        return flag;
    }

    public Optional<Customer> findById(long custId) {
        return customerRepoImpl.findById(custId);
    }

    public List<Customer> findAll() {
        return customerRepoImpl.findAll();
    }

    public Customer update(Customer customer) {
        return customerRepoImpl.save(customer);
    }

    public void deleteById(long custId) {
        customerRepoImpl.deleteById(custId);
    }
}
