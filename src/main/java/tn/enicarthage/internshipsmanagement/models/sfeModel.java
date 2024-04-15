package tn.enicarthage.internshipsmanagement.models;

public class sfeModel {

	private int id;
	private String sujet;
	private String encadreur;
	private String etudiant;
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
	public String getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}
	@Override
	public String toString() {
		return "sfeModel [id=" + id + ", sujet=" + sujet + ", encadreur=" + encadreur + ", etudiant=" + etudiant + "]";
	}
	public sfeModel(int id, String sujet, String encadreur, String etudiant) {
		super();
		this.id = id;
		this.sujet = sujet;
		this.encadreur = encadreur;
		this.etudiant = etudiant;
	}
	public sfeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
