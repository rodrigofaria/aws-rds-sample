package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductAPITest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void listAll_shouldReturnListWith3Products() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/products", List.class);
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        ProductDTO productDTO = new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        ResponseEntity<ProductDTO> response = template.postForEntity("/api/v1/products", request, ProductDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void update_withValidPayload_shouldReturnStatusCode200() {
        ProductDTO productDTO = new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        ResponseEntity<ProductDTO> response = template.postForEntity("/api/v1/products/123", request, ProductDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.getForEntity("/api/v1/products/123", String.class);
        assertThat(response.getStatusCode()).isEqualTo(204);
    }
}
