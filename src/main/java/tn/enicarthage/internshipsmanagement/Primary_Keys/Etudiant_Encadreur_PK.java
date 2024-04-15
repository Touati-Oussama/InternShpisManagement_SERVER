package tn.enicarthage.internshipsmanagement.Primary_Keys;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant_Encadreur_PK implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long etudiantId;
    private Long encadreurId;
}
