package tn.enicarthage.internshipsmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "salle")
	@JsonIgnore
	private List<Soutenance> soutenances;

    
    
}
