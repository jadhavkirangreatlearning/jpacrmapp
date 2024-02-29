package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerServiceImpl.save(customer), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword) {
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmailId, custPassword));
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable long custId, @Valid @RequestBody Customer customer) {
        Customer customer1 = customerServiceImpl.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer ID Does Not Exist"));

        customer1.setCustPassword(customer.getCustPassword());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustName(customer.getCustName());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());
        customer1.setCustEmailId(customer.getCustEmailId());

        return new ResponseEntity<>(customerServiceImpl.update(customer1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable long custId) {
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @GetMapping("/findbycontactnumber/custContactNumber}")
    public ResponseEntity<Customer> findByContactNumber(@PathVariable long custContactNumber) {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> cust.getCustContactNumber() == custContactNumber).toList().get(0));
    }
}
