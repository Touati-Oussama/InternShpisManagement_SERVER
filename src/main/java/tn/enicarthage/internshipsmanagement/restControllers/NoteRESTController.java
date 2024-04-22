package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Note;
import tn.enicarthage.internshipsmanagement.models.NoteModel;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.response.NoteDTO;
import tn.enicarthage.internshipsmanagement.services.NoteService;
import tn.enicarthage.internshipsmanagement.services.SoutenanceService;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
@CrossOrigin
public class NoteRESTController {
	private final NoteService noteService;
	private final SoutenanceService soutenanceService;
	
	@RequestMapping(value="/etud/{id}", method= RequestMethod.GET)
	NoteDTO findByEud(@PathVariable("id") int id){
		return this.noteService.findByEtudId(id);
	}
	
	@RequestMapping(value="/soutenance/{id}", method= RequestMethod.GET)
	NoteDTO findBySou(@PathVariable("id") int id){
		return this.noteService.findBySoutenance(id);
	}
	
	
	@PostMapping("/add")
	public 	ResponseEntity<?> addNote(@RequestBody NoteModel note) {
		if (note.getNote() < 0 || note.getNote() > 20)
			return ResponseEntity.badRequest().body("La note doit étre entre [0..20]");
		Note n = new Note();
		n.setNote(note.getNote());
		n.setSoutenance(this.soutenanceService.getSoutenance(note.getSoutenance()));
		return ResponseEntity.ok(this.noteService.saveNote(n));
	}
}
