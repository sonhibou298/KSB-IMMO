package sn.ksb.immo.ksbimmo.application.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ksb.immo.ksbimmo.application.models.Loyer;
import sn.ksb.immo.ksbimmo.application.services.LoyerService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/loyer")
@OpenAPIDefinition(tags = {@Tag(name = "Loyer", description = "Loyer API")})
public class LoyerController {

    private final LoyerService loyerService;

    public LoyerController(LoyerService loyerService) {
        this.loyerService = loyerService;
    }

    @GetMapping("/paid")
    public List<Loyer> getloyerPayes() {
        log.info("Entrée dans la méthode getloyerPayes du controller LoyerController");
        List<Loyer> loyers = null;
        try {
            loyers = loyerService.getLoyersPayes();
        }catch (Exception e) {
            log.error("Erreur lors la récupération des loyers payés : {}", e.getMessage());
        }
        if (loyers != null) {
            log.error("Erreur lors la récupération des loyers payés");
        }
        log.info("Sortie dans la méthode getloyerPayes du controller LoyerController");
        return loyers;
    }

    @GetMapping("/unpaid")
    public List<Loyer> getloyerNonPayes() {
        log.info("Entrée dans la méthode getloyerNonPayes du controller LoyerController");
        List<Loyer> loyers = null;
        try {
            loyers = loyerService.getLoyersAPayerPourLeMoisEnCours();
        }catch (Exception e) {
            log.error("Erreur lors la récupération des loyers non payés : {}", e.getMessage());
        }
        if (loyers == null || loyers.isEmpty()) {
            log.error("Erreur lors la récupération des loyers non payés");
        }
        log.info("Sortie dans la méthode getloyerNonPayes du controller LoyerController");
        return loyers;
    }

    //recuperer les loyers d'une propriete
    @GetMapping("/propriete/{id}")
    public List<Loyer> getLoyerByPropriete(@PathVariable String id) {
        log.info("Entrée dans la méthode getLoyerByPropriete du controller LoyerController");
        List<Loyer> loyers = null;
        try {
            loyers = loyerService.getLoyersByPropriete(id);
        }catch (Exception e) {
            log.error("Erreur lors la récupération des loyers : {}", e.getMessage());
        }
        if (loyers == null || loyers.isEmpty()) {
            log.error("Erreur lors la récupération des loyers");
        }
        log.info("Sortie dans la méthode getLoyerByPropriete du controller LoyerController");
        return loyers;
    }
}
