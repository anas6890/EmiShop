package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.Repository.CommandeRepository;
import org.springframework.stereotype.Service;

@Service
public class CommandeSerice {
    private final CommandeRepository commandeRepository;


    public CommandeSerice(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public void createCommande(){}
    public void deleteCommande(){}
    public void getCommande(){}
    public void modifyCommande(){}
}
