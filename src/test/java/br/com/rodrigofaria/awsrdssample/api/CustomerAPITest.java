package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
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
public class CustomerAPITest {

    @Autowired
    private TestRestTemplate template;

    @BeforeAll
    public void setup() {
        for (long id = 1; id <= 3; id++) {
            CustomerDTO customerDTO = new CustomerDTO(
                    null,
                    "Customer_" + id,
                    "customer" + id + "@gmail.com",
                    123123123 + id);
            HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
            template.postForEntity("/api/v1/customers", request, CustomerDTO.class);
        }
    }

    @Test
    public void listAll_shouldReturnListWith3Customers() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/customers", List.class);
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        CustomerDTO customerDTO = new CustomerDTO(1l, "Customer 1", "customer1@gmail.com", 12312312311l);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        ResponseEntity<CustomerDTO> response = template.postForEntity("/api/v1/customers", request, CustomerDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void update_withValidPayload_shouldReturnStatusCode200() {
        CustomerDTO customerDTO = new CustomerDTO(1l, "Customer1_edit", "customer1_edit@gmail.com", 123123l);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        ResponseEntity<CustomerDTO> response = template.exchange("/api/v1/customers/1", HttpMethod.PUT, request, CustomerDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("Customer1_edit");
        assertThat(response.getBody().getEmail()).isEqualTo("customer1_edit@gmail.com");
        assertThat(response.getBody().getCpf()).isEqualTo(123123l);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.exchange("/api/v1/customers/1", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }
}
