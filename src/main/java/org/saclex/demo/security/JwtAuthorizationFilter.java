package org.saclex.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
   private UtilisateurRepository utilisateurRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager , UtilisateurRepository utilisateurRepository
                                  ) {
        super(authenticationManager);
       this.utilisateurRepository = utilisateurRepository;
    }

    //méthode utilisée pour extraire le login du token et obtenir l'objet utilisateur
    //Ceci afin d'obtenir ses rôles de la BD
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        /*
        Si l'entête est nulle ou ne commence pas avec "Bearer " on délègue les opérations
        à spring et on sort de la méthode
         */
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);

        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token.replace(JwtProperties.TOKEN_PREFIX,""))
                .getSubject();

        if(username !=null){
            Utilisateur utilisateur = utilisateurRepository.findByLogin(username);
            DetailsUtilisateur detailsUtilisateur = new DetailsUtilisateur(utilisateur);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,
                            null,detailsUtilisateur.getAuthorities());
            return  authenticationToken;
        }
        return null;
    }

}
