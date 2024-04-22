package tn.enicarthage.internshipsmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceDTO1 {

    private int id;
    private String sfe;
    private String encadreur;
    private String president;
    private String rapporteur;
    private String salle;
    private LocalDateTime date;

    private List<File> files;

}
