package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerAPITest {

    @Autowired
    private TestRestTemplate template;

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
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void update_withValidPayload_shouldReturnStatusCode200() {
        CustomerDTO customerDTO = new CustomerDTO(1l, "Customer 1", "customer1@gmail.com", 12312312311l);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        ResponseEntity<CustomerDTO> response = template.postForEntity("/api/v1/customers/123", request, CustomerDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.getForEntity("/api/v1/customers/123", String.class);
        assertThat(response.getStatusCode()).isEqualTo(204);
    }
}
