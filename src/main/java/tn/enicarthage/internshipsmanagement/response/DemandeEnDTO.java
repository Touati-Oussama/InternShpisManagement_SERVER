package tn.enicarthage.internshipsmanagement.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeEnDTO {

    private int id;
    private String sujet;
    private String etudiant;
    private String email;
    private String etat;
    private String encadreur;
    private Long idEtud;
    private Long idEns;




}
