package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.Primary_Keys.Etudiant_Encadreur_PK;
import tn.enicarthage.internshipsmanagement.entities.DemandeEncadrement;
import tn.enicarthage.internshipsmanagement.entities.Etat;
import tn.enicarthage.internshipsmanagement.entities.TrelloBoard;
import tn.enicarthage.internshipsmanagement.entities.User;
import tn.enicarthage.internshipsmanagement.repos.DemnadeEncadrementRepos;
import tn.enicarthage.internshipsmanagement.repos.TrelloBoardRepository;
import tn.enicarthage.internshipsmanagement.request.DemandeEncadremetnModel;
import tn.enicarthage.internshipsmanagement.request.TrelloBoardReq;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.response.DemandeEtudDTO;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.services.DemandeEncadrementService;
import tn.enicarthage.internshipsmanagement.services.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/DemandeEncadrement")
@CrossOrigin
@RequiredArgsConstructor
public class DemandeEncadrementREST {
	
	 private final DemandeEncadrementService demandeService;
	 private final DemnadeEncadrementRepos demandeEncadrementRepository;
	 private final UserService userService;

	
	@RequestMapping(method= RequestMethod.GET)
	List<DemandeEncadrement> getAllDemandeEncadrements(){
		return demandeService.getAllDemandeEncadrements();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public DemandeEncadrement  getDemandeEncadrementById(@PathVariable("id") int id)
	{
		return demandeService.getDemandeEncadrement(id);
	}

	
	@RequestMapping(value="/etud/{id}",method = RequestMethod.GET)
	List<DemandeEtudDTO> getByEtudId(@PathVariable("id") int id)
	{
		return this.demandeService.getByEtudId(id);
	}
	
	
	@RequestMapping(value="/{idEtud}/{idEns}",method = RequestMethod.GET)
	List <DemandeEtudDTO>getByIds(@PathVariable("idEtud") Long idEtud, @PathVariable("idEns") Long idEns)
	{
		return this.demandeService.getByIds(idEtud, idEns);
	}
	
	@RequestMapping(value="/en/{id}",method = RequestMethod.GET)
	List <DemandeEnDTO>getByEnId(@PathVariable("id") int id) {
		return this.demandeService.getByEnId(id);
	}
	
	
	
	@PostMapping("/add")
	public 	DemandeEncadrement createDemandeEncadrement(@RequestBody DemandeEncadremetnModel demande) {
		DemandeEncadrement d = new DemandeEncadrement();
		Etudiant_Encadreur_PK id = new Etudiant_Encadreur_PK();
		id.setEtudiantId(demande.getEtudiantId());
		id.setEncadreurId(demande.getEncadreurId());
		d.setId(id);
		d.setEtudiant(userService.getUser(demande.getEtudiantId()));
		d.setEncadreur(userService.getUser(demande.getEncadreurId()));
		d.setSujet(demande.getSujet());
		d.setEtat(Etat.EN_COURS);
		return demandeService.saveDemandeEncadrement(d);
	}
	
	@RequestMapping(value ="/update/{idEns}/{idEtud}/{etat}"  ,method = RequestMethod.PUT)
	public DemandeEncadrement updateDemandeEncadrement(@PathVariable("idEns") Long idEns,
													   @PathVariable("etat") Etat etat,
													   @PathVariable("idEtud") Long idEtud) {
		return demandeService.updateDemandeEncadrement(etat,idEtud,idEns);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDemandeEncadrement(@PathVariable("id") int id) {
		DemandeEncadrement d = this.demandeService.getDemandeEncadrement(id);
		demandeService.deleteDemandeEncadrement(d);
		JSONResponse res = new JSONResponse();
		res.setMsg("La demnade est supprimée");
		return ResponseEntity.ok(res);
	}
	
	@RequestMapping(value="/delete/{idEtud}/{idEns}", method = RequestMethod.DELETE)
	public void deleteDemandeEncadremen(@PathVariable("idEtud") Long idEtud,@PathVariable("idEns") Long idEns) {
		User etd = this.userService.getUser(idEtud);
		User ens = this.userService.getUser(idEns);
		demandeService. deleteDemande(etd,ens);
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEncad(@PathVariable("id") int id) {
		DemandeEncadrement d = this.demandeEncadrementRepository.findById(id).get();
		JSONResponse res = new JSONResponse();
		res.setMsg("La demnade est supprimée");
		this.demandeService.deleteDemandeEncadrement(d);
		return ResponseEntity.ok(res);
	}


}
