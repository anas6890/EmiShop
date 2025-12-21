package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.DTO.CommandeRequestDTO;
import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;
import ma.ac.emi.ginf.emishop.Model.Commande;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Model.User;
import ma.ac.emi.ginf.emishop.Repository.CommandeRepository;
import ma.ac.emi.ginf.emishop.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommandeSerice {
    private final CommandeRepository commandeRepository;
    private final UserRepository userRepository;
    private final PanierService panierService;


    public CommandeSerice(CommandeRepository commandeRepository, UserRepository userRepository, PanierService panierService) {
        this.commandeRepository = commandeRepository;
        this.userRepository = userRepository;
        this.panierService = panierService;
    }

    public void createCommande(CommandeRequestDTO cmd){
        Long userId=cmd.getUserId();
        Long panierId=cmd.getPanierId();
        String shippingAddress=cmd.getShippingAddress();
        Commande commande=new Commande();
        User user=userRepository.findUserById(userId).get(0);
        Panier panier=panierService.getPanierById(panierId);
        commande.setUser(user);
        commande.setPanier(panier);
        commande.setCreatedAt(LocalDateTime.now());
        commande.setShippingAddress(shippingAddress);
        commande.setStatus(CommandeStatus.ACTIVE);
        commande.setTotalAmount(panier.getTotalAmount());
        this.commandeRepository.save(commande);
    }
    public void deleteCommande(Long id){
        this.commandeRepository.deleteById(id);
    }
    public Commande getCommande(Long id){
        return this.commandeRepository.findById(id).orElseThrow();
    }
    public List<Commande> getUserCommandes(Long userId){
        return this.commandeRepository.findByUserId(userId);
    }

    public List<Commande> getUserCommandesByStatus(Long userId, CommandeStatus status){
        return this.commandeRepository.findByUserIdAndStatus(userId, String.valueOf(status));
    }

    public void modifyCommandeStatus(Long id, CommandeStatus newStatus){
        Commande cmd=this.commandeRepository.findById(id).orElseThrow();
        cmd.setStatus(newStatus);
        this.commandeRepository.save(cmd);
    }
    public void modifyCommandeShippingAddress(Long id, String newShippingAddress){
        Commande cmd=this.commandeRepository.findById(id).orElseThrow();
        cmd.setShippingAddress(newShippingAddress);
        this.commandeRepository.save(cmd);
    }
}
