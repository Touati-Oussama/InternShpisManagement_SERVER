package tn.enicarthage.internshipsmanagement.services;



import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.entities.SFE;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.response.SfeDTO;

import java.util.List;

public interface SfeService {

	
	SFE saveSFE(SFE e);
	SFE updateSFE(SFE e);
	void deleteSFE(SFE e);
	void deleteSFEById(int id);
	SFE getSFE(int id);
	SfeDTO getSFEById(int id);
	List<DemandeEnDTO> getAllSFEsByEng(Long id);
	SFE findByEtudiantUserId(Long id);
	List <Commentaire> getCommentaires(int id);
	DemandeEnDTO getAllSFEsByEtudiantUsername(String username);
	DemandeEnDTO getAllSFEsByEtudiant(Long id);
	List <SfeDTO> getAll();
}
