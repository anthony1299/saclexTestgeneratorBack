package org.saclex.demo.security;


import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Date;

/**
 * classe utilisée pour insérer quelques utilisateurs tests en BD
 */

@Service
public class dbInit{
    private UtilisateurRepository utilisateurRepository;
    private DataSource dataSource;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public dbInit(UtilisateurRepository utilisateurRepository, DataSource dataSource) {
        this.utilisateurRepository = utilisateurRepository;
        this.dataSource = dataSource;
    }

   /* @Override
    public void run(String... args) throws Exception {
        if(utilisateurRepository.findAll().size() == 0) {
            Utilisateur admin = new Utilisateur();
            admin.setLogin("admin");
            admin.setRole(Utilisateur.Role.ADMINISTRATEUR);
            admin.setActive(true);
            admin.setPassword(bCryptPasswordEncoder.encode("admin"));
            Utilisateur vie = new Utilisateur();
            vie.setRole(Utilisateur.Role.RESPONSABLE);
            vie.setActive(true);
            utilisateurRepository.save(admin);
            utilisateurRepository.save(vie);
        }*/
    //}
}
