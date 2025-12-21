package ma.ac.emi.ginf.emishop.controller;

import ma.ac.emi.ginf.emishop.Model.PanierItem;
import ma.ac.emi.ginf.emishop.Service.PanierItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panier-items")
public class PanierItemController {

    private final PanierItemService panierItemService;

    public PanierItemController(PanierItemService panierItemService) {
        this.panierItemService = panierItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanierItem> getPanierItemById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(panierItemService.getItemById(id));
    }

    @GetMapping("/panier/{panierId}")
    public ResponseEntity<List<PanierItem>> getItemsByPanier(
            @PathVariable Long panierId
    ) {
        return ResponseEntity.ok(panierItemService.getItemsByPanierId(panierId));
    }

    @PostMapping
    public ResponseEntity<PanierItem> addItemToPanier(
            @RequestParam Long panierId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        PanierItem created =
                panierItemService.addItemToPanier(panierId, productId, quantity);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanierItem> updateQuantity(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        PanierItem updated =
                panierItemService.updateItemQuantity(id, quantity);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(
            @PathVariable Long id
    ) {
        panierItemService.removeItem(id);
        return ResponseEntity.noContent().build();
    }
}
