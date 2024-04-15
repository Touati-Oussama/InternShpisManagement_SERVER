package tn.enicarthage.internshipsmanagement.restControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Salle;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.services.SalleService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/salle")
@CrossOrigin
public class SalleRestController {

	@Autowired
	private SalleService salleService;
	
	
	
	@RequestMapping(method= RequestMethod.GET)
	List<Salle> getAllSalles(){
		return this.salleService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Salle salle) {;
		return ResponseEntity.ok(this.salleService.addSalle(salle));
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Salle  getSalleById(@PathVariable("id") int id)
	{
		return this.salleService.getSalle(id);
	}
	
	@RequestMapping(value="/nom/{nom}",method = RequestMethod.GET)
	public Salle  getNom(@PathVariable("nom") String salle)
	{
		return this.salleService.getByNom(salle);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateEtudiant(@RequestBody Salle s, @PathVariable("id") int id ) {
		Salle salle = this.salleService.getSalle(id);
		salle.setNom(s.getNom());
		return ResponseEntity.ok(this.salleService.updateSalle(salle));
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSalle(@PathVariable("id") int id) {
		Salle s = this.salleService.getSalle(id);
		JSONResponse res = new JSONResponse();
		res.setMsg("La salle est suppriméé");
		this.salleService.deleteSalle(s);
		return ResponseEntity.ok(res);
	}
	
}
