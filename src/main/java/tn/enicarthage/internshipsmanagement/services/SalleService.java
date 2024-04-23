package tn.enicarthage.internshipsmanagement.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Salle;
import tn.enicarthage.internshipsmanagement.repos.SalleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalleService {


	private final SalleRepository salleReposioty;
	
	public Salle addSalle(Salle s){
		return this.salleReposioty.save(s);
	}
	
	public Salle updateSalle(Salle s) {
		return this.salleReposioty.save(s);
	}
	
	public void deleteSalle(Salle s) {
		this.salleReposioty.delete(s);
	}
	
	public List<Salle> getAll(){
		return this.salleReposioty.findAll();
	}
	
	public Salle getSalle(int id) {
		return this.salleReposioty.findById(id).get();
	}
	
	public Salle getByNom(String  nom) {
		return this.salleReposioty.findByNom(nom);
	}
}
