package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import br.com.rodrigofaria.awsrdssample.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    private static final int QUANTITY_OF_CUSTOMER = 3;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Test
    public void listAll_shouldReturnThreeCustomers() {
        when(customerRepository.findAll()).thenReturn(generateCustomerList());

        List<CustomerDTO> customerDTOList = customerService.listAll();

        assertThat(customerDTOList.size()).isEqualTo(QUANTITY_OF_CUSTOMER);
    }

    @Test
    public void save_withValidBody_shouldReturnCustomerSaved() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(12);
        Customer customer = FakeDataUtil.createCustomerEntity(12);

        doReturn(customer).when(customerRepository).save(Mockito.any(Customer.class));

        CustomerDTO response = customerService.save(customerDTO);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getName()).isEqualTo(customer.getName());
        assertThat(response.getEmail()).isEqualTo(customer.getEmail());
        assertThat(response.getCpf()).isEqualTo(customer.getCpf());
    }

    @Test
    public void update_withValidIdAndBody_shouldReturnCustomerUpdated() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(12);
        Customer customer = FakeDataUtil.createCustomerEntity(12);

        doReturn(Optional.of(customer)).when(customerRepository).findById(12l);
        doReturn(customer).when(customerRepository).save(Mockito.any(Customer.class));

        CustomerDTO response = customerService.update(customerDTO, 12l);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getName()).isEqualTo(customer.getName());
        assertThat(response.getEmail()).isEqualTo(customer.getEmail());
        assertThat(response.getCpf()).isEqualTo(customer.getCpf());
    }

    @Test
    public void update_withInvalidId_shouldThrowsResponseStatusException() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(12);

        doReturn(Optional.empty()).when(customerRepository).findById(12l);

        Assertions.assertThrows(ResponseStatusException.class, () -> customerService.update(customerDTO, 12l));
    }

    @Test
    public void delete_withValidId_shouldReturnCustomerDeleted() {
        Customer customer = FakeDataUtil.createCustomerEntity(12);

        doReturn(Optional.of(customer)).when(customerRepository).findById(12l);

        CustomerDTO response = customerService.delete(12l);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getName()).isEqualTo(customer.getName());
        assertThat(response.getEmail()).isEqualTo(customer.getEmail());
        assertThat(response.getCpf()).isEqualTo(customer.getCpf());
    }

    @Test
    public void delete_withInvalidId_shouldThrowsResponseStatusException() {
        doReturn(Optional.empty()).when(customerRepository).findById(1l);

        Assertions.assertThrows(ResponseStatusException.class, () -> customerService.delete(1l));
    }

    @Test
    public void findById_withValidId_shouldReturnCustomer() {
        Customer customer = FakeDataUtil.createCustomerEntity(12);

        doReturn(Optional.of(customer)).when(customerRepository).findById(12l);

        Customer response = customerService.findById(12l);
        assertThat(response.getId()).isEqualTo(customer.getId());
        assertThat(response.getName()).isEqualTo(customer.getName());
        assertThat(response.getEmail()).isEqualTo(customer.getEmail());
        assertThat(response.getCpf()).isEqualTo(customer.getCpf());
    }

    @Test
    public void findById_withInvalidId_shouldThrowsResponseStatusException() {
        doReturn(Optional.empty()).when(customerRepository).findById(1l);

        Assertions.assertThrows(ResponseStatusException.class, () -> customerService.findById(1l));
    }

    private List<Customer> generateCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        for (long i = 0; i < QUANTITY_OF_CUSTOMER; i++) {
            customerList.add(FakeDataUtil.createCustomerEntity(i));
        }

        return customerList;
    }
}
