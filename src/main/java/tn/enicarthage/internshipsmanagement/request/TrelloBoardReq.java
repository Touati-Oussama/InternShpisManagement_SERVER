package tn.enicarthage.internshipsmanagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrelloBoardReq {

	private String idBoard;
	private String url;
	private Long userId;
	
}
