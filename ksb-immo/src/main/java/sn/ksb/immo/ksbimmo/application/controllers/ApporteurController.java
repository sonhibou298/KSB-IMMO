package sn.ksb.immo.ksbimmo.application.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.ksbimmo.application.dtos.ApporteurDto;
import sn.ksb.immo.ksbimmo.application.models.Apporteur;
import sn.ksb.immo.ksbimmo.application.services.ApporteurService;

import java.util.List;

@RestController
@Slf4j
@OpenAPIDefinition(tags = {@Tag(name = "Apporteur", description = "Apporteur API")})
@RequestMapping("/api/apporteurs")
class ApporteurController {

    private final ApporteurService service;

    public ApporteurController(ApporteurService service) {
        this.service = service;
    }

    //récupérer tous les apporteurs
    @GetMapping
    public List<Apporteur> getAllApporteur() {
        //log the entry of the method
        log.info("Entrée dans la méthode getAll du controller ApporteurController");
        //initialize a list of apporteur
        List<Apporteur> apporteurs = null;
        //try to get all apporteurs from the service
        try {
            apporteurs = service.getAllApporteurs();
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la récupération des apporteurs dans la base de données");
        }
        //if the list is empty
        if (apporteurs == null || apporteurs.isEmpty()) {
            //log the error
            log.error("Aucun apporteur n'a été trouvé dans la base de données");
        }
        else {
            //log the number of apporteurs
            log.info("Récuperation de {} apporteurs dans la base de données : ", apporteurs.size());
        }
        //log the exit of the method
        log.info("Sortie de la méthode getAll du controller ApporteurController");
        //return the list
        return apporteurs;
    }

    //créer un nouvel apporteur
    @PostMapping
    public Apporteur createApporteur(@RequestBody ApporteurDto apporteur) {
        //log the entry of the method
        log.info("Entrée dans la méthode create du controller ApporteurController");
        //initialize a apporteur
        Apporteur apporteurCreated = null;
        //try to create a apporteur from the service
        try {
            apporteurCreated = service.createApporteur(apporteur);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la création de l'apporteur dans la base de données");
        }
        //if the apporteur is null
        if (apporteurCreated == null) {
            //log the error
            log.error("L'apporteur n'a pas été créé dans la base de données");
        }
        else {
            //log the number of apporteurs
            log.info("Création de l'apporteur {} dans la base de données : ", apporteurCreated.getNom());
        }
        //log the exit of the method
        log.info("Sortie de la méthode create du controller ApporteurController");
        //return the list
        return apporteurCreated;
    }

    //récupérer un apporteur par son cin
    @GetMapping("/{cni}")
    public Apporteur getApporteurByCni(@PathVariable String cni) {
        //log the entry of the method
        log.info("Entrée dans la méthode getApporteurByCni du controller ApporteurController");
        //initialize a apporteur
        Apporteur apporteur = null;
        //try to get a apporteur from the service
        try {
            apporteur = service.getApporteurByCni(cni);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la récupération de l'apporteur dans la base de données");
        }
        //if the apporteur is null
        if (apporteur == null) {
            //log the error
            log.error("L'apporteur n'a pas été trouvé dans la base de données");
        }
        else {
            //log the number of apporteurs
            log.info("Récuperation de l'apporteur {} dans la base de données : ", apporteur.getNom());
        }
        //log the exit of the method
        log.info("Sortie de la méthode getApporteurByCni du controller ApporteurController");
        //return the list
        return apporteur;
    }

    //modifier un apporteur
    @PutMapping
    public Apporteur updateApporteur(@RequestBody ApporteurDto apporteur) {
        //log the entry of the method
        log.info("Entrée dans la méthode update du controller ApporteurController");
        //initialize a apporteur
        Apporteur apporteurUpdated = null;
        //try to update a apporteur from the service
        try {
            apporteurUpdated = service.updateApporteur(apporteur);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la modification de l'apporteur dans la base de données");
        }
        //if the apporteur is null
        if (apporteurUpdated == null) {
            //log the error
            log.error("L'apporteur n'a pas été modifié dans la base de données");
        }
        else {
            //log the number of apporteurs
            log.info("Modification de l'apporteur {} dans la base de données : ", apporteurUpdated.getNom());
        }
        //log the exit of the method
        log.info("Sortie de la méthode update du controller ApporteurController");
        //return the list
        return apporteurUpdated;
    }

    //supprimer un apporteur par son cin
    @DeleteMapping("/{cni}")
    public void deleteApporteurByCni(@PathVariable String cni) {
        //log the entry of the method
        log.info("Entrée dans la méthode deleteApporteurByCni du controller ApporteurController");
        //try to delete a apporteur from the service
        try {
            service.deleteApporteur(cni);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la suppression de l'apporteur dans la base de données");
        }
        //log the exit of the method
        log.info("Sortie de la méthode deleteApporteurByCni du controller ApporteurController");
    }
}