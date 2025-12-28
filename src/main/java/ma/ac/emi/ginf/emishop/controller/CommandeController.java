package ma.ac.emi.ginf.emishop.controller;

import ma.ac.emi.ginf.emishop.DTO.*;
import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;
import ma.ac.emi.ginf.emishop.Model.Commande;
import ma.ac.emi.ginf.emishop.Service.CommandeSerice;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/commande")
public class CommandeController {
    private final CommandeSerice commandeSerice;

    public CommandeController(CommandeSerice commandeSerice) {
        this.commandeSerice = commandeSerice;
    }

    @GetMapping("get_commande/{id}")
    public ResponseEntity<CommandeDTO> getCommande(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.commandeSerice.getCommande(id));
    }
    @PostMapping("create_commande")
    public ResponseEntity<?> createCommande(@RequestBody CommandeRequestDTO cmd){
        this.commandeSerice.createCommande(cmd);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("deleteCommande/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long id){
        this.commandeSerice.deleteCommande(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("get_all_commandes/{id}")
    public ResponseEntity<List<CommandeDTO>> getCommandes(@PathVariable Long id){
        return ResponseEntity.ok(this.commandeSerice.getUserCommandes(id));
    }

    @GetMapping("get_commandes_by_status/{id}/{status}")
    public ResponseEntity<List<CommandeDTO>> getCommandesByStatus(
             @PathVariable Long id,
             @PathVariable String status
            ){
        return ResponseEntity.ok(this.commandeSerice.getUserCommandesByStatus(id, status));
    }

    @PostMapping("{id}/modify_status")
    public ResponseEntity<?> changeCommandeStatus(
            @PathVariable Long id,
            @RequestBody ChangeStatusRequest rqst){
        this.commandeSerice.modifyCommandeStatus(id,rqst.getNewStatus());
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/modify_shipping_address")
    public ResponseEntity<?> changeCommandeShippingAddress(
            @PathVariable Long id,
            @RequestBody ChangeShippingAddressRequest rqst){
        this.commandeSerice.modifyCommandeShippingAddress(id,rqst.getNewShippingAddress());
        return ResponseEntity.ok().build();
    }


}
