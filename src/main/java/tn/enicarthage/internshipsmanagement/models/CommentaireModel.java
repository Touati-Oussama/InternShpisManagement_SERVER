package tn.enicarthage.internshipsmanagement.models;

import java.time.LocalDateTime;

public class CommentaireModel {

	private int id;
	private String commentaire;
	private String owner;
	private LocalDateTime date;
	private int sfe;
	public int getSfe() {
		return sfe;
	}
	public void setSfe(int sfe) {
		this.sfe = sfe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CommentaireModel [id=" + id + ", commentaire=" + commentaire + ", owner=" + owner + ", date=" + date
				+ "]";
	}
	public CommentaireModel(int id, String commentaire, String owner, LocalDateTime date) {
		super();
		this.id = id;
		this.commentaire = commentaire;
		this.owner = owner;
		this.date = date;
	}
	public CommentaireModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
