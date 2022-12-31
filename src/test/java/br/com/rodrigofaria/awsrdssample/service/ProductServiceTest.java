package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import br.com.rodrigofaria.awsrdssample.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void listAll_shouldReturnThreeProducts() {
        when(productRepository.findAll()).thenReturn(generateProductList());

        List<ProductDTO> productDTOList = productService.listAll();

        assertThat(productDTOList.size()).isEqualTo(3);
    }

    @Test
    public void save_withValidBody_shouldReturnProductSaved() {
        ProductDTO productDTO = FakeDataUtil.createProductDTO(12);
        Product product = FakeDataUtil.createProductEntity(12);

        doReturn(product).when(productRepository).save(Mockito.any(Product.class));

        ProductDTO response = productService.save(productDTO);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(product.getId());
        assertThat(response.getName()).isEqualTo(product.getName());
        assertThat(response.getBrand()).isEqualTo(product.getBrand());
        assertThat(response.getValue()).isEqualTo(product.getValue());
    }

    @Test
    public void update_withValidIdAndBody_shouldReturnProductUpdated() {
        ProductDTO productDTO = FakeDataUtil.createProductDTO(12);
        Product product = FakeDataUtil.createProductEntity(12);

        doReturn(Optional.of(product)).when(productRepository).findById(12l);
        doReturn(product).when(productRepository).save(Mockito.any(Product.class));

        ProductDTO response = productService.update(productDTO, 12l);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(product.getId());
        assertThat(response.getName()).isEqualTo(product.getName());
        assertThat(response.getBrand()).isEqualTo(product.getBrand());
        assertThat(response.getValue()).isEqualTo(product.getValue());
    }

    @Test
    public void update_withInvalidId_shouldThrowsResponseStatusException() {
        ProductDTO productDTO = FakeDataUtil.createProductDTO(12);

        doReturn(Optional.empty()).when(productRepository).findById(12l);

        Assertions.assertThrows(ResponseStatusException.class, () -> productService.update(productDTO, 12l));
    }

    @Test
    public void delete_withValidId_shouldReturnProductDeleted() {
        Product product = FakeDataUtil.createProductEntity(12);

        doReturn(Optional.of(product)).when(productRepository).findById(12l);

        ProductDTO response = productService.delete(12l);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(product.getId());
        assertThat(response.getName()).isEqualTo(product.getName());
        assertThat(response.getBrand()).isEqualTo(product.getBrand());
        assertThat(response.getValue()).isEqualTo(product.getValue());
    }

    @Test
    public void delete_withInvalidId_shouldThrowsResponseStatusException() {
        doReturn(Optional.empty()).when(productRepository).findById(1l);

        Assertions.assertThrows(ResponseStatusException.class, () -> productService.delete(1l));
    }

    @Test
    public void findById_withValidId_shouldReturnProduct() {
        Product product = FakeDataUtil.createProductEntity(12);

        doReturn(Optional.of(product)).when(productRepository).findById(12l);

        Product response = productService.findById(12l);
        assertThat(response.getId()).isEqualTo(product.getId());
        assertThat(response.getName()).isEqualTo(product.getName());
        assertThat(response.getBrand()).isEqualTo(product.getBrand());
        assertThat(response.getValue()).isEqualTo(product.getValue());
    }

    @Test
    public void findById_withInvalidId_shouldThrowsResponseStatusException() {
        doReturn(Optional.empty()).when(productRepository).findById(1l);

        Assertions.assertThrows(ResponseStatusException.class, () -> productService.findById(1l));
    }

    private List<Product> generateProductList() {
        List<Product> productList = new ArrayList<>();
        for (long i = 0; i < 3; i++) {
            productList.add(FakeDataUtil.createProductEntity(i));
        }

        return productList;
    }
}
