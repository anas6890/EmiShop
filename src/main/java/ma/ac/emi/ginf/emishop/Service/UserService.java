package ma.ac.emi.ginf.emishop.Service;

import ma.ac.emi.ginf.emishop.Model.User;
import ma.ac.emi.ginf.emishop.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public  UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(String nom, String prenom,String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = new User();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return "Utilisateur créé avec succès";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return "Login réussi";
    }
}
