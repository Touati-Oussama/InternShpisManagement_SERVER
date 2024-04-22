package tn.enicarthage.internshipsmanagement.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBoard {

    @Id
    private String id;
    private String url;
    @OneToOne(cascade = CascadeType.ALL)
    private SFE sfe;




}

