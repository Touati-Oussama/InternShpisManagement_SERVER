package tn.enicarthage.internshipsmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.internshipsmanagement.Primary_Keys.Etudiant_Encadreur_PK;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeEncadremetnModel {

    private Long etudiantId;
    private Long encadreurId;
    private String etat;
    private String sujet;
}
