package br.com.rodrigofaria.awsrdssample.mapper;

import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.entity.Product;

public interface ProductMapper {

    static Product dtoToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getBrand(), productDTO.getValue());
    }

    static ProductDTO entityToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getBrand(), product.getValue());
    }
}
