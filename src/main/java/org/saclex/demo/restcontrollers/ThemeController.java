package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.CarteMentale;
import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Theme;
import org.saclex.demo.service.CarteMentaleService;
import org.saclex.demo.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theme/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ThemeController {

    private final ThemeService themeService;
    private final CarteMentaleService carteMentaleService;

    public ThemeController(ThemeService themeService, CarteMentaleService carteMentaleService) {
        this.themeService = themeService;
        this.carteMentaleService = carteMentaleService;
    }


    @GetMapping("listerTheme")
    public List<Theme> getAllTheme(){
        return themeService.getAllThemes();
    }

    //Retourne le nombre de catégories associées à un thème
    @GetMapping("categoriesParTheme/{idTheme}")
    public int getCategories(@PathVariable Long idTheme){
        Theme th= themeService.findById(idTheme);
        int i = th.getCategories().size();
        return i;
    }

    //Retourne le nombre de question par theme
    @GetMapping("questionsParTheme/{idTheme}")
    public int getNbreQuestions(@PathVariable Long idTheme){
        Theme th = themeService.findById(idTheme);
        List<Categorie> categories = th.getCategories();
        int nbreQuestion=0;
        for (Categorie categorie : categories){
            nbreQuestion=nbreQuestion+categorie.getQuestions().size();
        }

        return nbreQuestion;
    }

    //retourne le nombre de cartes mentales par theme
    @GetMapping("cartesParTheme/{idTheme}")
    public int getNbreCartes(@PathVariable Long idTheme){
        List<CarteMentale> carteMentales =carteMentaleService.cartes();
        Theme th = themeService.findById(idTheme);
        List<Categorie> categories = th.getCategories();
        List<Question> questions;
        int i;
        int nbreCartes=0;
        for (Categorie categorie : categories){
            questions=categorie.getQuestions();
            for (Question q: questions){
                for (CarteMentale c : carteMentales){
                    if (c.getQuest().getIdQuestion().equals(q.getIdQuestion())){
                        nbreCartes = nbreCartes + 1;
                    }
                }
            }
        }

        return nbreCartes;
    }

    //creation d'un thème
    @PostMapping("creerTheme")
    public Theme createTheme(@RequestBody Theme theme){
        return themeService.createTheme(theme);
    }

    //modification d'un thème
    @PutMapping("modifierTheme")
    public Theme updateTheme(@RequestBody Theme theme){
        return themeService.updateTheme(theme);
    }

    //Suppression d'un thème à l'aide de l'id
    @DeleteMapping("supprimerTheme/{idTheme}")
    public void deleteTheme(@PathVariable Long idTheme){
        themeService.deleteTheme(idTheme);
    }
}
