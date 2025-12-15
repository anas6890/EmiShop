package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
