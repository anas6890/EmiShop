package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.Enum.PanierStatus;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Model.User;
import ma.ac.emi.ginf.emishop.Repository.PanierRepository;
import ma.ac.emi.ginf.emishop.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PanierService {
    private final PanierRepository panierRepository;
    private final UserRepository userRepository;

    public PanierService(PanierRepository panierRepo, UserRepository userRepo) {
        this.panierRepository = panierRepo;
        this.userRepository=userRepo;
    }

    Long createPanier(Long userId){
        Panier panier=new Panier();
        User user= userRepository.findUserById(userId).get(0);
        panier.setUser(user);
        panier.setCreatedAt(LocalDateTime.now());
        panier.setStatus(PanierStatus.ACTIVE);
        panier.setTotalAmount(BigDecimal.valueOf(0));
        Long id= panierRepository.save(panier).getId();
        return id;
    }
    List<Panier> getPaniersOf(Long userId){
        return this.panierRepository.findByUserId(userId);
    }
    Panier getPanierById(Long id){
        return panierRepository.findById(id).orElseThrow();
    }
    Panier deletePanierById(Long id){
        Panier panier=this.getPanierById(id);
        this.panierRepository.deleteById(id);
        return panier;
    }
    void updatePanierAmount(Long id){
        Panier panier=this.getPanierById(id);
        panier.setTotalAmount(
                panier.getItems().stream()
                        .map(item -> item.getUnitPrice().multiply(item.getQuantity()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        panierRepository.save(panier);
    }
    void updatePanierStatus(Long id,PanierStatus status){
        Panier panier=this.getPanierById(id);
        panier.setStatus(status);
        this.panierRepository.save(panier);
    }

}
