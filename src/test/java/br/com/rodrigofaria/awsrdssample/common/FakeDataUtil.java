package br.com.rodrigofaria.awsrdssample.common;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import br.com.rodrigofaria.awsrdssample.entity.Sale;

import java.time.ZonedDateTime;

public class FakeDataUtil {

    public static CustomerDTO createCustomerDTO(long identification) {
        return new CustomerDTO(
                identification,
                "Customer_" + identification,
                "customer" + identification + "@gmail.com",
                123123123 + identification);

    }

    public static ProductDTO createProductDTO(long identification) {
        return new ProductDTO(
                identification,
                "Tenis_" + identification,
                "Brand X",
                18.97 + identification);
    }

    public static SaleDTO createSaleDTO(long customerId, long productId) {
        return new SaleDTO(customerId + productId,
                customerId,
                productId,
                ZonedDateTime.now());
    }

    public static Customer createCustomerEntity(long identification) {
        return new Customer(
                identification,
                "Customer_" + identification,
                "customer" + identification + "@gmail.com",
                123123123 + identification);

    }

    public static Product createProductEntity(long identification) {
        return new Product(
                identification,
                "Tenis_" + identification,
                "Brand X",
                18.97 + identification);
    }

    public static Sale createSaleEntity(long customerId, long productId) {
        return new Sale(customerId + productId,
                FakeDataUtil.createCustomerEntity(customerId),
                FakeDataUtil.createProductEntity(productId),
                ZonedDateTime.now());
    }
}
