package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerAPITest extends APITest {

    private static final int QUANTITY_OF_CUSTOMER = 3;

    @Autowired
    private TestRestTemplate template;

    @BeforeAll
    public void setup() {
        createCustomer(template, QUANTITY_OF_CUSTOMER);
    }

    @AfterAll
    public void tearDown() {
        deleteAllCustomers(template);
    }

    @Test
    public void listAll_shouldReturnListWith3Customers() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/customers", List.class);
        assertThat(response.getBody().size()).isEqualTo(QUANTITY_OF_CUSTOMER);
    }

    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(1);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        ResponseEntity<CustomerDTO> response = template.postForEntity("/api/v1/customers", request, CustomerDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void update_withValidPayload_shouldReturnStatusCode200() {
        CustomerDTO customerDTO = FakeDataUtil.createCustomerDTO(10);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        ResponseEntity<CustomerDTO> response = template.exchange("/api/v1/customers/1", HttpMethod.PUT, request, CustomerDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo(customerDTO.getName());
        assertThat(response.getBody().getEmail()).isEqualTo(customerDTO.getEmail());
        assertThat(response.getBody().getCpf()).isEqualTo(customerDTO.getCpf());
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.exchange("/api/v1/customers/1", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }
}
