package tn.enicarthage.internshipsmanagement.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.repos.CommentaireRepository;
import tn.enicarthage.internshipsmanagement.response.CommentaireDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentaireServiceImp implements CommentaireService{
	
	private final CommentaireRepository commentaireRepository;
	@Override
	public Commentaire saveCommentaire(Commentaire e) {
		return commentaireRepository.save(e);
	}

	@Override
	public Commentaire updateCommentaire(Commentaire e) {
		return commentaireRepository.save(e);
	}

	@Override
	public void deleteCommentaire(Commentaire e) {
		commentaireRepository.delete(e);
		
	}

	@Override
	public void deleteCommentaireById(int id) {
		commentaireRepository.deleteById(id);
	}

	@Override
	public Commentaire getCommentaire(int id) {
		return commentaireRepository.findById(id).get();
	}

	@Override
	public List<CommentaireDTO> getAllCommentaires() {
		return commentaireRepository.findAll().stream()
				.map(tmp -> {
					CommentaireDTO d = new CommentaireDTO();
					d.setId(tmp.getId());
					d.setCommentaire(tmp.getCommentaire());
					d.setOwner(tmp.getOwner());
					d.setDate(tmp.getDate());
					d.setSfe(tmp.getSfe().getId());
					return d;
				})
				.collect(Collectors.toList());
	}


	@Override
	public List<CommentaireDTO> getCommentairesBySFE(int id) {
		return this.commentaireRepository.getCommentairesBySFE(id).stream()
				.map(tmp -> {
					CommentaireDTO d = new CommentaireDTO();
					d.setId(tmp.getId());
					d.setCommentaire(tmp.getCommentaire());
					d.setOwner(tmp.getOwner());
					d.setDate(tmp.getDate());
					d.setSfe(tmp.getSfe().getId());
					return d;
				})
				.collect(Collectors.toList());
	}



}
