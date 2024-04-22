package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.FileDB;

import java.util.List;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

	List<FileDB> findBySfeId(int id);
	//FileDB findBySfeEtudiantUserId(Long id);

	List<FileDB> findBySfeEtudiantUserId(Long id);

	@Query("SELECT d.nom AS departmentName, u.nom AS nom, u.prenom AS prenom, f FROM FileDB f JOIN f.sfe s JOIN s.etudiant u JOIN u.department d ORDER BY d.nom, u.nom, u.prenom")
	List<Object[]> findAllOrderedByDepartmentAndUser();

}

