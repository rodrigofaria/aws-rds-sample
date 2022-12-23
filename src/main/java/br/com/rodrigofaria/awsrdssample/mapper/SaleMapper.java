package br.com.rodrigofaria.awsrdssample.mapper;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.entity.Sale;

public interface SaleMapper {

    static Sale dtoToEntity(SaleDTO saleDTO, CustomerDTO customerDTO, ProductDTO productDTO) {
        return new Sale(
                saleDTO.getId(),
                CustomerMapper.dtoToEntity(customerDTO),
                ProductMapper.dtoToEntity(productDTO),
                saleDTO.getDate()
        );
    }

    static SaleDTO entityToDto(Sale sale) {
        return new SaleDTO(
                sale.getId(),
                sale.getCustomer().getId(),
                sale.getProduct().getId(),
                sale.getDate()
        );
    }
}
