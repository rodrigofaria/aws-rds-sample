package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaleAPITest extends APITest {

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

    @AfterAll
    public void tearDown() {
        deleteAllSales(template);
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
        long customerId = 0;
        createCustomer(template, (int) id);
        CustomerDTO[] response = template.getForObject("/api/v1/customers", CustomerDTO[].class);
        if (response.length > 0) {
            customerId = response[0].getId();
        }

        long productId = 0;
        createProduct(template, (int) id);
        ProductDTO[] response2 = template.getForObject("/api/v1/products", ProductDTO[].class);
        if (response2.length > 0) {
            productId = response2[0].getId();
        }

        return new SaleDTO(null, customerId, productId, ZonedDateTime.of(2022, 10, 20, 10, 25, 19, 0, ZoneId.systemDefault()));
    }
}
