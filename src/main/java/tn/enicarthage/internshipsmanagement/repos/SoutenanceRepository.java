package tn.enicarthage.internshipsmanagement.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.Soutenance;

import java.time.LocalDateTime;
import java.util.List;

public interface SoutenanceRepository extends JpaRepository<Soutenance, Integer> {

	
	@Query("SELECT e from Soutenance e where e.date = :date and e.salle=:salle")
	List<Soutenance> findByDateSalle(@Param("date")  LocalDateTime date,@Param("salle") String salle);
	
	@Query("SELECT e from Soutenance e where e.date = :date and (e.president.userId=:id or e.rapporteur.userId=:id)")
	List<Soutenance> findByDateJury(@Param("date")  LocalDateTime date,@Param("id") Long id);
	
	
	@Query("SELECT e from Soutenance e where e.sfe.id=:id")
	Soutenance findBySfeId(@Param("id") int id);
	
	
	@Query("SELECT s from Soutenance s join SFE e on (s.sfe = e) join User et on (e.etudiant = et)  where et.userId=:id")
	Soutenance findByEtudId(@Param("id") int id);
	
	@Query("SELECT s from Soutenance s join SFE e on (s.sfe = e) join User en on (e.encadreur = en)  where en.userId=:id or s.president.userId=:id  or s.rapporteur.userId=:id")
	List<Soutenance> findByEnId(@Param("id") int id);
	
	
	@Query("SELECT s from Soutenance s join Salle e on (s.salle = e)  where s.date = :date and e.id=:idSalle")
	List<Soutenance> getByDateSalle(@Param("date")  LocalDateTime date,@Param("idSalle") int idSalle);
	
	@Query("SELECT s from Soutenance s join Salle e on (s.salle = e)  where  e.id=:idSalle")
	List<Soutenance> getBySalle(@Param("idSalle") int idSalle);
	

}
