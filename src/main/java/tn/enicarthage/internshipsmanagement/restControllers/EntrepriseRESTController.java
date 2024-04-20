package tn.enicarthage.internshipsmanagement.restControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.internshipsmanagement.entities.Commentaire;
import tn.enicarthage.internshipsmanagement.entities.Entreprise;
import tn.enicarthage.internshipsmanagement.entities.SFE;
import tn.enicarthage.internshipsmanagement.entities.Salle;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.services.EntrepriseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entreprises")
@RequiredArgsConstructor
@CrossOrigin
public class EntrepriseRESTController {

    private final EntrepriseService entrepriseService;

    @RequestMapping(method= RequestMethod.GET)
    List<Entreprise> getAllEntreprises(){
        return entrepriseService.getALL();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEntreprise(@RequestBody Entreprise entreprise) {
        return ResponseEntity.ok(entrepriseService.saveEntreprise(entreprise));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateEtudiant(@RequestBody Entreprise e, @PathVariable("id") int id ) {
        Entreprise ent = this.entrepriseService.getEntreprise(id);
        ent.setNom(e.getNom());
        ent.setAdresse(e.getAdresse());
        ent.setSpecialite(e.getSpecialite());
        ent.setRepresentant(e.getRepresentant());
        ent.setTelephone(e.getTelephone());
        ent.setEmail(e.getEmail());
        return ResponseEntity.ok(this.entrepriseService.updateEntreprise(ent));
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Entreprise getSFEById(@PathVariable("id") int id)
    {
        return entrepriseService.getEntreprise((id));
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> deleteById(@PathVariable("id") int id)
    {
        entrepriseService.deleteEntrepriseById((id));
        return  ResponseEntity.ok(new JSONResponse(("L'entreprise est supprimée !")));
    }
}
