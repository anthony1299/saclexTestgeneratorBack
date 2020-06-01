package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.repositories.CategorieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/responsable/")
@CrossOrigin
public class CategorieController {

    private final CategorieRepository categorieRepository;

    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("listerCategories")
    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }

    @PostMapping("creerCategorie")
    public Categorie createCarte(@RequestBody Categorie categorie){
        return categorieRepository.save(categorie);
    }

    @PutMapping("modifierCategorie")
    public Categorie updateCategorie(@RequestBody Categorie categorie) {

        return categorieRepository.save(categorie);
    }

    @DeleteMapping("supprimerCategorie/{idCategorie}")
    public void deleteReaction(@PathVariable Long idCategorie){
        categorieRepository.deleteById(idCategorie);
    }
}
