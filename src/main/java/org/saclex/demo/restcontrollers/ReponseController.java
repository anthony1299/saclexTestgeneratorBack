package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Reponse;
import org.saclex.demo.repositories.ReponseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsable/")
@CrossOrigin
public class ReponseController {

    private final ReponseRepository  reponseRepository ;

    public ReponseController(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

    @GetMapping("listerReponse")
    public List<Reponse> getAllReponse(){
        return reponseRepository.findAll();
    }

    @PostMapping("creerReponse")
    public Reponse createReponse(@RequestBody Reponse reponse){
        return reponseRepository.save(reponse);
    }

    @PutMapping("modifierReponse")
    public Reponse updateReponse(@RequestBody Reponse reponse) throws Exception {
        if(reponse.getIdReponse() == null){
            throw new Exception("Reponse non existante");
        }

        return reponseRepository.save(reponse);
    }

    @DeleteMapping("supprimerReponse/{idReponse}")
    public void deleteReponse(@PathVariable Long idReponse){
        reponseRepository.deleteById(idReponse);
    }
}
