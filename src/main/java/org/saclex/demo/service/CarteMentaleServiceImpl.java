package org.saclex.demo.service;

import org.saclex.demo.entities.CarteMentale;
import org.saclex.demo.repositories.CarteMentaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarteMentaleServiceImpl implements CarteMentaleService {
   final
   CarteMentaleRepository carteMentaleRepository;

    public CarteMentaleServiceImpl(CarteMentaleRepository carteMentaleRepository) {
        this.carteMentaleRepository = carteMentaleRepository;
    }

    @Override
    public List<CarteMentale> cartes() {
        return carteMentaleRepository.findAll();
    }

    @Override
    public CarteMentale saveCarte(CarteMentale carteMentale) {
        return carteMentaleRepository.save(carteMentale);
    }

    @Override
    public CarteMentale updateCarte(CarteMentale carteMentale) {
        return null;
    }

    @Override
    public void deleteCarte(Long idCarte) {
        carteMentaleRepository.deleteById(idCarte);
    }
}
