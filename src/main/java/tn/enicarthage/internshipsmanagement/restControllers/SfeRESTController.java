package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.*;
import tn.enicarthage.internshipsmanagement.repos.SfeRepository;
import tn.enicarthage.internshipsmanagement.repos.TrelloBoardRepository;
import tn.enicarthage.internshipsmanagement.request.SfeModel;
import tn.enicarthage.internshipsmanagement.request.TrelloBoardReq;
import tn.enicarthage.internshipsmanagement.response.SfeDTO;
import tn.enicarthage.internshipsmanagement.response.DemandeEnDTO;
import tn.enicarthage.internshipsmanagement.services.DemandeEncadrementService;
import tn.enicarthage.internshipsmanagement.services.EmailSenderService;
import tn.enicarthage.internshipsmanagement.services.SfeService;
import tn.enicarthage.internshipsmanagement.services.UserService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sfe")
@CrossOrigin
public class SfeRESTController {
	

	private final SfeService SFEService;
	private final SfeRepository sfeRepository;
	private final UserService userService;
	private final DemandeEncadrementService demandeService;
	private final TrelloBoardRepository trelloBoardRepository;
	private final EmailSenderService emailSenderService;
	
	
	
	@RequestMapping(method= RequestMethod.GET)
	List<SFE> getAllSFEs(){
		return this.sfeRepository.findAll();
	}
	
	@RequestMapping(value="/get",method= RequestMethod.GET)
	List<SfeDTO> getAll(){
		
		return this.SFEService.getAll();
	}
	
	@RequestMapping(value="/en/{id}", method= RequestMethod.GET)
	List<DemandeEnDTO> getAllSFEs(@PathVariable("id") Long id){
		
		return SFEService.getAllSFEsByEng(id);
	}
	
	@RequestMapping(value="/etudiant/{id}", method= RequestMethod.GET)
	DemandeEnDTO getSFEbyEtud(@PathVariable("id") Long id){
		
		return SFEService.getAllSFEsByEtudiant(id);
	}
	@RequestMapping(value="/etudiant/username/{username}", method= RequestMethod.GET)
	DemandeEnDTO getSFEbyEtud(@PathVariable("username") String username){

		return SFEService.getAllSFEsByEtudiantUsername(username);
	}

	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public SFE  getSFEById(@PathVariable("id") int id)
	{
		return SFEService.getSFE(id);
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public SfeDTO getById(@PathVariable("id") int id)
	{
		return SFEService.getSFEById(id);
	}
	
	
	@PostMapping("/add")
	public 	ResponseEntity<?> createSFE(@RequestBody SfeModel sfe ) {
		SFE s = new SFE();
		s.setEtudiant(userService.getUser(sfe.getEtudiantId()));
		s.setEncadreur(userService.getUser(sfe.getEncadreurId()));
		s.setSujet(sfe.getSujet());
		return ResponseEntity.ok(SFEService.saveSFE(s));
	}
	
	@PostMapping("/add/encadrement/{idEtud}/{idEns}")
	public 	SFE createSFETest(@PathVariable("idEns") Long ensId, @PathVariable("idEtud") Long etudId ) {
		DemandeEncadrement d = this.demandeService.getDemandeEncadrementByEnsEtud(ensId,etudId);
		d.setEtat(Etat.ACCEPTATION);
		demandeService.updateDemandeEncadrement(Etat.ACCEPTATION,d.getEtudiant().getUserId(),d.getEncadreur().getUserId());
		SFE sfe = new SFE();
		sfe.setEtudiant(d.getEtudiant());
		sfe.setEncadreur(d.getEncadreur());
		sfe.setSujet(d.getSujet());
		return SFEService.saveSFE(sfe);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public SFE updateSFE(@RequestBody SFE SFE) {
		return SFEService.updateSFE(SFE);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void deleteSFE(@PathVariable("id") int id) {
		SFEService.deleteSFEById(id);
	}


	@RequestMapping( value="/findByEtudiant/{id}",method = RequestMethod.GET)
	public SFE findByEtudiant(@PathVariable("id") Long id) {

		return SFEService.findByEtudiantUserId(id);
	}
	
	@RequestMapping( value="/test/{id}",method = RequestMethod.GET)
	public List <Commentaire> getCommentaires(@PathVariable("id") int id) {
		System.out.println(SFEService.getCommentaires(id));
		return SFEService.getCommentaires(id);
		
	}

	@RequestMapping(path="/trello/add",method = RequestMethod.POST)
	public ResponseEntity<?> createBoard(@RequestBody TrelloBoardReq board ){
		TrelloBoard b = new TrelloBoard();
		SFE s = SFEService.findByEtudiantUserId(board.getUserId());
		b.setId(board.getIdBoard());
		b.setUrl(board.getUrl());
		b.setSfe(s);

		String msg = "Cher "+ s.getEtudiant().getNom() +" "+ s.getEtudiant().getPrenom()+ ",\n"+
				"Nous sommes ravis de vous informer que votre demande d'encadrement pour le sujet "+ s.getSujet() + "</b>" +
				" a été acceptée par l'enseignant " + s.getEncadreur().getNom()+" " + s.getEncadreur().getPrenom()+
				"\nPour faciliter le suivi du déroulement de votre stage, nous utilisons la plateforme Trello. Vous aurez besoin d'un compte sur Trello pour accéder à votre espace de travail. " +
				"Voici le lien vers votre espace de travail sur Trello: " + board.getUrl()+
				"\n \n Cordialement \n Direction de Stage à l'ENICARTHAGE";
		emailSenderService.sendEmail(s.getEtudiant().getEmail(),"STAGE PFE",msg);
		return ResponseEntity.ok(trelloBoardRepository.save(b));
	}

	@RequestMapping(path="/trello/board/{id}",method = RequestMethod.GET)
	public TrelloBoard findBoard(@PathVariable("id") int id){
		SFE s = sfeRepository.findById(id).get();
		return trelloBoardRepository.findBySfe(s);
	}

}
