package tn.enicarthage.internshipsmanagement.services;



import tn.enicarthage.internshipsmanagement.entities.DemandeInscription;

import java.util.List;



public interface DemandeInscriptionService {

	DemandeInscription saveDemandeInscription(DemandeInscription e);
	DemandeInscription updateDemandeInscription(DemandeInscription e);
	void deleteDemandeInscription(DemandeInscription e);
	void deleteDemandeInscriptionById(Long id);
	DemandeInscription getDemandeInscription(Long id);
	List<DemandeInscription> getAllDemandeInscriptions();
	List<DemandeInscription> getAllDemandeInscriptionsEnseignants();
	List<DemandeInscription> getAllDemandeInscriptionsEtudiants();

}
