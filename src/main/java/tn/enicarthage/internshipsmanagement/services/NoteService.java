package tn.enicarthage.internshipsmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Note;
import tn.enicarthage.internshipsmanagement.repos.NoteRepository;
import tn.enicarthage.internshipsmanagement.repos.SoutenanceRepository;
import tn.enicarthage.internshipsmanagement.response.NoteDTO;

@Service
public class NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	SoutenanceRepository soutenanceRepository;
	
	public NoteDTO findByEtudId(int id) {
		Note n = this.noteRepository.findByEtudId(id);
		if (n != null)
		{
			NoteDTO nm = new NoteDTO();
			nm.setId(n.getId());
			nm.setNote(n.getNote());
			nm.setSoutenance(n.getSoutenance().getId());
			return nm;
		}
		
		return null;
	}
	
	
	public Note saveNote(Note n) {
		// TODO Auto-generated method stub
		return this.noteRepository.save(n);
	}
	
	public NoteDTO findBySoutenance(int id) {
		Note n = this.noteRepository.findBySoutenance(id);
		if (n != null)
		{
			NoteDTO nm = new NoteDTO();
			nm.setId(n.getId());
			nm.setNote(n.getNote());
			nm.setSoutenance(n.getSoutenance().getId());
			return nm;
		}
		
		return null;
	}
	

}
