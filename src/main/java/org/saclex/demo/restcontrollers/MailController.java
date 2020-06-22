package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Mail;
import org.saclex.demo.repositories.MailRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class MailController {
    private final MailRepository mailRepository;

    public MailController(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @GetMapping("listerMail")
    public List<Mail> getAllMail(){
        return mailRepository.findAll();
    }

    @PostMapping("creerMail")
    public Mail createMail(@RequestBody Mail mail){
        return mailRepository.save(mail);
    }


    @DeleteMapping("supprimerMail/{idMail}")
    public void deleteMail(@PathVariable Long idMail){
        mailRepository.deleteById(idMail);
    }
}
