package org.saclex.demo.service;


import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    final
    UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUtilisateur(Long idUtilisateur) {
        utilisateurRepository.deleteById(idUtilisateur);

    }

    @Override
    public List<Utilisateur> findByRole(Utilisateur.Role role) {
        return utilisateurRepository.findByRole(role);
    }

    @Override
    public Utilisateur findByEmailIgnoreCase(String email) {
        return utilisateurRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Utilisateur findByLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }

    @Override
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).get();
    }
}
