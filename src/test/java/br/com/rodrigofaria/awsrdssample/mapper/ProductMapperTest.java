package br.com.rodrigofaria.awsrdssample.mapper;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    @Test
    public void dtoToEntity_shouldReturnEntityFilled() {
        ProductDTO productDTO = FakeDataUtil.createProductDTO(12);

        Product product = ProductMapper.dtoToEntity(productDTO);
        assertThat(product.getId()).isEqualTo(productDTO.getId());
        assertThat(product.getName()).isEqualTo(productDTO.getName());
        assertThat(product.getBrand()).isEqualTo(productDTO.getBrand());
        assertThat(product.getValue()).isEqualTo(productDTO.getValue());
    }

    @Test
    public void entityToDto_shouldReturnDtoFilled() {
        Product product = FakeDataUtil.createProductEntity(12);

        ProductDTO productDTO = ProductMapper.entityToDto(product);
        assertThat(productDTO.getId()).isEqualTo(product.getId());
        assertThat(productDTO.getName()).isEqualTo(product.getName());
        assertThat(productDTO.getBrand()).isEqualTo(product.getBrand());
        assertThat(productDTO.getValue()).isEqualTo(product.getValue());
    }
}
