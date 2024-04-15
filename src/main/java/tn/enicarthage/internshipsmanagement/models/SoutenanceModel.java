package tn.enicarthage.internshipsmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceModel {

    private LocalDateTime date;
    private Long president;
    private Long rapporteur;
    private int sfe;
    private int salle;
}
