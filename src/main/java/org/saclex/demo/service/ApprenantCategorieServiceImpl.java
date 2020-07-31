package org.saclex.demo.service;

import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.entities.Categorie;
import org.saclex.demo.repositories.ApprenantCategorieRepository;
import org.saclex.demo.repositories.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApprenantCategorieServiceImpl implements ApprenantCategorieService {

    private final ApprenantCategorieRepository apprenantCategorieRepository;
    private final CategorieRepository categorieRepository;

    public ApprenantCategorieServiceImpl(ApprenantCategorieRepository apprenantCategorieRepository , CategorieRepository categorieRepository) {
        this.apprenantCategorieRepository = apprenantCategorieRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public ApprenantCategorie createAppCategorie(ApprenantCategorie apprenantCategorie) {
        return apprenantCategorieRepository.save( apprenantCategorie );
    }

    @Override
    public ApprenantCategorie getAppCategorieByUserAndCat(Long idUser , Long idCat) {
        return apprenantCategorieRepository.getApprenantCategorie( idUser,idCat );
    }

    @Override
    public ApprenantCategorie updateAppCategorie(ApprenantCategorie apprenantCategorie) {
        return apprenantCategorieRepository.save( apprenantCategorie );
    }

    @Override
    public List < ApprenantCategorie > getUserCats(Long iduser) {
        return apprenantCategorieRepository.getUserCategories( iduser );
    }

    @Override
    public List < ApprenantCategorie > getAppCatNotActive() {
        return apprenantCategorieRepository.getApprenantCategorieNotActive();
    }

    @Override
    public List < Categorie > getCatsNotSelect(Long idUser) {
        List < ApprenantCategorie > lcatuser= apprenantCategorieRepository.getUserCategoriInAppCat( idUser );
        List < Categorie > lcattotal= categorieRepository.findAll();
        List < Categorie > lcat= new ArrayList <>(  );
        List < Categorie > lcatnonutilise= new ArrayList <>(  );

        for( ApprenantCategorie a:lcatuser
              ) {
            lcat.add( a.getCat() );
        }
        for( Categorie c:lcattotal
              ) {
            if( lcat.contains( c ) ){
            }else {
                lcatnonutilise.add( c );
            }
        }

        return lcatnonutilise;
    }
}
