package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Forfait;
import org.saclex.demo.repositories.ForfaitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/admin/")
@CrossOrigin
public class ForfaitController {

    private final ForfaitRepository forfaitRepository ;

    public ForfaitController(ForfaitRepository forfaitRepository) {
        this.forfaitRepository = forfaitRepository;
    }

    @GetMapping("listerForfaits")
    public List<Forfait> getAllForfait(){
        return forfaitRepository.findAll();
    }

    @PostMapping("creerForfait")
    public Forfait createForfait(@RequestBody Forfait forfait){
        return forfaitRepository.save(forfait);
    }

    @PutMapping("modifierForfait")
    public Forfait updateForfait(@RequestBody Forfait forfait) throws Exception {
        if(forfait.getIdForfait() == null){
            throw new Exception("Forfait non existante");
        }

        return forfaitRepository.save(forfait);
    }

    @DeleteMapping("supprimerForfait/{idForfait}")
    public void deleteForfait(@PathVariable Long idForfait){
        forfaitRepository.deleteById(idForfait);
    }
}
