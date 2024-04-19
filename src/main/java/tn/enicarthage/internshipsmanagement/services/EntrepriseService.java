package tn.enicarthage.internshipsmanagement.services;

import tn.enicarthage.internshipsmanagement.entities.Entreprise;

import java.util.List;

public interface EntrepriseService {

    Entreprise saveEntreprise(Entreprise e);
    List<Entreprise> getALL();
    Entreprise updateEntreprise(Entreprise e);
    Entreprise getEntreprise(int id);
    void deleteEntreprise(Entreprise e);
    void deleteEntrepriseById(int id);
}
