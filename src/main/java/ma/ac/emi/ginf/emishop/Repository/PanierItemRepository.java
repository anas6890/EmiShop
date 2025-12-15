package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Model.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PanierItemRepository extends JpaRepository <PanierItem, Long>{

}
