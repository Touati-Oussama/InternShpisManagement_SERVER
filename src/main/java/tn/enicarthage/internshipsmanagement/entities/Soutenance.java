package tn.enicarthage.internshipsmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soutenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Salle salle;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User president;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User rapporteur;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SFE sfe;

	@OneToOne(mappedBy = "soutenance")
	@JsonIgnore
	private Note note;



	

	
	
	
}
