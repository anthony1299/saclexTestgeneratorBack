package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Question;
import org.saclex.demo.entities.Reponse;
import org.saclex.demo.service.ReponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reponse/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
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

    @PostMapping("savereponses")
    public void saveReponses(@RequestBody List<Reponse> reponses){
        reponseService.saveAllReponse(reponses);
    }

    @PostMapping("correctAnswer/{idQuestion}")
    public List<Reponse> correction(@PathVariable Long idQuestion){
       return reponseService.findcorrectAnswer(idQuestion);
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
