package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.internshipsmanagement.entities.Entreprise;


public interface EntrepriseRepos extends JpaRepository<Entreprise, Integer> {
}
