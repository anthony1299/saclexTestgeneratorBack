package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.entities.Theme;
import org.saclex.demo.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategorieServiceImpl implements CategorieService {

    final
    CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);

    }

    @Override
    public List<Categorie> findByTheme(Theme theme) {
        return categorieRepository.findByTheme(theme);
    }
}
