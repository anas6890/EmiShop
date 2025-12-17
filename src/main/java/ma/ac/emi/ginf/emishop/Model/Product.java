package ma.ac.emi.ginf.emishop.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime createdAt;

    @Column(precision = 10, scale = 4)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int stockQuantity;
    private String imageUrl;
    private String category;
    private boolean active;
}
