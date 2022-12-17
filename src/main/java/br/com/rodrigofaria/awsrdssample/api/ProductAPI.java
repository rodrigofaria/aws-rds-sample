package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductAPI {

    @GetMapping
    public List<ProductDTO> listAll() {
        return List.of(
                new ProductDTO(123l, "Nike Pro Dri-FIT", "Nike", 18.97),
                new ProductDTO(124l, "Nike Winflo 9", "Nike", 75.97),
                new ProductDTO(125l, "Jordan Air 200E\n", "Nike", 96.97)
        );
    }
}
