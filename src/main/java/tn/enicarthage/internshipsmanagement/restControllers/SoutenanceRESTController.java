package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Soutenance;
import tn.enicarthage.internshipsmanagement.models.SoutenanceModel;
import tn.enicarthage.internshipsmanagement.repos.SoutenanceRepository;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO1;
import tn.enicarthage.internshipsmanagement.services.SalleService;
import tn.enicarthage.internshipsmanagement.services.SfeService;
import tn.enicarthage.internshipsmanagement.services.SoutenanceService;
import tn.enicarthage.internshipsmanagement.services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/soutenance")
@RequiredArgsConstructor
@CrossOrigin
public class SoutenanceRESTController {

	private final SoutenanceService soutenanceService;
	private final SoutenanceRepository soutenanceRepository;
	private final SalleService salleService;
	private final SfeService sfeService;
	private final UserService encadreurService;

	@RequestMapping(value="/list", method= RequestMethod.GET)
	List<Soutenance> all(){
		return this.soutenanceRepository.findAll();
	}

	@RequestMapping(method= RequestMethod.GET)
	List<SoutenanceDTO> getAll(){
		return this.soutenanceService.getAll();
	}
	
	@RequestMapping(value="/getByDateSalle/{date}/{salle}", method= RequestMethod.GET)
	List<SoutenanceDTO> test(@PathVariable("date") /*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") LocalDateTime  */ String date,@PathVariable("salle") String salle){
		System.out.println(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return this.soutenanceService.test(dateTime,salle);
	}
	
	@RequestMapping(value="/findByDateSalle/{date}/{idSalle}", method= RequestMethod.GET)
	List<SoutenanceDTO> getByDateSalle(@PathVariable("date") String date,@PathVariable("idSalle") int idSalle){
		System.out.println(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return this.soutenanceService.getBySalleDate(dateTime, idSalle);
	}

	@RequestMapping(value="/salle/{idSalle}", method= RequestMethod.GET)
	List<SoutenanceDTO> getBySalle(@PathVariable("idSalle") int idSalle){
		return this.soutenanceService.getBySalle(idSalle);
	}
	
	@RequestMapping(value="/getByDateJury/{date}/{idPre}", method= RequestMethod.GET)
	List<SoutenanceDTO> findByDateJury(@PathVariable("date")String date,@PathVariable("idPre") Long idPre){
		System.out.println(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return this.soutenanceService.findByDateJury(dateTime,idPre);
	}
	
	@RequestMapping(value="/getBySfe/{id}", method= RequestMethod.GET)
	SoutenanceDTO findBySfe(@PathVariable("id") int id){
		return this.soutenanceService.getBySfeId(id);
	}
	
	
	@RequestMapping(value="/etud/{id}", method= RequestMethod.GET)
	SoutenanceDTO findByEud(@PathVariable("id") int id){
		return this.soutenanceService.getByEtudId(id);
	}
	

	@RequestMapping(value="/en/{id}", method= RequestMethod.GET)
	List<SoutenanceDTO1> findByEnId(@PathVariable("id") int id){
		return this.soutenanceService.getByEnId(id);
	}
	
	
	@PostMapping("/add")
	public 	ResponseEntity<?> createSoutenance(@RequestBody SoutenanceModel sou) {

		// Verification de salle, sfe et jurys
		String msg = this.soutenanceService.verifierSoutenance(sou);
		System.out.println(msg);
		if (!msg.isEmpty())
			return ResponseEntity.badRequest().body(msg);
		Soutenance soutenence = new Soutenance();
		soutenence.setSfe(this.sfeService.getSFE(sou.getSfe()));
		soutenence.setPresident(this.encadreurService.getUser(sou.getPresident()));
		soutenence.setRapporteur(this.encadreurService.getUser(sou.getRapporteur()));
		soutenence.setSalle(this.salleService.getSalle(sou.getSalle()));
		soutenence.setDate(sou.getDate());
		return ResponseEntity.ok(this.soutenanceService.saveSoutenance(soutenence));

	}
}
