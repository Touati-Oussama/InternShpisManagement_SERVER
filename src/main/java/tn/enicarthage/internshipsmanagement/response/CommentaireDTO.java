package tn.enicarthage.internshipsmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {

	private int id;
	private String commentaire;
	private String owner;
	private LocalDateTime date;
	private int sfe;


	
	
	
}
