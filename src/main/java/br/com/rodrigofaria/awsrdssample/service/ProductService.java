package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import br.com.rodrigofaria.awsrdssample.mapper.ProductMapper;
import br.com.rodrigofaria.awsrdssample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> listAll() {
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.forEach(productEntity ->
            productDTOList.add(ProductMapper.entityToDto(productEntity))
        );

        return productDTOList;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.save(ProductMapper.dtoToEntity(productDTO));
        return ProductMapper.entityToDto(product);
    }

    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = findById(id);
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setValue(productDTO.getValue());
        return ProductMapper.entityToDto(productRepository.save(product));
    }

    public ProductDTO delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return ProductMapper.entityToDto(product);
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product with ID [" + id + "] not found."
            );
        }

        return product.get();
    }
}
