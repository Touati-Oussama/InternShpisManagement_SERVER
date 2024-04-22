package tn.enicarthage.internshipsmanagement.services;



import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.response.CommentaireDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommentaireService {

	Commentaire saveCommentaire(Commentaire e);
	Commentaire updateCommentaire(Commentaire e);
	void deleteCommentaire(Commentaire e);
	void deleteCommentaireById(int id);
	Commentaire getCommentaire(int id);
	List<CommentaireDTO> getAllCommentaires();
	List<CommentaireDTO> getCommentairesBySFE(int id);


	
}
