package ma.ac.emi.ginf.emishop.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur vide
@AllArgsConstructor // Constructeur avec tous les champs
@Entity
public class PanierItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private int quantite;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
