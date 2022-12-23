package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import org.junit.jupiter.api.Test;
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
        assertThat(response.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void delete_withValidId_shouldReturnStatusCode204() {
        ResponseEntity<String> response = template.exchange("/api/v1/sales/12", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    private SaleDTO createSaleDto() {
        return new SaleDTO(1234l, 1l, 1l, ZonedDateTime.of(2022, 10, 20, 10, 25, 19, 0, ZoneId.systemDefault()));
    }
}
