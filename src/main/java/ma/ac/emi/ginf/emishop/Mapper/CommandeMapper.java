package ma.ac.emi.ginf.emishop.Mapper;

import ma.ac.emi.ginf.emishop.DTO.CommandeDTO;
import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;
import ma.ac.emi.ginf.emishop.Model.Commande;
import ma.ac.emi.ginf.emishop.Model.Panier;
import ma.ac.emi.ginf.emishop.Model.User;

import java.util.List;
import java.util.stream.Collectors;

public class CommandeMapper {
    // Entity to DTO
    public static CommandeDTO toDTO(Commande commande) {
        if (commande == null) return null;

        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setTotalAmount(commande.getTotalAmount());
        dto.setStatus(commande.getStatus() != null ? commande.getStatus().name() : null);
        dto.setShippingAddress(commande.getShippingAddress());
        dto.setCreatedAt(commande.getCreatedAt());
        dto.setUserId(commande.getUser() != null ? commande.getUser().getId() : null);
        dto.setPanierId(commande.getPanier() != null ? commande.getPanier().getId() : null);
        return dto;
    }

    // DTO to Entity
    public static Commande toEntity(CommandeDTO dto, User user, Panier panier) {
        if (dto == null) return null;

        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setTotalAmount(dto.getTotalAmount());
        commande.setStatus(dto.getStatus() != null ? CommandeStatus.valueOf(dto.getStatus()) : null);
        commande.setShippingAddress(dto.getShippingAddress());
        commande.setCreatedAt(dto.getCreatedAt());
        commande.setUser(user);
        commande.setPanier(panier);
        return commande;
    }

    // List mapping
    public static List<CommandeDTO> toDTOList(List<Commande> commandes) {
        return commandes.stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
