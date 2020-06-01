package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.CarteMentale;
import org.saclex.demo.repositories.CarteMentaleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class CarteMentaleController {

    private final CarteMentaleRepository carteMentaleRepository;

    public CarteMentaleController(CarteMentaleRepository carteMentaleRepository) {
        this.carteMentaleRepository = carteMentaleRepository;
    }

    @GetMapping("/listerCartes")
    public List<CarteMentale> getAllCarte(){
        return carteMentaleRepository.findAll();
    }

    @PostMapping("/responsable/creerCarte")
    public CarteMentale createCarte(@RequestBody CarteMentale carteMentale){
        return carteMentaleRepository.save(carteMentale);
    }

    @PutMapping("/responsable/modifierCarte")
    public CarteMentale updateCarte(@RequestBody CarteMentale carteMentale) {
        return carteMentaleRepository.save(carteMentale);
    }

    @DeleteMapping("/responsable/supprimerCarte/{idCarte}")
    public void deleteCarte(@PathVariable Long idCarte){
        carteMentaleRepository.deleteById(idCarte);
    }
}
