package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Reponse;
import org.saclex.demo.service.ReponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reponse/")
@CrossOrigin("*")
public class ReponseController {

    private final ReponseService reponseService ;

    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }


    @GetMapping("listerReponse")
    public List<Reponse> getAllReponse(){
        return reponseService.getAllReponses();
    }

    @PostMapping("creerReponse")
    public Reponse createReponse(@RequestBody Reponse reponse){
        return reponseService.createReponse(reponse);
    }

    @PutMapping("modifierReponse")
    public Reponse updateReponse(@RequestBody Reponse reponse) throws Exception {
        if(reponse.getIdReponse() == null){
            throw new Exception("Reponse inexistante");
        }

        return reponseService.updateReponse(reponse);
    }

    @DeleteMapping("supprimerReponse/{idReponse}")
    public void deleteReponse(@PathVariable Long idReponse){
        reponseService.deleteReponse(idReponse);
    }
}
