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
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
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


    public SecurityConfiguration(DetailsUtilisateurService detailsUtilisateurService , UtilisateurRepository utilisateurRepository , UtilisateurService utilisateurService ) {
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
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.utilisateurRepository))
                .authorizeRequests()
                .antMatchers( "/login" ).permitAll()
                .antMatchers("/theme/creerTheme").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/theme/modifierTheme").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/theme/supprimerTheme/**").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/categorie/creercategorie").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/categorie/modifiercategorie").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/categorie/supprimercategorie/**").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/utilisateur/creerUtilisateur/**").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/utilisateur/creerRespCategorie/**").hasRole( Utilisateur.Role.ADMINISTRATEUR.toString() )
                .antMatchers("/categorie/creercategorie").hasRole( Utilisateur.Role.RESPONSABLE_THEME.toString() )
                .antMatchers("/categorie/modifiercategorie").hasRole( Utilisateur.Role.RESPONSABLE_THEME.toString() )
                .antMatchers("/categorie/supprimercategorie/**").hasRole( Utilisateur.Role.RESPONSABLE_THEME.toString() )
                .antMatchers("/evaluation/lasteval/**").hasRole( Utilisateur.Role.RESPONSABLE_THEME.toString() );
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
