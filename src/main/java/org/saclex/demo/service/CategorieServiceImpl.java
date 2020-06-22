package org.saclex.demo.service;

import org.saclex.demo.entities.Categorie;
import org.saclex.demo.repositories.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategorieServiceImpl implements CategorieService {

    CategorieRepository categorieRepository;
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
}
