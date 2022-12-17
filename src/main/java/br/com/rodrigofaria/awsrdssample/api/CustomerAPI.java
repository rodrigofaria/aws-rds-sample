package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerAPI {

    @GetMapping
    public List<CustomerDTO> listAll() {
        return List.of(
                new CustomerDTO(1l, "Customer 1", "customer1@gmail.com", 12312312311l),
                new CustomerDTO(2l, "Customer 2", "customer2@gmail.com", 12312312322l),
                new CustomerDTO(3l, "Customer 3", "customer3@gmail.com", 12312312333l)
                );
    }
}
