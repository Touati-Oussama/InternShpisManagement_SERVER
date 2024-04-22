package tn.enicarthage.internshipsmanagement.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.enicarthage.internshipsmanagement.entities.SFE;
import tn.enicarthage.internshipsmanagement.entities.TrelloBoard;

public interface TrelloBoardRepository extends JpaRepository<TrelloBoard,String> {

	TrelloBoard findBySfe(SFE s);
}
