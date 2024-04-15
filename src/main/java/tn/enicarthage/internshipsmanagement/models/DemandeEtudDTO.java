package tn.enicarthage.internshipsmanagement.models;

public class DemandeEtudDTO {

	private int id;
	private String sujet;
	private String encadreur;
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
	public String getEncadreur() {
		return encadreur;
	}
	public void setEncadreur(String encadreur) {
		this.encadreur = encadreur;
	}
	@Override
	public String toString() {
		return "DemandeEtudDTO [id=" + id + ", sujet=" + sujet + ", encadreur=" + encadreur + "]";
	}
	public DemandeEtudDTO(int id, String sujet, String encadreur) {
		super();
		this.id = id;
		this.sujet = sujet;
		this.encadreur = encadreur;
	}
	public DemandeEtudDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
