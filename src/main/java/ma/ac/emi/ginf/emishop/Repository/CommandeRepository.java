package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;
import ma.ac.emi.ginf.emishop.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUserId(Long userId);

    @Query("SELECT c FROM Commande c WHERE c.user.id = :userId ORDER BY c.createdAt DESC")
    Optional<Commande> findLatestOrder(@Param("userId") Long userId);

    List<Commande> findByStatus(String status);

    List<Commande> findByUserIdAndStatus(Long userId, CommandeStatus status);

    List<Commande> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Commande> findByTotalAmountGreaterThan(BigDecimal montant);

    List<Commande> findByTotalAmountLessThan(BigDecimal montant);

    Optional<Commande> findByPanierId(Long panierId);
}
