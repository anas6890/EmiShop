package ma.ac.emi.ginf.emishop.Service;

import jakarta.persistence.EntityNotFoundException;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Model.PanierItem;
import ma.ac.emi.ginf.emishop.Model.Product;
import ma.ac.emi.ginf.emishop.Repository.PanierItemRepository;
import ma.ac.emi.ginf.emishop.Repository.PanierRepository;
import ma.ac.emi.ginf.emishop.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PanierItemService {

    private final PanierItemRepository panierItemRepository;
    private final PanierRepository panierRepository;
    private final ProductRepository productRepository;

    public PanierItemService(PanierItemRepository panierItemRepository,
                             PanierRepository panierRepository,
                             ProductRepository productRepository) {
        this.panierItemRepository = panierItemRepository;
        this.panierRepository = panierRepository;
        this.productRepository = productRepository;
    }

    public PanierItem addItemToPanier(Long panierId, Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new EntityNotFoundException("Panier not found with id " + panierId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

        Optional<PanierItem> optExisting = panierItemRepository.findByPanierIdAndProductId(panierId, productId);

        BigDecimal productPrice = product.getPrice() == null ? BigDecimal.ZERO : product.getPrice();

        PanierItem item;
        if (optExisting.isPresent()) {
            item = optExisting.get();
            int newQty = item.getQuantity() + quantity;
            item.setQuantity(newQty);
            if (item.getUnitPrice() == null) item.setUnitPrice(productPrice);
        } else {
            item = new PanierItem();
            item.setPanier(panier);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(productPrice);
        }

        PanierItem saved = panierItemRepository.save(item);
        return saved;
    }

    public PanierItem updateItemQuantity(Long panierItemId, int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("newQuantity must be greater than 0; to remove an item call removeItem()");
        }

        PanierItem item = panierItemRepository.findById(panierItemId)
                .orElseThrow(() -> new EntityNotFoundException("PanierItem not found with id " + panierItemId));

        item.setQuantity(newQuantity);
        if (item.getUnitPrice() == null) {
            item.setUnitPrice(BigDecimal.ZERO);
        }
        PanierItem saved = panierItemRepository.save(item);
        return saved;
    }

    @Transactional
    public PanierItem getItemById(Long id) {
        return panierItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PanierItem not found with id " + id));
    }

    @Transactional
    public List<PanierItem> getItemsByPanierId(Long panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new EntityNotFoundException("Panier not found with id " + panierId));
        return panierItemRepository.findByPanierId(panier.getId());
    }

    public void removeItem(Long panierItemId) {
        PanierItem item = panierItemRepository.findById(panierItemId)
                .orElseThrow(() -> new EntityNotFoundException("PanierItem not found with id " + panierItemId));
        panierItemRepository.delete(item);
    }
}
