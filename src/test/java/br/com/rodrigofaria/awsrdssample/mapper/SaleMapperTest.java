package br.com.rodrigofaria.awsrdssample.mapper;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.entity.Sale;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleMapperTest {

    @Test
    public void dtoToEntity_shouldReturnEntityFilled() {
        SaleDTO saleDTO = FakeDataUtil.createSaleDTO(10, 11);

        Sale sale = SaleMapper.dtoToEntity(
                saleDTO,
                FakeDataUtil.createCustomerDTO(10),
                FakeDataUtil.createProductDTO(11)
        );
        assertThat(sale.getId()).isEqualTo(saleDTO.getId());
        assertThat(sale.getCustomer().getId()).isEqualTo(saleDTO.getCustomerFk());
        assertThat(sale.getProduct().getId()).isEqualTo(saleDTO.getProductFk());
        assertThat(sale.getDate()).isEqualTo(saleDTO.getDate());
    }

    @Test
    public void entityToDto_shouldReturnDtoFilled() {
        Sale sale = FakeDataUtil.createSaleEntity(10, 11);

        SaleDTO saleDTO = SaleMapper.entityToDto(sale);
        assertThat(saleDTO.getId()).isEqualTo(sale.getId());
        assertThat(saleDTO.getCustomerFk()).isEqualTo(sale.getCustomer().getId());
        assertThat(saleDTO.getProductFk()).isEqualTo(sale.getProduct().getId());
        assertThat(saleDTO.getDate()).isEqualTo(sale.getDate());
    }
}
