package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PanierRepository extends JpaRepository <Panier, Long>{

    @Override
    Optional<Panier> findById(Long id);

    List<Panier> findByUserId(Long userId);

    List<Panier> findByUserIdAndStatus(Long userId, String status);

    List<Panier> findByStatus(String status);

    List<Panier> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Panier> findByTotalAmountGreaterThan(BigDecimal montant);

    List<Panier> findByTotalAmountLessThan(BigDecimal montant);

    @Query("SELECT p FROM Panier p WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    Optional<Panier> findTopByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

}
