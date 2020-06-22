package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.EnvoiMail;
import org.saclex.demo.repositories.EnvoiMailRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class EnvoiMailController {

    private final EnvoiMailRepository envoiMailRepository;

    public EnvoiMailController(EnvoiMailRepository envoiMailRepository) {
        this.envoiMailRepository = envoiMailRepository;
    }

    @GetMapping("listerMailEnvoye")
    public List<EnvoiMail> getAllMailEnvoye(){
        return envoiMailRepository.findAll();
    }

    @PostMapping("creerMailEnvoye")
    public EnvoiMail createMailEnvoye(@RequestBody EnvoiMail envoiMail){
        return envoiMailRepository.save(envoiMail);
    }

}
