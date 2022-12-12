package sn.ksb.immo.ksbimmo.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.ksb.immo.ksbimmo.application.models.Loyer;
import sn.ksb.immo.ksbimmo.application.repositories.LoyerRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
@Slf4j
public class LoyerService {

    private final LoyerRepo loyerRepository;

    public LoyerService(LoyerRepo loyerRepository) {
        this.loyerRepository = loyerRepository;
    }

    //récuperer les loyers valides
    public List<Loyer> getLoyersPayes() {
        log.info("Entrée dans la méthode getLoyers du service LoyerService");
        List<Loyer> loyers = null;
        try {
            //récuperer le premier jour du mois en cours
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date dateDebut = calendar.getTime();
            //récuperer le dernier jour du mois en cours
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date dateFin = calendar.getTime();
            log.info("Récupération des loyers payés entre le {} et le {}", dateDebut, dateFin);
            loyers = loyerRepository.findByDernierPaiementAfter(dateDebut);
        }catch (Exception e) {
            log.error("Erreur lors de la récupération des loyers : {}", e.getMessage());
        }
        if (loyers == null || loyers.isEmpty()) {
            log.error("Erreur lors de la récupération des loyers");
        }
        log.info("Sortie dans la méthode getLoyers du service LoyerService");
        return loyers;
    }

    //récuperer les loyer a payer pour le mois en cours
    public List<Loyer> getLoyersAPayerPourLeMoisEnCours() {
        log.info("Entrée dans la méthode getLoyersAPayerPourLeMoisEnCours du service LoyerService");
        List<Loyer> loyers = null;
        try {
            int moisEnCours = LocalDate.now().getMonthValue();
            loyers = loyerRepository.findByCurrentMonth(moisEnCours);
        }catch (Exception e) {
            log.error("Erreur lors de la récupération des loyers : {}", e.getMessage());
        }
        if ( loyers == null || loyers.isEmpty()) {
            log.warn("Aucun loyer à payer pour le mois en cours");
        }
        log.info("Sortie dans la méthode getLoyersAPayerPourLeMoisEnCours du service LoyerService");
        return loyers;
    }

    //récuperer les loyer d'une propriété
    public List<Loyer> getLoyersByPropriete(String idPropriete) {
        log.info("Entrée dans la méthode getLoyersByPropriete du service LoyerService");
        List<Loyer> loyers = null;
        try {
            loyers = loyerRepository.findByPropriete_Id(UUID.fromString(idPropriete));
        }catch (Exception e) {
            log.error("Erreur lors de la récupération des loyers : {}", e.getMessage());
        }
        if ( loyers == null || loyers.isEmpty()) {
            log.warn("Aucun loyer pour cette propriété");
        }
        log.info("Sortie dans la méthode getLoyersByPropriete du service LoyerService");
        return loyers;
    }

}
