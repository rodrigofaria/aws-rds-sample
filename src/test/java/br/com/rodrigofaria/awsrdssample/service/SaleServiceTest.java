package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.common.FakeDataUtil;
import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import br.com.rodrigofaria.awsrdssample.entity.Sale;
import br.com.rodrigofaria.awsrdssample.repository.SaleRepository;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SaleServiceTest {

    @MockBean
    private SaleRepository saleRepository;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Test
    public void listAll_shouldReturnTwoSales() {
        when(saleRepository.findAll()).thenReturn(generateSaleList());

        List<SaleDTO> saleDTOList = saleService.listAll();

        assertThat(saleDTOList.size()).isEqualTo(2);
    }

    @Test
    public void save_withValidBody_shouldReturnCustomerSaved() {
        Customer customer = new Customer();
        customer.setId(11l);

        Product product = new Product();
        product.setId(12l);

        SaleDTO saleDTO = FakeDataUtil.createSaleDTO(11, 12);
        Sale sale = FakeDataUtil.createSaleEntity(11, 12);

        doReturn(sale).when(saleRepository).save(Mockito.any(Sale.class));
        doReturn(customer).when(customerService).findById(11l);
        doReturn(product).when(productService).findById(12l);

        SaleDTO response = saleService.save(saleDTO);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(sale.getId());
        assertThat(response.getCustomerFk()).isEqualTo(customer.getId());
        assertThat(response.getProductFk()).isEqualTo(product.getId());
        assertThat(response.getDate()).isEqualTo(sale.getDate());
    }

    @Test
    public void save_withInvalidCustomerId_shouldThrowsResponseStatusException() {
        SaleDTO saleDTO = FakeDataUtil.createSaleDTO(11, 12);

        doThrow(ResponseStatusException.class).when(customerService).findById(11l);

        Assertions.assertThrows(ResponseStatusException.class, () -> saleService.save(saleDTO));
    }

    @Test
    public void save_withInvalidProductId_shouldThrowsResponseStatusException() {
        Customer customer = new Customer();
        customer.setId(11l);

        SaleDTO saleDTO = FakeDataUtil.createSaleDTO(11, 12);

        doReturn(customer).when(customerService).findById(11l);
        doThrow(ResponseStatusException.class).when(productService).findById(12l);

        Assertions.assertThrows(ResponseStatusException.class, () -> saleService.save(saleDTO));
    }

    @Test
    public void delete_withValidId_shouldReturnSaleDeleted() {
        Customer customer = new Customer();
        customer.setId(11l);

        Product product = new Product();
        product.setId(12l);

        Sale sale = FakeDataUtil.createSaleEntity(11, 12);

        doReturn(Optional.of(sale)).when(saleRepository).findById(23l);

        SaleDTO response = saleService.delete(23l);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(sale.getId());
        assertThat(response.getCustomerFk()).isEqualTo(customer.getId());
        assertThat(response.getProductFk()).isEqualTo(product.getId());
        assertThat(response.getDate()).isEqualTo(sale.getDate());
    }

    @Test
    public void delete_withInvalidId_shouldThrowsResponseStatusException() {
        doReturn(Optional.empty()).when(saleRepository).findById(1l);

        Assertions.assertThrows(ResponseStatusException.class, () -> saleService.delete(1l));
    }

    private List<Sale> generateSaleList() {
        List<Sale> saleList = new ArrayList<>();
        for (long i = 0; i < 2; i++) {
            saleList.add(FakeDataUtil.createSaleEntity(11, 12));
        }

        return saleList;
    }
}
