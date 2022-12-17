package br.com.rodrigofaria.awsrdssample.dto;

import java.time.ZonedDateTime;

public record SaleDTO(Long id, CustomerDTO customer, ProductDTO product, ZonedDateTime date) {
}
