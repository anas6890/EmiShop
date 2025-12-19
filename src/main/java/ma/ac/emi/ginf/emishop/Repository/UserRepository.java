package ma.ac.emi.ginf.emishop.Repository;

import ma.ac.emi.ginf.emishop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByNom(String nom);

    List<User> findByPrenom(String prenom);

    List<User> findDistinctByCommandesIsNotEmpty();// for example la bghina Filtrer les utilisateurs pour des opérations commerciales(hadi hi mchit b3id wsf)


}
