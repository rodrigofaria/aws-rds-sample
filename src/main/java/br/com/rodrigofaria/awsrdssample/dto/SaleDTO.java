package br.com.rodrigofaria.awsrdssample.dto;

import java.time.ZonedDateTime;

public class SaleDTO {

    private Long id;
    private Long customerFk;
    private Long productFk;
    private ZonedDateTime date;

    public SaleDTO() { }

    public SaleDTO(Long id, Long customerFk, Long productFk, ZonedDateTime date) {
        this.id = id;
        this.customerFk = customerFk;
        this.productFk = productFk;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerFk() {
        return customerFk;
    }

    public void setCustomerFk(Long customerFk) {
        this.customerFk = customerFk;
    }

    public Long getProductFk() {
        return productFk;
    }

    public void setProductFk(Long productFk) {
        this.productFk = productFk;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SaleDTO[" +
                "id=" + id + ", " +
                "customerFk=" + customerFk + ", " +
                "productFk=" + productFk + ", " +
                "date=" + date + ']';
    }
}
