package tn.enicarthage.internshipsmanagement.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

	@Query("SELECT c FROM Commentaire c WHERE c.sfe.id = :id ORDER BY c.date ASC")
	List <Commentaire> getCommentairesBySFE(@Param("id") int id);
}
