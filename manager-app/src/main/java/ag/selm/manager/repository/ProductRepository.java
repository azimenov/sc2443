package ag.selm.manager.repository;

import ag.selm.manager.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
}
