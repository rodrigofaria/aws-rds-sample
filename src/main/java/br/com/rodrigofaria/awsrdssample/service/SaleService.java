package br.com.rodrigofaria.awsrdssample.service;

import br.com.rodrigofaria.awsrdssample.dto.SaleDTO;
import br.com.rodrigofaria.awsrdssample.entity.Customer;
import br.com.rodrigofaria.awsrdssample.entity.Product;
import br.com.rodrigofaria.awsrdssample.entity.Sale;
import br.com.rodrigofaria.awsrdssample.mapper.CustomerMapper;
import br.com.rodrigofaria.awsrdssample.mapper.ProductMapper;
import br.com.rodrigofaria.awsrdssample.mapper.SaleMapper;
import br.com.rodrigofaria.awsrdssample.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    public List<SaleDTO> listAll() {
        Iterable<Sale> saleList = saleRepository.findAll();
        List<SaleDTO> saleDTOList = new ArrayList<>();
        saleList.forEach(saleEntity ->
            saleDTOList.add(SaleMapper.entityToDto(saleEntity))
        );

        return saleDTOList;
    }

    public SaleDTO save(SaleDTO saleDTO) {
        Customer customer = customerService.findById(saleDTO.getCustomerFk());
        Product product = productService.findById(saleDTO.getProductFk());

        Sale sale = saleRepository.save(
                SaleMapper.dtoToEntity(
                        saleDTO,
                        CustomerMapper.entityToDto(customer),
                        ProductMapper.entityToDto(product)
                )
        );
        return SaleMapper.entityToDto(sale);
    }

    public SaleDTO delete(Long id) {
        Sale sale = findById(id);
        saleRepository.delete(sale);
        return SaleMapper.entityToDto(sale);
    }

    private Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Sale with ID [" + id + "] not found."
            );
        }

        return sale.get();
    }
}
