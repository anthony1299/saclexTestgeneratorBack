package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.service.CategorieService;
import org.saclex.demo.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorie/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategorieController {

    private final CategorieService categorieService;
    private final UtilisateurService utilisateurService;

    public CategorieController(CategorieService categorieService, UtilisateurService utilisateurService) {
        this.categorieService = categorieService;
        this.utilisateurService=utilisateurService;
    }

    //liste des categories
    @GetMapping("listerCategories")
    public List<Categorie> getAllCategories(){
        return categorieService.getAllCategories();
    }

    //creer une categorie
    @PostMapping("creerCategorie/{idUser}")
    public Categorie createCategorie(@RequestBody Categorie categorie,@PathVariable(name="idUser") Long idUser){
        categorie.setRespCat(utilisateurService.findById(idUser));
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
