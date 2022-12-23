package br.com.rodrigofaria.awsrdssample.repository;

import br.com.rodrigofaria.awsrdssample.entity.Sale;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Long> {
}
