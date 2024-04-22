package tn.enicarthage.internshipsmanagement.services;



import tn.enicarthage.internshipsmanagement.entities.Soutenance;
import tn.enicarthage.internshipsmanagement.models.SoutenanceModel;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO1;

import java.time.LocalDateTime;
import java.util.List;


public interface SoutenanceService {

	Soutenance saveSoutenance(Soutenance e);
	Soutenance updateSoutenance(Soutenance e);
	void deleteSoutenance(Soutenance e);
	void deleteSoutenanceById(int id);
	Soutenance getSoutenance(int id);
	List<SoutenanceDTO> getAll();
	//List<DemandeEnDTO> getAllSoutenancesByEng(int id);
	//Soutenance findByNCET(int ncet);
	//DemandeEnDTO getAllSoutenancesByEtudiant(int id);
	List<SoutenanceDTO> test(LocalDateTime date,String salle);
	List<SoutenanceDTO> getBySalleDate(LocalDateTime date,int id);
	List<SoutenanceDTO> findByDateJury(LocalDateTime date,Long id);
	SoutenanceDTO getBySfeId(int id);
	SoutenanceDTO getByEtudId(int Long);
	List<SoutenanceDTO1> getByEnId(int Long);
	List<SoutenanceDTO> getBySalle(int id);

    String verifierSoutenance(SoutenanceModel sou);
}
