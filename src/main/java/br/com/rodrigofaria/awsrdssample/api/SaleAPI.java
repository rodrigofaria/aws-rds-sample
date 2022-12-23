package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
public class SaleAPI {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleDTO> listAll() {
        return saleService.listAll();
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@RequestBody SaleDTO saleDTO) {
        return ResponseEntity.status(201).body(saleService.save(saleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SaleDTO> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
