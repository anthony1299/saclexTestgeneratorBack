package org.saclex.demo.security;

import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.repositories.UtilisateurRepository;
import org.saclex.demo.service.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Classe utilisée pour paramétrer spring security
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DetailsUtilisateurService detailsUtilisateurService;
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurService utilisateurService;

    public SecurityConfiguration(DetailsUtilisateurService detailsUtilisateurService, UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.detailsUtilisateurService = detailsUtilisateurService;
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    //pour paramétrer le gestionnaire d'authentification
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    //pour filtrer les requetes http
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthentificationFilter(authenticationManager(), utilisateurService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),utilisateurRepository))
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/utilisateur/creerUtilisateur").permitAll()
                .antMatchers("evaluation/creerEvaluation/**").permitAll()
                .antMatchers("theme/listerTheme").permitAll()
                .antMatchers("/theme/creerTheme").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/theme/modifierTheme").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/theme/supprimerTheme").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/question/creerQuestion").permitAll()
                //Autorisations pour les categories
                .antMatchers("/categorie/creerCategorie/**").hasRole(Utilisateur.Role.RESPONSABLE_THEME.toString())
                .antMatchers("/categorie/modifierCategorie").hasRole(Utilisateur.Role.RESPONSABLE_THEME.toString())
                .antMatchers("/categorie/supprimerCategorie/**").hasRole(Utilisateur.Role.RESPONSABLE_THEME.toString())
                .antMatchers("/categorie/listerCategories").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/categorie/getCatByTheme").permitAll()
                //Autorisations pour le responsable
                .antMatchers("/utilisateur/creerUtilisateur").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/utilisateur/listerResponsableTheme").permitAll()
                .antMatchers("/utilisateur/listerResponsableCategorie").permitAll()
                .antMatchers("/utilisateur/supprimerUtilisateur/**").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                //Autorisations pour l'apprenant
                .antMatchers("/utilisateur/creerApprenant").permitAll()
                .antMatchers("/utilisateur/verif-token").permitAll()
                //Autorisations pour les questions
                .antMatchers("/question/creerQuestion").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                .antMatchers("/question/modifierQuestion").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                .antMatchers("/question/supprimerQuestion/**").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                //Autorisations pour les reponses
                .antMatchers("/reponse/creerReponse").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                .antMatchers("/reponse/modifierReponse").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                .antMatchers("/reponse/savereponses").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                .antMatchers("/reponse/supprimerReponse").hasRole(Utilisateur.Role.RESPONSABLE_CATEGORIE.toString())
                //Autorisations sur les evaluations
                .antMatchers("evaluation/creerEvaluation/**").permitAll()
                //hasRole(Utilisateur.Role.APPRENANT.toString())
                //Autorisation sur les reponses aux evaluations
                .antMatchers("reponseEval/creerReponse/**").hasRole(Utilisateur.Role.APPRENANT.toString())

                .antMatchers("/question/listerReponses").permitAll()
                .antMatchers("/reponseEval/creerReponse/**").permitAll()
                .antMatchers("/fichier/creerFichier").hasRole(Utilisateur.Role.RESPONSABLE_THEME.toString())
                //hasRole(Utilisateur.Role.RESPONSABLE.toString())
                .antMatchers("/reponse/creerReponse").permitAll()
                .antMatchers("/question/listerQuestions").permitAll()
                //hasRole(Utilisateur.Role.RESPONSABLE.toString())
               // .antMatchers("/utilisateur/creerUtilisateur").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/typequestion/creerTypeQuestion").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString())
                .antMatchers("/typeevaluation/creerTypeEvaluation").hasRole(Utilisateur.Role.ADMINISTRATEUR.toString());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setExposedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept","x-ijt",JwtProperties.HEADER_STRING,JwtProperties.TIME_EXPIRATION));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization",JwtProperties.TIME_EXPIRATION,"x-ijt"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    //pour définir l'authentification basée sur une BD
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(detailsUtilisateurService);
        return daoAuthenticationProvider;
    }

    // pour définir comment les mots de passe seront hachés
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
