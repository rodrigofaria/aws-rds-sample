package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
public class SaleAPI {

    @GetMapping
    public List<SaleDTO> listAll() {
        CustomerDTO c1 = new CustomerDTO(1l, "Customer 1", "customer1@gmail.com", 12312312311l);
        ProductDTO p1 = new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97);
        return List.of(
                new SaleDTO(1234l, c1, p1, ZonedDateTime.of(2022, 10, 20, 10, 25, 19, 0, ZoneId.systemDefault())),
                new SaleDTO(1234l, c1, p1, ZonedDateTime.of(2022, 10, 23, 10, 25, 19, 0, ZoneId.systemDefault()))
        );
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@RequestBody SaleDTO saleDTO) {
        SaleDTO sale = new SaleDTO(12l, saleDTO.customer(), saleDTO.product(), saleDTO.date());
        return ResponseEntity.status(201).body(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleDTO> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
