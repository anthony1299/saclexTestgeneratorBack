package org.saclex.demo.service;

import org.saclex.demo.entities.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VerificationTokenService {

    //Fonction qui permet de sauvegarder un token en BD
     VerificationToken createToken(VerificationToken token);

     VerificationToken findByToken(String token);
}
