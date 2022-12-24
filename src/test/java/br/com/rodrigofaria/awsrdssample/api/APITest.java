package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import br.com.rodrigofaria.awsrdssample.dto.ProductDTO;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class APITest {

    public void createCustomer(TestRestTemplate template, int quantity) {
        for (long id = 1; id <= quantity; id++) {
            CustomerDTO customerDTO = new CustomerDTO(
                    null,
                    "Customer_" + id,
                    "customer" + id + "@gmail.com",
                    123123123 + id);
            HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO);
            template.postForEntity("/api/v1/customers", request, CustomerDTO.class);
        }
    }

    public void createProduct(TestRestTemplate template, int quantity) {
        for (long id = 1; id <= quantity; id++) {
            ProductDTO productDTO = new ProductDTO(
                    null,
                    "Tenis_" + id,
                    "Brand X",
                    18.97 + id);
            HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO);
            template.postForEntity("/api/v1/products", request, ProductDTO.class);
        }
    }

    public void deleteAllCustomers(TestRestTemplate template) {
        CustomerDTO[] response = template.getForObject("/api/v1/customers", CustomerDTO[].class);
        if (response.length > 0) {
            for (CustomerDTO customerDTO : response) {
                template.exchange("/api/v1/customers/" + customerDTO.getId(), HttpMethod.DELETE, null, String.class);
            }
        }
    }

    public void deleteAllProducts(TestRestTemplate template) {
        ProductDTO[] response = template.getForObject("/api/v1/products", ProductDTO[].class);
        if (response.length > 0) {
            for (ProductDTO productDTO : response) {
                template.exchange("/api/v1/products/" + productDTO.getId(), HttpMethod.DELETE, null, String.class);
            }
        }
    }

    public void deleteAllSales(TestRestTemplate template) {
        SaleDTO[] response = template.getForObject("/api/v1/sales", SaleDTO[].class);
        if (response.length > 0) {
            for (SaleDTO saleDTO : response) {
                template.exchange("/api/v1/sales/" + saleDTO.getId(), HttpMethod.DELETE, null, String.class);
            }

            deleteAllCustomers(template);
            deleteAllProducts(template);
        }
    }
}
