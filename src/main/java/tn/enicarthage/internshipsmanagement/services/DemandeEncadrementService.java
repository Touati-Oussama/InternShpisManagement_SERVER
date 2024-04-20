package tn.enicarthage.internshipsmanagement.services;



import tn.enicarthage.internshipsmanagement.entities.DemandeEncadrement;
import tn.enicarthage.internshipsmanagement.entities.Etat;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.response.DemandeEtudDTO;

import java.util.List;


public interface DemandeEncadrementService {

	DemandeEncadrement saveDemandeEncadrement(DemandeEncadrement e);
	DemandeEncadrement updateDemandeEncadrement(Etat e, Long idEtud, Long idEns);
	void deleteDemandeEncadrement(DemandeEncadrement e);
	void deleteDemandeEncadrementById(int id);
	DemandeEncadrement getDemandeEncadrement(int id);
	List<DemandeEncadrement> getAllDemandeEncadrements();
	
	int deleteDemande(User e, User ens);
	
	List<DemandeEtudDTO>getByEtudId(int id);
	public List<DemandeEnDTO>getByEnId(int id);
	List<DemandeEtudDTO> getByIds(Long idEtud, Long idEns);
	void deleteById(int id);
	DemandeEncadrement getDemandeEncadrementByEnsEtud(Long ensId, Long etudId);
}
