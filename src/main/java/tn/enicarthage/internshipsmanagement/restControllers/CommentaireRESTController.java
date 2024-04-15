package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.response.CommentaireDTO;
import tn.enicarthage.internshipsmanagement.services.CommentaireService;
import tn.enicarthage.internshipsmanagement.services.SfeService;
import tn.enicarthage.internshipsmanagement.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commentaire")
@RequiredArgsConstructor
@CrossOrigin
public class CommentaireRESTController {

	private final CommentaireService commentaireService;
	private final SfeService sfeService;
	private final UserService userService;
	
	
	
	@RequestMapping(method= RequestMethod.GET)
	List<CommentaireDTO> getAllCommentaires(){
		return commentaireService.getAllCommentaires();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Commentaire  getCommentaireById(@PathVariable("id") int id)
	{
		return commentaireService.getCommentaire(id);
	}
	
	
	@PostMapping("/add/{sfeId}")
	public 	ResponseEntity<?> createCommentaire(@RequestBody Commentaire commentaire, @PathVariable("sfeId") int sfe) {
		
		commentaire.setSfe(sfeService.getSFE(sfe));
		
		return ResponseEntity.ok(commentaireService.saveCommentaire(commentaire));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Commentaire updateCommentaire(@RequestBody Commentaire Commentaire) {
		
		return commentaireService.updateCommentaire(Commentaire);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void deleteCommentaire(@PathVariable("id") int id) {
		commentaireService.deleteCommentaireById(id);
	}
	
    @RequestMapping(value="/sfe/{id}",method = RequestMethod.GET)
    public List <CommentaireDTO> getCommentairesBySFE(@PathVariable("id") int id){
    	return commentaireService.getCommentairesBySFE(id);
    }
	


}