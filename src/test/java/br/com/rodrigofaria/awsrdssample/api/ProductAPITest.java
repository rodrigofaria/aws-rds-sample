package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
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
public class ProductAPITest extends APITest {

    private static final int QUANTITY_OF_PRODUCT = 3;

    @Autowired
    private TestRestTemplate template;

    @BeforeAll
    public void setup() {
        createProduct(template, QUANTITY_OF_PRODUCT);
    }

    @AfterAll
    public void tearDown() {
        deleteAllProducts(template);
    }

    @Test
    public void listAll_shouldReturnListWith3Products() {
        ResponseEntity<List> response = template.getForEntity("/api/v1/products", List.class);
        assertThat(response.getBody().size()).isEqualTo(QUANTITY_OF_PRODUCT);
    }

    @Test
    public void save_withValidPayload_shouldReturnStatusCode201() {
        ProductDTO productDTO = new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        ResponseEntity<ProductDTO> response = template.postForEntity("/api/v1/products", request, ProductDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void update_withValidPayload_shouldReturnStatusCode200() {
        ProductDTO productDTO = new ProductDTO(1l, "Nike Pro Dri-FIT", "Nike", 18.97);
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
        ResponseEntity<ProductDTO> response = template.exchange("/api/v1/products/1", HttpMethod.PUT, request, ProductDTO.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody().getName()).isEqualTo("Nike Pro Dri-FIT");
        assertThat(response.getBody().getBrand()).isEqualTo("Nike");
        assertThat(response.getBody().getValue()).isEqualTo(18.97);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.exchange("/api/v1/products/2", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }
}
