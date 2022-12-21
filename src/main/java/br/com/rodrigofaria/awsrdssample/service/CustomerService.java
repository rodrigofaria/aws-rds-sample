package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import br.com.rodrigofaria.awsrdssample.mapper.CustomerMapper;
import br.com.rodrigofaria.awsrdssample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> listAll() {
        Iterable<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerList.forEach(customerEntity ->
                customerDTOList.add(CustomerMapper.entityToDto(customerEntity))
        );

        return customerDTOList;
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(CustomerMapper.dtoToEntity(customerDTO));
        return CustomerMapper.entityToDto(customer);
    }

    public CustomerDTO update(CustomerDTO customerDTO, Long id) {
        Customer customer = findById(id);
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCpf(customerDTO.getCpf());
        return CustomerMapper.entityToDto(customerRepository.save(customer));
    }

    public CustomerDTO delete(Long id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
        return CustomerMapper.entityToDto(customer);
    }

    private Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer with ID [" + id + "] not found."
            );
        }

        return customer.get();
    }
}
