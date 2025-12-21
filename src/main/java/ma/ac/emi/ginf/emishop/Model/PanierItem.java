package ma.ac.emi.ginf.emishop.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class PanierItem {

    public PanierItem() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Column(precision = 10, scale = 4)
    private BigDecimal unitPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panier_id")
    private Panier panier;

    public Long getId() {
        return id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}
