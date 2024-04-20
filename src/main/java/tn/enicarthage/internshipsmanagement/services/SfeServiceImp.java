package tn.enicarthage.internshipsmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.entities.SFE;
import tn.enicarthage.internshipsmanagement.repos.SfeRepository;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.response.SfeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SfeServiceImp implements SfeService{
	
	@Autowired
	SfeRepository SFERepository;
	
	@Override
	public SFE saveSFE(SFE e) {
		// TODO Auto-generated method stub
		return SFERepository.save(e);
	}

	@Override
	public SFE updateSFE(SFE e) {
		// TODO Auto-generated method stub
		return SFERepository.save(e);
	}

	@Override
	public void deleteSFE(SFE e) {
		// TODO Auto-generated method stub
		SFERepository.delete(e);
		
	}

	@Override
	public void deleteSFEById(int id) {
		// TODO Auto-generated method stub
		SFERepository.deleteById(id);
	}

	@Override
	public SfeDTO getSFEById(int id) {
		// TODO Auto-generated method stub
		SFE tmp = this.SFERepository.findById(id).get();
		SfeDTO d = new SfeDTO();
		d.setId(tmp.getId());
		d.setSujet(tmp.getSujet());
		d.setEncadreur(tmp.getEncadreur().getNom() + " " + tmp.getEncadreur().getPrenom());
		d.setEtudiant(tmp.getEtudiant().getNom() + " " + tmp.getEtudiant().getPrenom());
		
		return d;
	}

	@Override
	public List<DemandeEnDTO> getAllSFEsByEng(Long id) {
		// TODO Auto-generated method stub
			List<SFE> tmp = this.SFERepository.getAllSFEsByEng(id);
			System.out.println("hhhhhhhhhhhhhhhhhh "+tmp.size()+  " hhhhhhhhhhhhhhhhhhh");
			List<DemandeEnDTO> list = new ArrayList();
			for (int i = 0; i < tmp.size();i++) {
				DemandeEnDTO d = new DemandeEnDTO();
				d.setId(tmp.get(i).getId());
				d.setSujet(tmp.get(i).getSujet());
				d.setEtudiant(tmp.get(i).getEtudiant().getNom() + " " + tmp.get(i).getEtudiant().getPrenom());
				d.setEmail(tmp.get(i).getEtudiant().getEmail());
				list.add(d);
			}
			return list;
	}

	@Override
	public DemandeEnDTO getAllSFEsByEtudiant(Long id) {
		// TODO Auto-generated method stub
			SFE tmp = this.SFERepository.getAllSFEsByEtudiant(id);
			if (tmp != null) {
				DemandeEnDTO d = new DemandeEnDTO();
				d.setId(tmp.getId());
				d.setSujet(tmp.getSujet());
				d.setEtudiant(tmp.getEtudiant().getNom() + " " + tmp.getEtudiant().getPrenom());
				d.setEmail(tmp.getEtudiant().getEmail());
				d.setIdEns(tmp.getEncadreur().getUserId());
				d.setIdEtud(tmp.getEtudiant().getUserId());

				return d;
			}
			return null;
	}

	@Override
	public DemandeEnDTO getAllSFEsByEtudiantUsername(String username) {
		// TODO Auto-generated method stub
		SFE tmp = this.SFERepository.findByEtudiantUsername(username);
		if (tmp != null) {
			DemandeEnDTO d = new DemandeEnDTO();
			d.setId(tmp.getId());
			d.setSujet(tmp.getSujet());
			d.setEtudiant(tmp.getEtudiant().getNom() + " " + tmp.getEtudiant().getPrenom());
			d.setEmail(tmp.getEtudiant().getEmail());
			d.setIdEns(tmp.getEncadreur().getUserId());
			d.setIdEtud(tmp.getEtudiant().getUserId());
			d.setEncadreur(tmp.getEncadreur().getNom() + " " + tmp.getEncadreur().getPrenom());
			return d;
		}
		return null;
	}

	@Override
	public SFE findByEtudiantUserId(Long id) {

		return this.SFERepository.findByEtudiantUserId(id);
	}

	@Override
	public List<Commentaire> getCommentaires(int id) {
		// TODO Auto-generated method stub
		return this.SFERepository.getCommentaires(id);
	}

	@Override
	public List<SfeDTO> getAll() {
		return this.SFERepository.findAll().stream()
				.map(tmp -> {
					SfeDTO d = new SfeDTO();
					d.setId(tmp.getId());
					d.setSujet(tmp.getSujet());
					d.setEncadreur(tmp.getEncadreur().getNom() + " " + tmp.getEncadreur().getPrenom());
					d.setEtudiant(tmp.getEtudiant().getNom() + " " + tmp.getEtudiant().getPrenom());
					return d;
				})
				.collect(Collectors.toList());
	}

	@Override
	public SFE getSFE(int id) {
		// TODO Auto-generated method stub
		return this.SFERepository.findById(id).get();
	}

}
