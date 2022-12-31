package br.com.rodrigofaria.awsrdssample.mapper;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {

    @Test
    public void dtoToEntity_shouldReturnEntityFilled() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(123);

        Customer customer = CustomerMapper.dtoToEntity(customerDTO);
        assertThat(customer.getId()).isEqualTo(customerDTO.getId());
        assertThat(customer.getName()).isEqualTo(customerDTO.getName());
        assertThat(customer.getEmail()).isEqualTo(customerDTO.getEmail());
        assertThat(customer.getCpf()).isEqualTo(customerDTO.getCpf());
    }

    @Test
    public void entityToDto_shouldReturnDtoFilled() {
        Customer customer = FakeDataUtil.createCustomerEntity(123);

        CustomerDTO customerDTO = CustomerMapper.entityToDto(customer);
        assertThat(customerDTO.getId()).isEqualTo(customer.getId());
        assertThat(customerDTO.getName()).isEqualTo(customer.getName());
        assertThat(customerDTO.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerDTO.getCpf()).isEqualTo(customer.getCpf());
    }
}
