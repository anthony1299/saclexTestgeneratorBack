package org.saclex.demo.service;

import org.saclex.demo.entities.Theme;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ThemeServiceImpl implements ThemeService {

    ThemeRepository themeRepository;

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme findById(Long idTheme) {
        return themeRepository.findById(idTheme).get();
    }

    @Override
    public Theme updateTheme(Theme theme) {
        return  themeRepository.save(theme);
    }

    @Override
    public void deleteTheme(Long idTheme) {
        themeRepository.deleteById(idTheme);

    }

    @Override
    public List<Theme> findByUser(Utilisateur utilisateur) {
        return themeRepository.findByUtilisateur(utilisateur);
    }
}
