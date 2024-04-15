package tn.enicarthage.internshipsmanagement.models;

public class DemandeEnDTO {


	private int id;
	private String sujet;
	private String etudiant;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}


	public DemandeEnDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DemandeEnDTO(int id, String sujet, String etudiant, String email) {
		super();
		this.id = id;
		this.sujet = sujet;
		this.etudiant = etudiant;
		this.email = email;
	}
	@Override
	public String toString() {
		return "DemandeEnDTO [id=" + id + ", sujet=" + sujet + ", etudiant=" + etudiant + ", email=" + email + "]";
	}
	
	
	
}
