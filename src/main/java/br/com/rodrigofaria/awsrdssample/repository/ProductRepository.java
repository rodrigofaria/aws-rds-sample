package br.com.rodrigofaria.awsrdssample.repository;

import br.com.rodrigofaria.awsrdssample.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
