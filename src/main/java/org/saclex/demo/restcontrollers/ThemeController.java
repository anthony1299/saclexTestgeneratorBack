package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Theme;
import org.saclex.demo.repositories.ThemeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
public class ThemeController {

    private final ThemeRepository themeRepository;

    public ThemeController(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @GetMapping("/listerTheme")
    public List<Theme> getAllTheme(){
        return themeRepository.findAll();
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
