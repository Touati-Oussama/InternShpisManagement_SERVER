package tn.enicarthage.internshipsmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.DemandeEncadrement;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.DemnadeEncadrementRepos;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.response.DemandeEtudDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeEncadrementServiceImp implements DemandeEncadrementService{
	

	@Autowired
	DemnadeEncadrementRepos demandeRepository;
	@Override
	public DemandeEncadrement saveDemandeEncadrement(DemandeEncadrement e) {
		return this.demandeRepository.save(e);
	}

	@Override
	public DemandeEncadrement updateDemandeEncadrement(DemandeEncadrement e) {
		return this.demandeRepository.save(e);
	}

	@Override
	public void deleteDemandeEncadrement(DemandeEncadrement e) {
		this.demandeRepository.delete(e);
		
	}

	@Override
	public void deleteDemandeEncadrementById(int id) {
		this.demandeRepository.deleteById(id);
		
	}

	@Override
	public DemandeEncadrement getDemandeEncadrement(int id) {
		return this.demandeRepository.findById(id).get();
	}

	@Override
	public List<DemandeEncadrement> getAllDemandeEncadrements() {
		return this.demandeRepository.findAll();
	}

	@Override
	public int  deleteDemande(User e, User ens) {
		return this.demandeRepository. deleteDemande(e,ens);
		
	}
	
	@Override
	public void  deleteById(int id) {
		 this.demandeRepository.deleteById(id);
		
	}

	@Override
	public DemandeEncadrement getDemandeEncadrementByEnsEtud(Long ensId, Long etudId) {
		return demandeRepository.getByIds(etudId,ensId).get(0);
	}

	@Override
	public List<DemandeEtudDTO> getByEtudId(int id) {
		List<DemandeEncadrement> tmp = this.demandeRepository.getByEtudId(id);
		ArrayList<DemandeEtudDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			DemandeEtudDTO d = new DemandeEtudDTO();
			d.setId(i+1);
			d.setSujet(tmp.get(i).getSujet());
			d.setEtat(tmp.get(i).getEtat().name());
			d.setEncadreur(tmp.get(i).getEncadreur().getNom() + " " + tmp.get(i).getEncadreur().getPrenom());
			list.add(d);
		}
		return list;
	}
	
	@Override
	public List<DemandeEtudDTO> getByIds(Long idEtud, Long idEns) {
		List<DemandeEncadrement> tmp = this.demandeRepository.getByIds(idEtud, idEns);
		ArrayList<DemandeEtudDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			DemandeEtudDTO d = new DemandeEtudDTO();
			d.setId(i+1);
			d.setSujet(tmp.get(i).getSujet());
			d.setEtat(tmp.get(i).getEtat().name());
			d.setEncadreur(tmp.get(i).getEncadreur().getNom() + " " + tmp.get(i).getEncadreur().getPrenom());
			list.add(d);
		}
		return list;
	}

	@Override
	public List<DemandeEnDTO> getByEnId(int id) {
		List<DemandeEncadrement> tmp = this.demandeRepository.getByEnId(id);
		ArrayList<DemandeEnDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			DemandeEnDTO d = new DemandeEnDTO();
			d.setId(i+1);
			d.setSujet(tmp.get(i).getSujet());
			d.setEtat(tmp.get(i).getEtat().name());
			d.setEtudiant(tmp.get(i).getEtudiant().getNom() + " " + tmp.get(i).getEtudiant().getPrenom());
			d.setEmail(tmp.get(i).getEtudiant().getEmail());
			list.add(d);
		}
		return list;
	}

}
