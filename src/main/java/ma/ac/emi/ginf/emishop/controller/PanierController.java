package ma.ac.emi.ginf.emishop.controller;

import ma.ac.emi.ginf.emishop.DTO.ChangePanierStatusRequest;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Service.PanierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/panier")
public class PanierController {
    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanier(@PathVariable Long id){
        return ResponseEntity.ok(this.panierService.getPanierById(id));
    }

    @PostMapping("{user_id}/add_panier")
    public ResponseEntity<Long> addPanier(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.panierService.createPanier(id));
    }

    @DeleteMapping("delete_panier/{id}")
    public ResponseEntity<Panier> deletePanier(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.panierService.deletePanierById(id));
    }

    @PostMapping("{id}/modify_panier_status")
    public ResponseEntity<?> changeStatus(
            @PathVariable Long id,
            @RequestBody ChangePanierStatusRequest panierStatusRequest)
    {
        this.panierService.updatePanierStatus(id, panierStatusRequest.getPanierStatus());
        return ResponseEntity.ok().build();
    }

}
