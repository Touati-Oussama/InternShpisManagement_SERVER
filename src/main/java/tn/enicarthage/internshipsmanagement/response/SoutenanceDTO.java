package tn.enicarthage.internshipsmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceDTO {

	private int id;
	private String sfe;
	private String encadreur;
	private String president;
	private String rapporteur;
	private String salle;
	private LocalDateTime date;
	private String idFile;
	private String fileDownloadUri;
	private String rapport;

}
