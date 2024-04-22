package tn.enicarthage.internshipsmanagement.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.entities.SFE;

import java.util.List;

public interface SfeRepository extends JpaRepository<SFE, Integer>{
	SFE findByEtudiantUserId(Long id);
	SFE findByEtudiantUsername(String username);
	SFE findBySujet(String sujet);

	@Query("Select c from Commentaire c join SFE s on c.sfe = s where s.id = ?1")
	List <Commentaire> getCommentaires(int id);
	
	@Query("SELECT s from SFE s where s.encadreur.userId=:id")
	List<SFE> getAllSFEsByEng(@Param("id") Long id);
	
	@Query("SELECT s from SFE s where s.etudiant.userId=:id")
	SFE getAllSFEsByEtudiant(@Param("id") Long id);

}
