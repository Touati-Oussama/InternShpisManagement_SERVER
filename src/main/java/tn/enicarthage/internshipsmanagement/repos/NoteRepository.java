package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.Note;


public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("SELECT n  from Note n join  Soutenance s on (n.soutenance = s) join SFE e on (s.sfe = e) join User et on (e.etudiant = et)  where et.userId=:id")
	Note findByEtudId(@Param("id") int id);
	
	@Query("SELECT n  from Note n join  Soutenance s on (n.soutenance = s) where s.id=:id")
	Note findBySoutenance(@Param("id") int id);
}
