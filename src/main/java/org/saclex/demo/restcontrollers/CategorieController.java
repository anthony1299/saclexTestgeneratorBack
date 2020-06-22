package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorie/")
@CrossOrigin("*")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    //liste des categories
    @GetMapping("listerCategories")
    public List<Categorie> getAllCategories(){
        return categorieService.getAllCategories();
    }

    //creer une categorie
    @PostMapping("creerCategorie")
    public Categorie createCategorie(@RequestBody Categorie categorie){
        return categorieService.createCategorie(categorie);
    }

    //modifier une categorie
    @PutMapping("modifierCategorie")
    public Categorie updateCategorie(@RequestBody Categorie categorie) {

        return categorieService.updateCategorie(categorie);
    }

    //supprimer une categorie
    @DeleteMapping("supprimerCategorie/{idCategorie}")
    public void deleteReaction(@PathVariable Long idCategorie){
        categorieService.deleteCategorie(idCategorie);
    }
}
