package ma.ac.emi.ginf.emishop.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 4)
    private BigDecimal totalAmount;

    private String status;
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "panier_id")
    private List<PanierItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "panier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Commande commande;
}
