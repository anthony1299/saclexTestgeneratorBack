package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Fichier;
import org.saclex.demo.repositories.FichierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/responsable/")
@CrossOrigin
public class FichierController {
    private final FichierRepository fichierRepository;

    public FichierController(FichierRepository fichierRepository) {
        this.fichierRepository = fichierRepository;
    }

    @GetMapping("listerFichiers")
    public List<Fichier> getAllFichier(){
        return fichierRepository.findAll();
    }

    @PostMapping("creerFichier")
    public Fichier createFichier(@RequestBody Fichier fichier){
        return fichierRepository.save(fichier);
    }

    @PutMapping("modifierFichier")
    public Fichier updateFichier(@RequestBody Fichier fichier) throws Exception {
        if(fichier.getIdFichier() == null){
            throw new Exception("Fichier non existante");
        }

        return fichierRepository.save(fichier);
    }

    @DeleteMapping("supprimerFichier/{idFichier}")
    public void deleteFichier(@PathVariable Long idFichier){
        fichierRepository.deleteById(idFichier);
    }
}
