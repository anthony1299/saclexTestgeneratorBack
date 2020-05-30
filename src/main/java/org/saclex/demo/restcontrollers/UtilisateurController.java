package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder encoder;


    public UtilisateurController(UtilisateurRepository utilisateurRepository, PasswordEncoder encoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.encoder = encoder;
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

        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
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
