package org.saclex.demo.service;

import org.saclex.demo.entities.Fichier;
import org.saclex.demo.repositories.FichierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FichierServiceImpl implements FichierService {

final
FichierRepository fichierRepository;

    public FichierServiceImpl(FichierRepository fichierRepository) {
        this.fichierRepository = fichierRepository;
    }

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
