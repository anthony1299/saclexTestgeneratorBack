package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Fichier;
import org.saclex.demo.repositories.FichierRepository;
import org.saclex.demo.service.FichierService;
import org.saclex.demo.service.FichierServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "fichier/")
@CrossOrigin("*")
public class FichierController {
    private final FichierService fichierService;

    public FichierController(FichierRepository fichierRepository) {
        fichierService = new FichierServiceImpl();
    }

    @GetMapping("listerFichiers")
    public List<Fichier> getAllFichier(){
        return fichierService.getAllFichiers();
    }

    @PostMapping("creerFichier")
    public Fichier createFichier(@RequestBody Fichier fichier){
        return fichierService.createFichier(fichier);
    }

    @PutMapping("modifierFichier")
    public Fichier updateFichier(@RequestBody Fichier fichier) throws Exception {
        if(fichier.getIdFichier() == null){
            throw new Exception("Fichier non existante");
        }

        return fichierService.updateFichier(fichier);
    }

    @DeleteMapping("supprimerFichier/{idFichier}")
    public void deleteFichier(@PathVariable Long idFichier){
        fichierService.deleteFichier(idFichier);
    }
}
