package tn.enicarthage.internshipsmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SFE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sujet;

    @OneToOne(cascade = CascadeType.ALL)
    private User etudiant;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User encadreur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sfe")
    List<Commentaire> commentaires;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sfe")
    @JsonIgnore
    private List<FileDB> files;

    @OneToOne(mappedBy = "sfe")
    @JsonIgnore
    private Soutenance soutenance;
}
