package tn.enicarthage.internshipsmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.DemandeInscription;
import tn.enicarthage.internshipsmanagement.entities.ERole;
import tn.enicarthage.internshipsmanagement.repos.DemandeInscriptionRepository;

import java.util.List;

@Service
public class DemandeInscriptionServiceImp implements DemandeInscriptionService{

	@Autowired
	DemandeInscriptionRepository demandeInscriptionRepository;

	
	@Override
	public DemandeInscription saveDemandeInscription(DemandeInscription e) {
		
		return demandeInscriptionRepository.save(e);
	}

	@Override
	public DemandeInscription updateDemandeInscription(DemandeInscription e) {
		return demandeInscriptionRepository.save(e);
	}

	@Override
	public void deleteDemandeInscription(DemandeInscription e) {
		demandeInscriptionRepository.delete(e);
		
	}

	@Override
	public List<DemandeInscription> getAllDemandeInscriptions() {
		return demandeInscriptionRepository.findAll();
	}

	@Override
	public List<DemandeInscription> getAllDemandeInscriptionsEnseignants() {
		return demandeInscriptionRepository.findByRole(ERole.ENSEIGNANT);
	}

	@Override
	public List<DemandeInscription> getAllDemandeInscriptionsEtudiants() {
		return demandeInscriptionRepository.findByRole(ERole.ETUDIANT);
	}

	@Override
	public void deleteDemandeInscriptionById(Long id) {
		demandeInscriptionRepository.deleteById(id);
	}

	@Override
	public DemandeInscription getDemandeInscription(Long id) {
		return demandeInscriptionRepository.findById(id).get();
	}

}
