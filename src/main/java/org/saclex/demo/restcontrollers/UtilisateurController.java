package org.saclex.demo.restcontrollers;

import groovy.text.Template;
import org.saclex.demo.entities.Utilisateur;
import org.saclex.demo.entities.VerificationToken;
import org.saclex.demo.service.UtilisateurService;
import org.saclex.demo.service.VerificationTokenService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("utilisateur")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private VerificationTokenService verificationTokenService;
    private JavaMailSender javaMailSender;
    private PasswordEncoder encoder;


    public UtilisateurController(UtilisateurService utilisateurService,VerificationTokenService verificationTokenService, JavaMailSender javaMailSender, PasswordEncoder encoder) {
        this.utilisateurService = utilisateurService;
        this.verificationTokenService = verificationTokenService;
        this.javaMailSender = javaMailSender;
        this.encoder = encoder;
    }

    //liste tous les utilisateurs
    @GetMapping("/listerUtilisateur")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateurs();
    }

    //liste seulement les responsables de themes
    @GetMapping("/listerResponsableTheme")
    public List<Utilisateur> getResponsables(){
        return utilisateurService.findByRole(Utilisateur.Role.RESPONSABLE_THEME);
    }

    //liste seulement les responsables de catégories
    @GetMapping("/listerResponsableCategorie")
    public List<Utilisateur> getResponsablescategories(){
        return utilisateurService.findByRole(Utilisateur.Role.RESPONSABLE_CATEGORIE);
    }
    @GetMapping("/listerApprenant")
    public List<Utilisateur> getAprenant(){
        return utilisateurService.findByRole(Utilisateur.Role.APPRENANT);
    }

    //Fonction qui crée les utilisateurs ayant pour role responsable_theme
    @PostMapping("/creerUtilisateur")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur){
            utilisateur.setActive(true);
            utilisateur.setRole(Utilisateur.Role.RESPONSABLE_THEME);
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurService.createUtilisateur(utilisateur);
    }
    //Fonction qui crée les utilisateurs ayant pour role responsable_categorie
    @PostMapping("/creerRespCategorie")
    public Utilisateur createRespCat(@RequestBody Utilisateur utilisateur){
            utilisateur.setActive(true);
            utilisateur.setRole(Utilisateur.Role.RESPONSABLE_CATEGORIE);
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurService.createUtilisateur(utilisateur);
    }

    //Fonction qui cree les utilisateurs ayant pour rôle apprenant
    @PostMapping("/creerApprenant")
    public String createApprennant(@RequestBody Utilisateur utilisateur) throws MessagingException {
        //On definit son role et on le sauvegarde en BD son champs isActive est à false
       utilisateur.setRole(Utilisateur.Role.APPRENANT);
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateur.setActive( true );
        utilisateurService.createUtilisateur(utilisateur);

        //On cree un token en fonction de l'utilisateur qui vient d'être créé
        VerificationToken verificationToken = new VerificationToken(utilisateur);
        verificationTokenService.createToken(verificationToken);

        //On envoi un mail à l'utilisateur avec un lien qui permettra d'activer son comppte pour
        // se rassurer que l'adresse mail saisie est valide

       /* SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("fouda.anthony12@gmail.com");
        message.setSubject("Inscription terminée");
        message.setText("<b>Pour valider votre compte, cliquez ici:</b> "
                   +"http://localhost:8087/verif-token?token="+verificationToken.getToken()
                );*/
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper( mimeMessage ,"utf-8" );
        String htmlmsg="\n" +
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "\n" +
                "    <title>Mozilla</title>\n" +
                "\n" +
                "    <style>\n" +
                "\n" +
                "        body {margin:0; padding:0; -webkit-text-size-adjust:none; -ms-text-size-adjust:none;} img{line-height:100%; outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;} a img{border: none;} #backgroundTable {margin:0; padding:0; width:100% !important; } a, a:link{color:#2A5DB0; text-decoration: underline;} table td {border-collapse:collapse;} span {color: inherit; border-bottom: none;} span:hover { background-color: transparent; }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "    <style>\n" +
                " .scalable-image img{max-width:100% !important;height:auto !important}.button a{transition:background-color .25s, border-color .25s}.button a:hover{background-color:#e1e1e1 !important;border-color:#0976a5 !important}@media only screen and (max-width: 400px){.preheader{font-size:12px !important;text-align:center !important}.header--white{text-align:center}.header--white .header__logo{display:block;margin:0 auto;width:118px !important;height:auto !important}.header--left .header__logo{display:block;width:118px !important;height:auto !important}}@media screen and (-webkit-device-pixel-ratio), screen and (-moz-device-pixel-ratio){.sub-story__image,.sub-story__content{display:block\n" +
                " !important}.sub-story__image{float:left !important;width:200px}.sub-story__content{margin-top:30px !important;margin-left:200px !important}}@media only screen and (max-width: 550px){.sub-story__inner{padding-left:30px !important}.sub-story__image,.sub-story__content{margin:0 auto !important;float:none !important;text-align:center}.sub-story .button{padding-left:0 !important}}@media only screen and (max-width: 400px){.featured-story--top table,.featured-story--top td{text-align:left}.featured-story--top__heading td,.sub-story__heading td{font-size:18px !important}.featured-story--bottom:nth-child(2) .featured-story--bottom__inner{padding-top:10px\n" +
                " !important}.featured-story--bottom__inner{padding-top:20px !important}.featured-story--bottom__heading td{font-size:28px !important;line-height:32px !important}.featured-story__copy td,.sub-story__copy td{font-size:14px !important;line-height:20px !important}.sub-story table,.sub-story td{text-align:center}.sub-story__hero img{width:100px !important;margin:0 auto}}@media only screen and (max-width: 400px){.footer td{font-size:12px !important;line-height:16px !important}}\n" +
                "     @media screen and (max-width:600px) {\n" +
                "    table[class=\"columns\"] {\n" +
                "        margin: 0 auto !important;float:none !important;padding:10px 0 !important;\n" +
                "    }\n" +
                "    td[class=\"left\"] {\n" +
                "     padding: 0px 0 !important;\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background: #e1e1e1;font-family:Arial, Helvetica, sans-serif; font-size:1em;\"><style type=\"text/css\">\n" +
                "div.preheader \n" +
                "{ display: none !important; } \n" +
                "</style>\n" +
                "<div class=\"preheader\" style=\"font-size: 1px; display: none !important;\"></div>\n" +
                "    <table id=\"backgroundTable\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background:#e1e1e1;\">\n" +
                "        <tr>\n" +
                "            <td class=\"body\" align=\"center\" valign=\"top\" style=\"background:#e1e1e1;\" width=\"100%\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td width=\"640\">\n" +
                "                            </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"main\" width=\"640\" align=\"center\" style=\"padding: 0 10px;\">\n" +
                "                            <table style=\"min-width: 100%; \" class=\"stylingblock-content-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"stylingblock-content-wrapper camarker-inner\"><table cellspacing=\"0\" cellpadding=\"0\">\n" +
                " <tr>\n" +
                "  <td width=\"640\" align=\"left\">\n" +
                "   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"+
                "   </table>\n" +
                "  </td>\n" +
                " </tr>\n" +
                "</table></td></tr></table><table style=\"min-width: 100%; \" class=\"stylingblock-content-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"stylingblock-content-wrapper camarker-inner\"><table class=\"featured-story featured-story--top\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                " <tr>\n" +
                "  <td style=\"padding-bottom: 20px;\">\n" +
                "   <table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tr>\n" +
                "     <td class=\"featured-story__inner\" style=\"background: #fff;\">\n" +
                "      <table cellspacing=\"0\" cellpadding=\"0\">\n"+
                "       <tr>\n" +
                "        <td class=\"featured-story__content-inner\" style=\"padding: 32px 30px 45px;\">\n" +
                "         <table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "           <td class=\"featured-story__heading featured-story--top__heading\" style=\"background: #fff;\" width=\"640\" align=\"left\">\n" +
                "            <table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "             <tr>\n" +
                "              <td style=\"font-family: Geneva, Tahoma, Verdana, sans-serif; font-size: 22px; color: #464646;\" width=\"400\" align=\"left\">\n" +
                "                Création du compte de "+utilisateur.getNom().toUpperCase()+" "+utilisateur.getPrenom()+"\n"+
                "              </td>\n" +
                "             </tr>\n" +
                "            </table>\n" +
                "           </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td class=\"featured-story__copy\" style=\"background: #fff;\" width=\"640\" align=\"center\">\n" +
                "            <table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "             <tr>\n" +
                "              <td style=\"font-family: Geneva, Tahoma, Verdana, sans-serif; font-size: 16px; line-height: 22px; color: #555555; padding-top: 16px;\" align=\"left\">\n" +
                "                La création de votre compte a été réalisée avec succes. Il vous faudra souscrire à une catégorie et dès lors vous pourrez profiter de toutes nos offres.<br><br>\n" +
                "                A bientôt sur le SACLEX TEST GENERATOR.\n" +
                "              </td>\n" +
                "             </tr>\n" +
                "            </table>\n" +
                "           </td>\n" +
                "          </tr>\n" +
                "         </table>\n" +
                "        </td>\n" +
                "       </tr>\n" +
                "      </table>\n" +
                "     </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "  </td>\n" +
                " </tr>\n" +
                "</table></td></tr></table></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                     <td class=\"footer\" width=\"640\" align=\"center\" style=\"padding-top: 10px;\">\n" +
                "                      <table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                       <tr>\n" +
                "                        <td align=\"center\" style=\"font-family: Geneva, Tahoma, Verdana, sans-serif; font-size: 14px; line-height: 18px; color: #738597; padding: 0 20px 40px;\">\n" +
                "                                      <br>      <br>\n" +
                "<strong>Merci d'avoir lu!</strong>\n" +
                "\n" +
                "<br> \n" +
                "\n" +
                "<br>\n" +
                "<br>\n" +
                "<i>\"L'expertise au service de la performance\"</i> \n" +
                "<br>\n" +
                "<br>\n" +
                "\n" +
                "\n" +
                "\n" +
                "      <br>\n" +
                "<strong><a href=\"https://www.saclex.org\" style=\"color: #0c99d5;\"  target=\"_blank\">SACLEX SARL</a></strong>\n" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;\n" +
                "<strong><a href=\"https://urexpertise.com/\" style=\"color: #0c99d5;\"  target=\"_blank\">UREXPERTISE</a></strong>\n" +
                "<br><br>\n" +
                "\n" +
                "\n" +
                "                         Cameroun, Yaoundé, rond point Nlongkak, Face station Total, vers NIKI\n" +
                "                         <br>\n" +
                "                        </td>\n" +
                "                       </tr>\n" +
                "                      </table>\n" +
                "                     </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <!-- Exact Target tracking code -->\n" +
                "   \n" +
                "  \n" +
                "</custom></body>\n" +
                "</html>\n" +
                "\n";
        helper.setText( htmlmsg,true );
        helper.setTo( "fodjomaximejr@gmail.com" );
        helper.setSubject("Acceptation de la demande"  );
        javaMailSender.send(mimeMessage );


        return "mail envoye";

    }
    //fonction qui see déclenche suite au clique sur le lien envoyé par mail
    @PostMapping("/verif-token")
    public String confirmationCompte(@RequestParam("token") String confirmToken){
        //Verification de l'existence du token
        VerificationToken verificationToken =verificationTokenService.findByToken(confirmToken);
        //Lorsque le token existe alors le champ isActive passe a true
         if(verificationToken != null){
             Utilisateur utilisateur=utilisateurService.findByEmailIgnoreCase(verificationToken.getUtilisateur().getEmail());
             utilisateur.setActive(true);
             utilisateurService.updateUtilisateur(utilisateur);

             return "success";
         }
         else {
             return "echec";
         }

    }


    @PutMapping("/modifierUtilisateur")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception {
        if(utilisateur.getId() == null){
            throw new Exception("Utilisateur inexistant");
        }

        return utilisateurService.updateUtilisateur(utilisateur);
    }

    @PutMapping("/modifierpwd")
    public Utilisateur updatepwd(@RequestBody Utilisateur utilisateur) throws Exception {
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        if(utilisateur.getId() == null){
            throw new Exception("Utilisateur inexistant");
        }

        return utilisateurService.updateUtilisateur(utilisateur);
    }

    @GetMapping("/UnUtilisateur/{idUtilisateur}")
    public Utilisateur getUnUser(@PathVariable Long idUtilisateur) {
        return utilisateurService.findById(idUtilisateur);
    }

    @DeleteMapping("/supprimerUtilisateur/{idUtilisateur}")
    public void deleteTypeEvaluation(@PathVariable Long idUtilisateur){
        utilisateurService.deleteUtilisateur(idUtilisateur);
    }
}
