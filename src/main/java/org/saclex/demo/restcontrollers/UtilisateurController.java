package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.entities.VerificationToken;
import org.saclex.demo.repositories.UtilisateurRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.saclex.demo.repositories.VerificationTokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private JavaMailSender javaMailSender;
    //private PasswordEncoder encoder;


    public UtilisateurController(UtilisateurRepository utilisateurRepository, VerificationTokenRepository verificationTokenRepository, JavaMailSender javaMailSender) {
        this.utilisateurRepository = utilisateurRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody Utilisateur utilisateur){
        return utilisateurRepository.findByLoginAndPassword(utilisateur.getLogin(),utilisateur.getPassword());
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request){
        String authToken=request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }


    @GetMapping("/admin/listerUtilisateur")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurRepository.findAll();
    }

    @PostMapping("/all/creerUtilisateur")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur){
            utilisateur.setActive(true);
       // utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }
    @PostMapping("/all/creerApprenant")
    public String createApprennant(@RequestBody Utilisateur utilisateur){
        utilisateur.setRole(Utilisateur.Role.apprenant);
        utilisateurRepository.save(utilisateur);
        VerificationToken verificationToken = new VerificationToken(utilisateur);
        verificationTokenRepository.save(verificationToken);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(utilisateur.getEmail());
        message.setSubject("Inscription terminée");
        message.setText("Pour valider votre compte, cliquez ici: "
                    +"http://localhost:8087/verif-token?token="+verificationToken.getToken());

        javaMailSender.send(message);

        return "mail envoye";

    }

    @PostMapping("/verif-token")
    public String confirmationCompte(@RequestParam("token") String confirmToken){
        VerificationToken verificationToken =verificationTokenRepository.findByToken(confirmToken);
         if(verificationToken != null){
             Utilisateur utilisateur=utilisateurRepository.findByEmailIgnoreCase(verificationToken.getUtilisateur().getEmail());
             utilisateur.setActive(true);
             utilisateurRepository.save(utilisateur);

             return "success";
         }
         else {
             return "echec";
         }

    }


    @PutMapping("/modifierUtilisateur")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception {
        if(utilisateur.getId() == null){
            throw new Exception("Utilisateur non existante");
        }

        return utilisateurRepository.save(utilisateur);
    }

    @DeleteMapping("/asmin/supprimerUtilisateur/{idUtilisateur}")
    public void deleteTypeEvaluation(@PathVariable Long idUtilisateur){
        utilisateurRepository.deleteById(idUtilisateur);
    }
}
