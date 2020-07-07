package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Theme;
import org.saclex.demo.service.CategorieService;
import org.saclex.demo.service.ThemeService;
import org.saclex.demo.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorie/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategorieController {

    private final CategorieService categorieService;
    private final UtilisateurService utilisateurService;
    private final ThemeService themeService;

    public CategorieController(CategorieService categorieService, UtilisateurService utilisateurService, ThemeService themeService) {
        this.categorieService = categorieService;
        this.utilisateurService=utilisateurService;
        this.themeService = themeService;
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

    /*@GetMapping("getCatByTheme")
    public List<Categorie> getCatByTheme(@RequestBody Theme theme){
        return categorieService.findByTheme(theme);
    }*/

    @GetMapping("getCatByTheme/{idTheme}")
    public List<Categorie> getCatByTheme(@PathVariable Long idTheme){
        Theme theme = themeService.findById(idTheme);
        return categorieService.findByTheme(theme);
    }
    //supprimer une categorie
    @DeleteMapping("supprimerCategorie/{idCategorie}")
    public void deleteReaction(@PathVariable Long idCategorie){
        categorieService.deleteCategorie(idCategorie);
    }
}
