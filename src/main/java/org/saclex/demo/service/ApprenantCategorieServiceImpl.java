package org.saclex.demo.service;

import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.repositories.ApprenantCategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApprenantCategorieServiceImpl implements ApprenantCategorieService {

    private final ApprenantCategorieRepository apprenantCategorieRepository;

    public ApprenantCategorieServiceImpl(ApprenantCategorieRepository apprenantCategorieRepository) {
        this.apprenantCategorieRepository = apprenantCategorieRepository;
    }

    @Override
    public ApprenantCategorie createAppCategorie(ApprenantCategorie apprenantCategorie) {
        return apprenantCategorieRepository.save( apprenantCategorie );
    }

    @Override
    public ApprenantCategorie updateAppCategorie(ApprenantCategorie apprenantCategorie) {
        return apprenantCategorieRepository.save( apprenantCategorie );
    }

    @Override
    public List < ApprenantCategorie > getUserCats(Long iduser) {
        return apprenantCategorieRepository.getUserCategories( iduser );
    }
}
