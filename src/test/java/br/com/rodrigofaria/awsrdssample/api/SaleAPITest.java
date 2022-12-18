package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleAPITest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void listAll_shouldReturnListWith2Sales() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/sales", List.class);
        assertThat(response.getBody().size()).isEqualTo(2);
    }


    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        SaleDTO saleDTO = createSaleDto();
        HttpEntity<SaleDTO> request = new HttpEntity<>(saleDTO);
        ResponseEntity<SaleDTO> response = template.postForEntity("/api/v1/sales", request, SaleDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.getForEntity("/api/v1/sales/123", String.class);
        assertThat(response.getStatusCode()).isEqualTo(204);
    }

    private SaleDTO createSaleDto() {
        CustomerDTO c1 = new CustomerDTO(1l, "Customer 1", "customer1@gmail.com", 12312312311l);
        ProductDTO p1 = new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97);
        return new SaleDTO(1234l, c1, p1, ZonedDateTime.of(2022, 10, 20, 10, 25, 19, 0, ZoneId.systemDefault()));
    }
}
