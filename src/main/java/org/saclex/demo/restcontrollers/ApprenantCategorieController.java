package org.saclex.demo.restcontrollers;


import org.saclex.demo.entities.ApprenantCategorie;
import org.saclex.demo.entities.Categorie;
import org.saclex.demo.service.ApprenantCategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("apprenantcategorie/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ApprenantCategorieController {

    private final ApprenantCategorieService apprenantCategorieService;

    public ApprenantCategorieController(ApprenantCategorieService apprenantCategorieService) {
        this.apprenantCategorieService = apprenantCategorieService;
    }

    @PostMapping("creerAppCategorie")
        public ApprenantCategorie createCategorie (@RequestBody ApprenantCategorie app){
            return apprenantCategorieService.createAppCategorie( app );
        }
    @GetMapping("categorieUser/{idUser}")
    public List < Categorie >getcategories(@PathVariable Long idUser){
        List < ApprenantCategorie > list=apprenantCategorieService.getUserCats( idUser );
        List < Categorie > lcat=new ArrayList <>(  );
        for( ApprenantCategorie a: list ){
            Categorie c= new Categorie(  );
            c.setIdCategorie( a.getCat().getIdCategorie() );
            c.setLibelle( a.getCat().getLibelle() );
            lcat.add( c );
        }
        return lcat;
    }

    }

