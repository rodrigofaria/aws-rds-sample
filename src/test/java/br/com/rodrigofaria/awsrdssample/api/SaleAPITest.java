package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaleAPITest {

    @Autowired
    private TestRestTemplate template;

    @BeforeAll
    public void setup() {
        for (long id = 1; id <= 2; id++) {
            SaleDTO saleDTO = createSaleDto(id);
            HttpEntity<SaleDTO> request = new HttpEntity<>(saleDTO);
            template.postForEntity("/api/v1/sales", request, SaleDTO.class);
        }
    }

    @Test
    public void listAll_shouldReturnListWith2Sales() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/sales", List.class);
        assertThat(response.getBody().size()).isEqualTo(2);
    }

    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        SaleDTO saleDTO = createSaleDto(3);
        HttpEntity<SaleDTO> request = new HttpEntity<>(saleDTO);
        ResponseEntity<SaleDTO> response = template.postForEntity("/api/v1/sales", request, SaleDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.exchange("/api/v1/sales/1", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    private SaleDTO createSaleDto(long id) {
        createCustomer(id);
        createProduct(id);
        return new SaleDTO(id, id, id, ZonedDateTime.of(2022, 10, 20, 10, 25, 19, 0, ZoneId.systemDefault()));
    }

    private void createCustomer(long id) {
        CustomerDTO customerDTO = new CustomerDTO(
                null,
                "Customer_" + id,
                "customer" + id + "@gmail.com",
                123123123 + id);
        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
        template.postForEntity("/api/v1/customers", request, CustomerDTO.class);
    }

    private void createProduct(long id) {
        ProductDTO productDTO = new ProductDTO(
                null,
                "Tenis_" + id,
                "Brand X",
                18.97 + id);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        template.postForEntity("/api/v1/products", request, ProductDTO.class);
    }
}
