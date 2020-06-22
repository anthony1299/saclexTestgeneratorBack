package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.entities.VerificationToken;
import org.saclex.demo.repositories.UtilisateurRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.saclex.demo.repositories.VerificationTokenRepository;
import org.saclex.demo.service.UtilisateurService;
import org.saclex.demo.service.VerificationTokenService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("utilisateur")
@CrossOrigin("*")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private VerificationTokenService verificationTokenService;
    private JavaMailSender javaMailSender;
    private PasswordEncoder encoder;


    public UtilisateurController(UtilisateurService utilisateurService,VerificationTokenService verificationTokenService, JavaMailSender javaMailSender, PasswordEncoder encoder) {
        this.utilisateurService = utilisateurService;
        this.verificationTokenService = verificationTokenService;
        this.javaMailSender = javaMailSender;
        this.encoder = encoder;
    }

    //liste tous les utilisateurs
    @GetMapping("/listerUtilisateur")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateurs();
    }

    //liste seulement les responsables de themem
    @GetMapping("/listerResponsable")
    public List<Utilisateur> getResponsables(){
        return utilisateurService.findByRole(Utilisateur.Role.RESPONSABLE);
    }

    //Fonction qui crée les utilisateurs ayant pour role responsable
    @PostMapping("/creerUtilisateur")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur){
            utilisateur.setActive(true);
            utilisateur.setRole(Utilisateur.Role.RESPONSABLE);
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurService.createUtilisateur(utilisateur);
    }

    //Fonction qui cree les utilisateurs ayant pour rôle apprenant
    @PostMapping("/creerApprenant")
    public String createApprennant(@RequestBody Utilisateur utilisateur){
        //On definit son role et on le sauvegarde en BD son champs isActive est à false
        utilisateur.setRole(Utilisateur.Role.APPRENANT);
        utilisateurService.createUtilisateur(utilisateur);

        //On cree un token en fonction de l'utilisateur qui vient d'être créé
        VerificationToken verificationToken = new VerificationToken(utilisateur);
        verificationTokenService.createToken(verificationToken);

        //On envoi un mail à l'utilisateur avec un lien qui permettra d'activer son comppte pour
        // se rassurer que l'adresse mail saisie est valide
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(utilisateur.getEmail());
        message.setSubject("Inscription terminée");
        message.setText("Pour valider votre compte, cliquez ici: "
                    +"http://localhost:8087/verif-token?token="+verificationToken.getToken());

        javaMailSender.send(message);

        return "mail envoye";

    }
    //fonction qui see déclenche suite au clique sur le lien envoyé par mail
    @PostMapping("/verif-token")
    public String confirmationCompte(@RequestParam("token") String confirmToken){
        //Verification de l'existence du token
        VerificationToken verificationToken =verificationTokenService.findByToken(confirmToken);
        //Lorsque le token existe alors le champ isActive passe a true
         if(verificationToken != null){
             Utilisateur utilisateur=utilisateurService.findByEmailIgnoreCase(verificationToken.getUtilisateur().getEmail());
             utilisateur.setActive(true);
             utilisateurService.updateUtilisateur(utilisateur);

             return "success";
         }
         else {
             return "echec";
         }

    }


    @PutMapping("/modifierUtilisateur")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception {
        if(utilisateur.getId() == null){
            throw new Exception("Utilisateur inexistant");
        }

        return utilisateurService.updateUtilisateur(utilisateur);
    }

    @DeleteMapping("/supprimerUtilisateur/{idUtilisateur}")
    public void deleteTypeEvaluation(@PathVariable Long idUtilisateur){
        utilisateurService.deleteUtilisateur(idUtilisateur);
    }
}
