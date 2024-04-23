package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.enicarthage.internshipsmanagement.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Integer> {
	Salle findByNom(String nom);

}
