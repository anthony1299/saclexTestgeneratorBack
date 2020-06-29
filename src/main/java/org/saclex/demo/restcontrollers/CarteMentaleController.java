package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.CarteMentale;
import org.saclex.demo.service.CarteMentaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartementale")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CarteMentaleController {


    private final CarteMentaleService carteMentaleserviceimpl;

    public CarteMentaleController(CarteMentaleService carteMentaleserviceimpl) {
        this.carteMentaleserviceimpl = carteMentaleserviceimpl;
    }

    //listes des cartes mentales
    @GetMapping("/listerCartes")
    public List<CarteMentale> getAllCarte(){
        return carteMentaleserviceimpl.cartes();
    }

    //creer une carte mentale
    @PostMapping("/creerCarte")
    public CarteMentale createCarte(@RequestBody CarteMentale carteMentale){
        return carteMentaleserviceimpl.saveCarte(carteMentale);
    }

    //modifier une carte mentale
    @PutMapping("/modifierCarte")
    public CarteMentale updateCarte(@RequestBody CarteMentale carteMentale) {
        return carteMentaleserviceimpl.saveCarte(carteMentale);
    }

    //suprimmer une carte mentale
    @DeleteMapping("/supprimerCarte/{idCarte}")
    public void deleteCarte(@PathVariable Long idCarte){
        carteMentaleserviceimpl.deleteCarte(idCarte);
    }
}
