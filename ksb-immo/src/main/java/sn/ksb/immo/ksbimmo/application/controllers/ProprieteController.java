package sn.ksb.immo.ksbimmo.application.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.ksbimmo.application.dtos.ProprieteDto;
import sn.ksb.immo.ksbimmo.application.models.Propriete;
import sn.ksb.immo.ksbimmo.application.services.ProprieteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@OpenAPIDefinition(tags = {@Tag(name = "Propriete", description = "Propriete API")})
@RequestMapping("/api/proprietes")
public class ProprieteController {

    private final ProprieteService proprieteService;

    public ProprieteController(ProprieteService proprieteService) {
        this.proprieteService = proprieteService;
    }

    //récupérer les propriétés d'une agence
    @GetMapping("/agence/{agenceId}")
    public List<Propriete> getProprietesByAgence(String agenceId) {
        //log entrée dans la méthode getProprietesByAgence du controller ProprieteController
        log.info("Entrée dans la méthode getProprietesByAgence du controller ProprieteController");
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteService.getProprietesByAgence(agenceId);
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //log sortie de la méthode getProprietesByAgence du controller ProprieteController
        log.info("Sortie de la méthode getProprietesByAgence du controller ProprieteController");
        //retour des propriétés
        return proprietes;
    }

    //récupérer les propriétés d'un client
    @GetMapping("/client/{clientId}")
    public List<Propriete> getProprietesByClient(String clientId) {
        //log entrée dans la méthode getProprietesByClient du controller ProprieteController
        log.info("Entrée dans la méthode getProprietesByClient du controller ProprieteController");
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteService.getProprietesByProprietaire(clientId);
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //log sortie de la méthode getProprietesByClient du controller ProprieteController
        log.info("Sortie de la méthode getProprietesByClient du controller ProprieteController");
        //retour des propriétés
        return proprietes;
    }

    //creer une propriété pour un client
    @PostMapping
    public Propriete createPropriete(ProprieteDto dto) {
        //log entrée dans la méthode createPropriete du controller ProprieteController
        log.info("Entrée dans la méthode createPropriete du controller ProprieteController");
        Propriete propriete = null;
        //try catch pour créer une propriété
        try {
            //création de la propriété
            propriete = proprieteService.addPropriete(dto);
            //log création de la propriété
            log.info("Création de la propriété");
        } catch (Exception e) {
            //log erreur création de la propriété
            log.error("Erreur lors de la création de la propriété");
        }
        //log sortie de la méthode createPropriete du controller ProprieteController
        log.info("Sortie de la méthode createPropriete du controller ProprieteController");
        //retour de la propriété
        return propriete;
    }

    //modifier une propriété
    @PutMapping
    public Propriete updatePropriete(ProprieteDto dto) {
        //log entrée dans la méthode updatePropriete du controller ProprieteController
        log.info("Entrée dans la méthode updatePropriete du controller ProprieteController");
        Propriete propriete = null;
        //try catch pour modifier une propriété
        try {
            //modification de la propriété
            propriete = proprieteService.updatePropriete(dto);
            //log modification de la propriété
            log.info("Modification de la propriété");
        } catch (Exception e) {
            //log erreur modification de la propriété
            log.error("Erreur lors de la modification de la propriété");
        }
        //log sortie de la méthode updatePropriete du controller ProprieteController
        log.info("Sortie de la méthode updatePropriete du controller ProprieteController");
        //retour de la propriété
        return propriete;
    }

    //supprimer une propriété
    @DeleteMapping("/{proprieteId}")
    public void deletePropriete(String proprieteId) {
        //log entrée dans la méthode deletePropriete du controller ProprieteController
        log.info("Entrée dans la méthode deletePropriete du controller ProprieteController");
        //try catch pour supprimer une propriété
        try {
            //suppression de la propriété
            proprieteService.deletePropriete(proprieteId);
            //log suppression de la propriété
            log.info("Suppression de la propriété");
        } catch (Exception e) {
            //log erreur suppression de la propriété
            log.error("Erreur lors de la suppression de la propriété");
        }
        //log sortie de la méthode deletePropriete du controller ProprieteController
        log.info("Sortie de la méthode deletePropriete du controller ProprieteController");
    }

    //réserver une propriété par son id
    @GetMapping("/{proprieteId}")
    public Propriete getProprieteById(@PathVariable String proprieteId) {
        //log entrée dans la méthode getProprieteById du controller ProprieteController
        log.info("Entrée dans la méthode getProprieteById du controller ProprieteController");
        Propriete propriete = null;
        //try catch pour récupérer une propriété
        try {
            //récupération de la propriété
            propriete = proprieteService.getProprieteById(proprieteId);
            //log récupération de la propriété
            log.info("Récupération de la propriété");
        } catch (Exception e) {
            //log erreur récupération de la propriété
            log.error("Erreur lors de la récupération de la propriété");
        }
        //log sortie de la méthode getProprieteById du controller ProprieteController
        log.info("Sortie de la méthode getProprieteById du controller ProprieteController");
        //retour de la propriété
        return propriete;
    }

    //récupérer les propriétés qui sont louées
    @GetMapping("/louees")
    public List<Propriete> getProprietesLouees() {
        //log entrée dans la méthode getProprietesLouees du controller ProprieteController
        log.info("Entrée dans la méthode getProprietesLouees du controller ProprieteController");
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteService.getProprieteByStatus(true);
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //log sortie de la méthode getProprietesLouees du controller ProprieteController
        log.info("Sortie de la méthode getProprietesLouees du controller ProprieteController");
        //retour des propriétés
        return proprietes;
    }

    //récupérer les propriétés qui sont disponibles
    @GetMapping("/disponibles")
    public List<Propriete> getProprietesDisponibles() {
        //log entrée dans la méthode getProprietesDisponibles du controller ProprieteController
        log.info("Entrée dans la méthode getProprietesDisponibles du controller ProprieteController");
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteService.getProprieteByStatus(false);
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //log sortie de la méthode getProprietesDisponibles du controller ProprieteController
        log.info("Sortie de la méthode getProprietesDisponibles du controller ProprieteController");
        //retour des propriétés
        return proprietes;
    }

    //récupérer les propriétés par adresse
    @GetMapping("/adresse/{adresse}")
    public List<Propriete> getProprietesByAdresse(@PathVariable String adresse) {
        //log entrée dans la méthode getProprietesByAdresse du controller ProprieteController
        log.info("Entrée dans la méthode getProprietesByAdresse du controller ProprieteController");
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteService.getProprieteByAdresse(adresse);
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //log sortie de la méthode getProprietesByAdresse du controller ProprieteController
        log.info("Sortie de la méthode getProprietesByAdresse du controller ProprieteController");
        //retour des propriétés
        return proprietes;
    }
}
