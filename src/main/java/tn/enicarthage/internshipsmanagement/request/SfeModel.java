package tn.enicarthage.internshipsmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SfeModel {
    private Long etudiantId;
    private Long encadreurId;
    private String sujet;
}
