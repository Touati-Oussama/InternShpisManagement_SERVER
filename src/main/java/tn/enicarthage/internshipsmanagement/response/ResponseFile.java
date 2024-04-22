package tn.enicarthage.internshipsmanagement.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFile {
	  private String id;
	  private String name;
	  private String url;
	  private String type;
	  private long size;
	  private String sfe;
	  private String etudiant;


	}
