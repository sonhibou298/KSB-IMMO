package sn.ksb.immo.ksbimmo.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.ksb.immo.ksbimmo.application.dtos.MensualiteDto;
import sn.ksb.immo.ksbimmo.application.models.Locataire;
import sn.ksb.immo.ksbimmo.application.models.Loyer;
import sn.ksb.immo.ksbimmo.application.models.Mensualite;
import sn.ksb.immo.ksbimmo.application.repositories.LocataireRepo;
import sn.ksb.immo.ksbimmo.application.repositories.LoyerRepo;
import sn.ksb.immo.ksbimmo.application.repositories.MensualiteRepo;
import sn.ksb.immo.ksbimmo.application.repositories.ProprieteRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class MensualiteService {

    private final MensualiteRepo mensualiteRepository;

    private final LocataireRepo locataireRepository;

    private final ProprieteRepo proprieteRepository;

    private final LoyerRepo loyerRepository;

    public MensualiteService(MensualiteRepo mensualiteRepository, LocataireRepo locataireRepository, ProprieteRepo proprieteRepository, LoyerRepo loyerRepository) {
        this.mensualiteRepository = mensualiteRepository;
        this.locataireRepository = locataireRepository;
        this.proprieteRepository = proprieteRepository;
        this.loyerRepository = loyerRepository;
    }

    //enregister un paiement
    public Mensualite createMensualite(MensualiteDto dto) {
        log.info("Entrée dans la méthode createMensualite du service MensualiteService");
        Mensualite mensualite = null;
        try {
            //recuperer le loyer du locataire
            Locataire locataire = locataireRepository.findById(UUID.fromString(dto.getLocataireId())).get();
            Loyer loyer = locataire.getLoyer();
            //creer un objet mensualite
            mensualite = Mensualite.builder().loyer(loyer).datePaiement(new Date()).build();
            loyer.getMensualites().add(mensualite);
            loyer.setDernierPaiement(new Date());
            loyer.setDateProchainPaiement(Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            loyerRepository.save(loyer);
            mensualiteRepository.save(mensualite);
        }catch (Exception e) {
            log.error("Erreur lors la création de l'objet : {}", e.getMessage());
        }
        if (mensualite == null) {
            log.error("Erreur lors la création de l'objet");
        }
        log.info("Sortie de la méthode createMensualite du service MensualiteService");
        return mensualite;
    }
    
}
