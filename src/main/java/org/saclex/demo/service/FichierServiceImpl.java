package org.saclex.demo.service;

import org.saclex.demo.entities.Fichier;
import org.saclex.demo.repositories.FichierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FichierServiceImpl implements FichierService {


    FichierRepository fichierRepository;

    @Override
    public List<Fichier> getAllFichiers() {
        return fichierRepository.findAll();
    }

    @Override
    public Fichier createFichier(Fichier fichier) {
        return fichierRepository.save(fichier);
    }

    @Override
    public Fichier updateFichier(Fichier fichier) {
        return fichierRepository.save(fichier);
    }

    @Override
    public void deleteFichier(Long idFichier) {
        fichierRepository.deleteById(idFichier);
    }
}
