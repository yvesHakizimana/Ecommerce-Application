package com.rca.ecommerce.customer;

import com.rca.ecommerce.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest){
        //Find the Customer by ID or throw an exception saying that the customer is not found.
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer:: No Customer found with the provided ID:: %s ", customerRequest.id())));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }
    //For Checking if the request we are receiving is not bringing null Values so as when we update will bring some inconsistencies
    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName()))
            customer.setFirstName(customerRequest.firstName());
        if(StringUtils.isNotBlank(customerRequest.lastName()))
            customer.setLastName(customerRequest.lastName());
        if(StringUtils.isNotBlank(customerRequest.email()))
            customer.setEmail(customerRequest.email());
        if(customerRequest.address() != null)
            customer.setAddress(customerRequest.address());
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }
}
