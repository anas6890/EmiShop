package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.DTO.CommandeDTO;
import ma.ac.emi.ginf.emishop.DTO.CommandeRequestDTO;
import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;
import ma.ac.emi.ginf.emishop.Mapper.CommandeMapper;
import ma.ac.emi.ginf.emishop.Model.Commande;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Model.User;
import ma.ac.emi.ginf.emishop.Repository.CommandeRepository;
import ma.ac.emi.ginf.emishop.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static ma.ac.emi.ginf.emishop.Mapper.CommandeMapper.toDTOList;

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
    public CommandeDTO getCommande(Long id){
        return CommandeMapper.toDTO(this.commandeRepository.findById(id).orElseThrow());
    }
    public List<CommandeDTO> getUserCommandes(Long userId){
        return toDTOList(this.commandeRepository.findByUserId(userId));
    }

    public List<CommandeDTO> getUserCommandesByStatus(Long userId, String status){
        return toDTOList(this.commandeRepository.findByUserIdAndStatus(userId, CommandeStatus.valueOf(status)));
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
