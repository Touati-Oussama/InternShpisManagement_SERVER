package tn.enicarthage.internshipsmanagement.repos;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.enicarthage.internshipsmanagement.entities.DemandeEncadrement;
import tn.enicarthage.internshipsmanagement.entities.User;


import java.util.List;


@Repository
public interface DemnadeEncadrementRepos extends JpaRepository<DemandeEncadrement, Integer> {
	@Transactional
	@Modifying
	
	@Query("DELETE from DemandeEncadrement e where e.etudiant =:etud and e.encadreur=:encad")
	int deleteDemande(@Param("etud") User etud,@Param("encad") User encad);

	@Query("SELECT d from DemandeEncadrement d where d.etudiant.userId=:id")
	List<DemandeEncadrement> getByEtudId(@Param("id") int id);
	
	@Query("DELETE from DemandeEncadrement d where d.etudiant.userId=:id")
	List<DemandeEncadrement> deleteByEtudId(@Param("id") int id);
	
	@Query("DELETE from DemandeEncadrement d where d.id=:id")
	void deleteById(@Param("id") int id);

	@Query("SELECT d from DemandeEncadrement d where d.encadreur.userId=:id")
	List<DemandeEncadrement> getByEnId(@Param("id") int id);

	@Query("SELECT d from DemandeEncadrement d where d.etudiant.userId=:idEtud and d.encadreur.userId =:idEns")
	List<DemandeEncadrement> getByIds(@Param("idEtud") Long idEtud, @Param("idEns") Long idEns);
}
