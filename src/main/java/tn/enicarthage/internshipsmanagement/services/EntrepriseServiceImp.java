package tn.enicarthage.internshipsmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.enicarthage.internshipsmanagement.entities.Entreprise;
import tn.enicarthage.internshipsmanagement.repos.EntrepriseRepos;

import java.util.List;


@RequiredArgsConstructor
@Service
public class EntrepriseServiceImp implements EntrepriseService{

    private final EntrepriseRepos entrepriseRepos;
    @Override
    public Entreprise saveEntreprise(Entreprise e) {
        return entrepriseRepos.save(e);
    }

    @Override
    public List<Entreprise> getALL() {
        return entrepriseRepos.findAll();
    }

    @Override
    public Entreprise updateEntreprise(Entreprise e) {
        return entrepriseRepos.save(e);
    }

    @Override
    public Entreprise getEntreprise(int id) {
        return entrepriseRepos.findById(id).get();
    }

    @Override
    public void deleteEntreprise(Entreprise e) {
        entrepriseRepos.delete(e);
    }

    @Override
    public void deleteEntrepriseById(int id) {
        entrepriseRepos.deleteById(id);
    }
}
