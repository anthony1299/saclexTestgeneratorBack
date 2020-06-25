package org.saclex.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.service.UtilisateurService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static java.lang.System.out;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UtilisateurService utilisateurService;

    public JwtAuthentificationFilter(AuthenticationManager authenticationManager, UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
    }

    //fonction utilisée quand on tente de se connecter
    //elle est déclenchée quand on effectue une requête POST à /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            //ici on prend les paramètres de connexion venant de la requete et on les mappe à un objet LoginViewModel
        LoginViewModel infosLogin = null;
        try {
            infosLogin = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //on crée un token de connexion, NB: ceci n'est pas le JWT
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                Objects.requireNonNull(infosLogin).getUsername(),
                infosLogin.getPassword(),
                new ArrayList<>()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return authentication;

    }

    //fonction utilisée quand l'authentification a réussi, on construit le token JWT
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      DetailsUtilisateur detailsUtilisateur = (DetailsUtilisateur) authResult.getPrincipal();
      //On crée le jwt token
      String token = JWT.create()
              .withSubject(detailsUtilisateur.getUsername())
              .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.DATE_EXPIRATION))
              .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        //on ajoute ce token à l'en tête de la requête http
      response.addHeader(JwtProperties.HEADER_STRING,JwtProperties.TOKEN_PREFIX + token);
       Utilisateur u = utilisateurService.findByLogin(detailsUtilisateur.getUsername());
       Gson gson =new Gson();
        String utilisateurGson = gson.toJson(u);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(utilisateurGson);
        out.flush();
    }

}
