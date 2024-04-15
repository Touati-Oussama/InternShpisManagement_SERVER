package tn.enicarthage.internshipsmanagement.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.enicarthage.internshipsmanagement.entities.DemandeInscription;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.entities.User;

import java.util.List;

public interface DemandeInscriptionRepository extends JpaRepository<DemandeInscription, Long> {

    @Query("SELECT d FROM DemandeInscription d WHERE d.role = ?1")
    List<DemandeInscription> findByRole(ERole role);

}
