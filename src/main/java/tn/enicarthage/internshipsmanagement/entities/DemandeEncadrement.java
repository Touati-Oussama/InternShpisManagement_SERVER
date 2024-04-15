package tn.enicarthage.internshipsmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.enicarthage.internshipsmanagement.Primary_Keys.Etudiant_Encadreur_PK;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemandeEncadrement {

    @EmbeddedId
	private Etudiant_Encadreur_PK id;

	private String sujet;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne
    @JoinColumn(name = "etudiantId", referencedColumnName =
            "userId", insertable = false , updatable = false)
    private User etudiant;

    @ManyToOne
    @JoinColumn(name = "encadreurId", referencedColumnName =
            "userId", insertable = false , updatable = false)
    private User encadreur;

    


}
