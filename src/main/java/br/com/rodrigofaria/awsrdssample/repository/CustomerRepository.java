package br.com.rodrigofaria.awsrdssample.repository;

import br.com.rodrigofaria.awsrdssample.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
