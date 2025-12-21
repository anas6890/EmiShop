package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Model.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PanierItemRepository extends JpaRepository <PanierItem, Long>{


    List<PanierItem> findByPanierId(Long panierId);

    Optional<PanierItem> findByPanierIdAndProductId(Long panierId, Long productId);

    List<PanierItem> findByProductId(Long productId);

    List<PanierItem> findByQuantityGreaterThan(int quantity);

    List<PanierItem> findByQuantityLessThan(int quantity);

    List<PanierItem> findByUnitPriceGreaterThan(BigDecimal price);

    List<PanierItem> findByUnitPriceLessThan(BigDecimal price);


}
