package tn.enicarthage.internshipsmanagement.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandeInscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true)
	private String username;
	private String nom;
	private String prenom;
	private Long telephone;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private ERole role;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Department department;
}
