package br.com.rodrigofaria.awsrdssample.api;

import br.com.rodrigofaria.awsrdssample.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = new CustomerDTO(1l, customerDTO.name(), customerDTO.email(), customerDTO.cpf());
        return ResponseEntity.status(201).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return ResponseEntity.ok(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
