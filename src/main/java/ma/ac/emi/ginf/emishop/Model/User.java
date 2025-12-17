package ma.ac.emi.ginf.emishop.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String username;

    private String email;
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Commande> commandes = new ArrayList<>();

    public void addCommand(Commande commande) {
        this.commandes.add(commande);
    }
}
