package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.CarteMentale;
import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Theme;
import org.saclex.demo.repositories.CarteMentaleRepository;
import org.saclex.demo.repositories.ThemeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ThemeController {

    private final ThemeRepository themeRepository;
    private final CarteMentaleRepository carteMentaleRepository;

    public ThemeController(ThemeRepository themeRepository, CarteMentaleRepository carteMentaleRepository) {
        this.themeRepository = themeRepository;
        this.carteMentaleRepository = carteMentaleRepository;
    }

    @GetMapping("/listerTheme")
    public List<Theme> getAllTheme(){
        return themeRepository.findAll();
    }

    @GetMapping("/categoriesParTheme/{idTheme}")
    public List<Categorie> getCategories(@PathVariable Long idTheme){
        return themeRepository.findById(idTheme).get().getCategories();
    }

    @GetMapping("/questionsParTheme/{idTheme}")
    public int getNbreQuestions(@PathVariable Long idTheme){
        Theme th = themeRepository.findById(idTheme).get();
        List<Categorie> categories = th.getCategories();
        int i;
        int nbreQuestion=0;
        for (Categorie categorie : categories){
            nbreQuestion=nbreQuestion+categorie.getQuestions().size();
        }

        return nbreQuestion;
    }

    @GetMapping("/cartesParTheme/{idTheme}")
    public int getNbreCartes(@PathVariable Long idTheme){
        List<CarteMentale> carteMentales =carteMentaleRepository.findAll();
        Theme th = themeRepository.findById(idTheme).get();
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


    @PostMapping("/admin/creerTheme")
    public Theme createTheme(@RequestBody Theme theme){
        return themeRepository.save(theme);
    }

    @PutMapping("/admin/modifierTheme")
    public Theme updateTheme(@RequestBody Theme theme){
        return themeRepository.save(theme);
    }

    @DeleteMapping("/admin/supprimerTheme/{idTheme}")
    public void deleteTheme(@PathVariable Long idTheme){
        themeRepository.deleteById(idTheme);
    }
}
