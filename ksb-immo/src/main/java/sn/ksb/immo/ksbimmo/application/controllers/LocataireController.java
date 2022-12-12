package sn.ksb.immo.ksbimmo.application.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.ksbimmo.application.dtos.LocataireDto;
import sn.ksb.immo.ksbimmo.application.models.Locataire;
import sn.ksb.immo.ksbimmo.application.services.LocataireService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@OpenAPIDefinition(tags = {@Tag(name = "Locataire", description = "Locataire API")})
@RequestMapping("/api/locataires")
public class LocataireController {

    private final LocataireService locataireService;

    public LocataireController(LocataireService locataireService) {
        this.locataireService = locataireService;
    }

    //récupérer les locataires d'une agence
    @GetMapping("/agence/{agenceId}")
    public List<Locataire> getLocatairesByAgence(@PathVariable String agenceId) {
        //log entrée dans la méthode getLocatairesByAgence du controller LocataireController
        log.info("Entrée dans la méthode getLocatairesByAgence du controller LocataireController");
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            locataires = locataireService.getLocatairesByAgence(agenceId);
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires");
        }
        //log sortie de la méthode getLocatairesByAgence du controller LocataireController
        log.info("Sortie de la méthode getLocatairesByAgence du controller LocataireController");
        //retour des locataires
        return locataires;
    }

    //récuperer les locataires
    @GetMapping
    public List<Locataire> getAllLocataires() {
        //log entrée dans la méthode getAllLocataires du controller LocataireController
        log.info("Entrée dans la méthode getAllLocataires du controller LocataireController");
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            locataires = locataireService.getAllLocataires();
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires");
        }
        //log sortie de la méthode getAllLocataires du controller LocataireController
        log.info("Sortie de la méthode getAllLocataires du controller LocataireController");
        //retour des locataires
        return locataires;
    }

    //récupérer un locataire par son cin
    @GetMapping("/{cin}")
    public Locataire getLocataireByCin(@PathVariable String cin) {
        //log entrée dans la méthode getLocataireByCin du controller LocataireController
        log.info("Entrée dans la méthode getLocataireByCin du controller LocataireController");
        Locataire locataire = new Locataire();
        //try catch pour récupérer le locataire
        try {
            //récupération du locataire
            locataire = locataireService.getLocataireByCin(cin);
            //log récupération du locataire
            log.info("Récupération du locataire");
        } catch (Exception e) {
            //log erreur récupération du locataire
            log.error("Erreur lors de la récupération du locataire");
        }
        //log sortie de la méthode getLocataireByCin du controller LocataireController
        log.info("Sortie de la méthode getLocataireByCin du controller LocataireController");
        //retour du locataire
        return locataire;
    }

    //récupérer les locataires d'une propriété
    @GetMapping("/propriete/{proprieteId}")
    public List<Locataire> getLocatairesByPropriete(@PathVariable String proprieteId) {
        //log entrée dans la méthode getLocatairesByPropriete du controller LocataireController
        log.info("Entrée dans la méthode getLocatairesByPropriete du controller LocataireController");
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            locataires = locataireService.getLocatairesByPropriete(proprieteId);
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires");
        }
        //log sortie de la méthode getLocatairesByPropriete du controller LocataireController
        log.info("Sortie de la méthode getLocatairesByPropriete du controller LocataireController");
        //retour des locataires
        return locataires;
    }

    //créer un locataire
    @PostMapping
    public Locataire createLocataire(@RequestBody LocataireDto locataire) {
        //log entrée dans la méthode createLocataire du controller LocataireController
        log.info("Entrée dans la méthode createLocataire du controller LocataireController");
        Locataire locataireCreated = new Locataire();
        //try catch pour créer le locataire
        try {
            //création du locataire
            locataireCreated = locataireService.createLocataire(locataire);
            //log création du locataire
            log.info("Création du locataire...");
        } catch (Exception e) {
            //log erreur création du locataire
            log.error("Erreur lors de la création du locataire : {}", e.getMessage());
        }
        //log sortie de la méthode createLocataire du controller LocataireController
        log.info("Sortie de la méthode createLocataire du controller LocataireController");
        //retour du locataire créé
        return locataireCreated;
    }

    //modifier un locataire
    @PutMapping
    public Locataire updateLocataire(@RequestBody LocataireDto locataire) {
        //log entrée dans la méthode updateLocataire du controller LocataireController
        log.info("Entrée dans la méthode updateLocataire du controller LocataireController");
        Locataire locataireUpdated = new Locataire();
        //try catch pour modifier le locataire
        try {
            //modification du locataire
            locataireUpdated = locataireService.updateLocataire(locataire);
            //log modification du locataire
            log.info("Modification du locataire...");
        } catch (Exception e) {
            //log erreur modification du locataire
            log.error("Erreur lors de la modification du locataire : {}", e.getMessage());
        }
        //log sortie de la méthode updateLocataire du controller LocataireController
        log.info("Sortie de la méthode updateLocataire du controller LocataireController");
        //retour du locataire modifié
        return locataireUpdated;
    }

    //supprimer un locataire
    @DeleteMapping("/{idLocataire}")
    public void deleteLocataire(@PathVariable String idLocataire) {
        //log entré dans la méthode deleteLocataire du controller LocataireController
        log.info("Entrée dans la méthode deleteLocataire du controller LocataireController");
        //try catch pour supprimer le locataire
        try {
            //suppression du locataire
            locataireService.deleteLocataire(idLocataire);
            //log suppression du locataire
            log.info("Suppression du locataire...");
        } catch (Exception e) {
            //log erreur suppression du locataire
            log.error("Erreur lors de la suppression du locataire : {}", e.getMessage());
        }
        //log sortie de la méthode deleteLocataire du controller LocataireController
        log.info("Sortie de la méthode deleteLocataire du controller LocataireController");
    }
}
