package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.Model.Product;
import ma.ac.emi.ginf.emishop.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found: " + id
                ));
    }

    public List<Product> searchByName(String productname) {
        if (productname == null || productname.isBlank()) {
            return getAllProducts();
        }
        return productRepository.findByNameContainingIgnoreCase(productname.trim());
    }

    public List<Product> getByCategory(String category) {
        if (category == null || category.isBlank()) {
            return getAllProducts();
        }
        return productRepository.findByCategory(category.trim());
    }

    public List<Product> getActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    public List<Product> getInactiveProducts() {
        return productRepository.findByActiveFalse();
    }

    @Transactional
    public Product createProduct(Product product) {
        Product payload = requirePayload(product);
        if (payload.getCreatedAt() == null) {
            payload.setCreatedAt(LocalDateTime.now());
        }
        payload.setId(null);
        validateProduct(payload);
        return productRepository.save(payload);
    }

    @Transactional
    public Product updateProduct(Long id, Product updates) {
        Product existing = getProductById(id);
        Product payload = requirePayload(updates);
        merge(existing, payload);
        validateProduct(existing);
        return productRepository.save(existing);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void updateStock(Long id, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        Product product = getProductById(id);
        product.setStockQuantity(newQuantity);
        productRepository.save(product);
    }

    private Product requirePayload(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product payload is required");
        }
        return product;
    }

    private void merge(Product target, Product source) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setStockQuantity(source.getStockQuantity());
        target.setImageUrl(source.getImageUrl());
        target.setCategory(source.getCategory());
        target.setActive(source.isActive());
        if (source.getCreatedAt() != null) {
            target.setCreatedAt(source.getCreatedAt());
        }
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must be positive or zero");
        }
        if (product.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be positive or zero");
        }
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(LocalDateTime.now());
        }
    }
}
