package tn.enicarthage.internshipsmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enicarthage.internshipsmanagement.entities.FileDB;

import java.util.List;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

	FileDB findBySfeId(int id);
	FileDB findBySfeEtudiantUserId(Long id);

}

