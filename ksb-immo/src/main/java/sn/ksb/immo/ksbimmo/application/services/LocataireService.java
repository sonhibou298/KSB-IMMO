package sn.ksb.immo.ksbimmo.application.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ksb.immo.ksbimmo.application.dtos.LocataireDto;
import sn.ksb.immo.ksbimmo.application.enums.Role;
import sn.ksb.immo.ksbimmo.application.models.Locataire;
import sn.ksb.immo.ksbimmo.application.models.Loyer;
import sn.ksb.immo.ksbimmo.application.models.Propriete;
import sn.ksb.immo.ksbimmo.application.models.SituationProfessionnelle;
import sn.ksb.immo.ksbimmo.application.repositories.AgenceRepo;
import sn.ksb.immo.ksbimmo.application.repositories.LocataireRepo;
import sn.ksb.immo.ksbimmo.application.repositories.LoyerRepo;
import sn.ksb.immo.ksbimmo.application.repositories.ProprieteRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
@Slf4j
public class LocataireService {

    private final LocataireRepo locataireRepo;

    private final AgenceRepo agenceService;

    private final ProprieteRepo proprieteRepo;

    private final LoyerRepo loyerRepo;

    private final ModelMapper mapper;

    public LocataireService(LocataireRepo locataireRepo, AgenceRepo agenceService, ProprieteRepo proprieteRepo, LoyerRepo loyerRepo, ModelMapper mapper) {
        this.locataireRepo = locataireRepo;
        this.agenceService = agenceService;
        this.proprieteRepo = proprieteRepo;
        this.loyerRepo = loyerRepo;
        this.mapper = mapper;
    }

    //récupérer tous les locataires
    public List<Locataire> getAllLocataires() {
        //log entrée dans la méthode getAllLocataires du service LocataireService
        log.info("Entrée dans la méthode getAllLocataires du service LocataireService");
        //initialisation de la liste des locataires
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            locataires = locataireRepo.findAll();
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires : {}", e.getMessage());
        }

        //si la liste est vide
        if (locataires.isEmpty()) {
            //log aucun locataire trouvé dans la base de données
            log.error("Aucun locataire trouvé dans la base de données");
        }
        //log sortie de la méthode getAllLocataires du service LocataireService
        log.info("Sortie de la méthode getAllLocataires du service LocataireService");
        //retourner la liste des locataires
        return locataires;
    }

    //récupérer un locataire par son cin
    public Locataire getLocataireByCin(String cin) {
        //log entrée dans la méthode getLocataireByCin du service LocataireService
        log.info("Entrée dans la méthode getLocataireByCin du service LocataireService");
        //initialisation du locataire
        Locataire locataire = null;
        //try catch pour récupérer le locataire
        try {
            //récupération du locataire
            locataire = locataireRepo.findByCni(cin);
            //log récupération du locataire
            log.info("Récupération du locataire");
        } catch (Exception e) {
            //log erreur récupération du locataire
            log.error("Erreur lors de la récupération du locataire : {}", e.getMessage());
        }

        //si le locataire est null
        if (locataire == null) {
            //log aucun locataire trouvé dans la base de données
            log.error("Aucun locataire trouvé dans la base de données");
        }
        //log sortie de la méthode getLocataireByCin du service LocataireService
        log.info("Sortie de la méthode getLocataireByCin du service LocataireService");
        //retourner le locataire
        return locataire;
    }

    //récupérer les locataires d'une agence
    public List<Locataire> getLocatairesByAgence(String idAgence) {
        //log entrée dans la méthode getLocatairesByAgence du service LocataireService
        log.info("Entrée dans la méthode getLocatairesByAgence du service LocataireService");
        //initialisation de la liste des locataires
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            //locataires = locataireRepo.findByAgences_id(UUID.fromString(idAgence));
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires : {}", e.getMessage());
        }

        //si la liste est vide
        if (locataires.isEmpty()) {
            //log aucun locataire trouvé dans la base de données
            log.error("Aucun locataire trouvé dans la base de données");
        }
        //log sortie de la méthode getLocatairesByAgence du service LocataireService
        log.info("Sortie de la méthode getLocatairesByAgence du service LocataireService");
        //retourner la liste des locataires
        return locataires;
    }

    //créer un locataire
    public Locataire createLocataire(LocataireDto dto) {
        //log entrée dans la méthode createLocataire du service LocataireService
        log.info("Entrée dans la méthode createLocataire du service LocataireService");
        //initialisation du locataire
        Locataire locataire = null;
        //try catch pour créer le locataire
        try {
            //création du locataire
            locataire = locataireRepo.existsByCni(dto.getCni()) ? locataireRepo.findByCni(dto.getCni()) : mapper.map(dto, Locataire.class);
            //recupération de l'agence
            //Agence agence = agenceService.findById(UUID.fromString(dto.getAgenceId())).orElse(null);
            Propriete propriete = proprieteRepo.findById(UUID.fromString(dto.getProprieteId())).orElse(null);
            //ajout de l'agence au locataire
            if (propriete != null) {
                /*if (locataire.getProprietes() == null) {
                    locataire.setProprietes(new ArrayList<>());
                }
                propriete.setStatus(true);
                locataire.getProprietes().add(propriete);*/
                locataire.setRole(Role.LOCATAIRE);
                //récupérer les infos du loyer
                Loyer loyer = mapper.map(dto.getLoyer(), Loyer.class);
                loyer.setPropriete(propriete);
                propriete.setStatus(true);
                loyer.setMontant(dto.getLoyer().getMensualite() * dto.getLoyer().getDureeBail());
                //convertir le Date en LocalDate
                LocalDate date = loyer.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //ajouter la durée du bail
                date = date.plusMonths(dto.getLoyer().getDureeBail());
                //convertir le LocalDate en Date
                Date dateFin = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                //ajouter la date de fin du bail
                loyer.setDateFin(dateFin);
                loyer.setLocataire(locataire);
                propriete.getLoyer().add(loyer);
                locataire.setLoyer(loyer);
                //sauvegarde du locataire
                locataire = locataireRepo.save(locataire);
                log.info("Création du locataire");

                //générer le contrat de location ici

            }else {
                log.error("La propriété demandée n'existe pas dans la base de données");
            }
        } catch (Exception e) {
            //log erreur création du locataire
            log.error("Erreur lors de la création du locataire : {}", e.getMessage());
        }
        //si le locataire est null
        if (locataire == null || locataire.getId() == null) {
            //log locataire non créé
            log.error("Le locataire n'a pas été créé");
        }
        //log sortie de la méthode createLocataire du service LocataireService
        log.info("Sortie de la méthode createLocataire du service LocataireService");
        //retourner le locataire
        return locataire;
    }

    //modifier un locataire
    public Locataire updateLocataire(LocataireDto dto) {
        //log entrée dans la méthode updateLocataire du service LocataireService
        log.info("Entrée dans la méthode updateLocataire du service LocataireService");
        //initialisation du locataire
        Locataire locataire = null;
        //try catch pour modifier le locataire
        try {
            //récupération du locataire
            locataire = locataireRepo.findByCni(dto.getCni());
            if (locataire != null) {
                //modification du locataire
                locataire.setCni(dto.getCni());
                locataire.setNom(dto.getNom());
                locataire.setPrenom(dto.getPrenom());
                locataire.setAdresse(dto.getAdresse());
                locataire.setTelephone(dto.getTelephone());
                locataire.setEmail(dto.getEmail());
                locataire.setProfession(dto.getProfession());
                locataire.setSituationProfessionnelle(mapper.map(dto.getSituationProfessionnelle(), SituationProfessionnelle.class));
                //sauvegarde du locataire
                locataire = locataireRepo.save(locataire);
            }
            //log modification du locataire
            log.info("Modification du locataire");
        } catch (Exception e) {
            //log erreur modification du locataire
            log.error("Erreur lors de la modification du locataire : {}", e.getMessage());
        }
        //si le locataire est null
        if (locataire == null) {
            //log locataire non modifié
            log.error("Le locataire n'a pas été modifié");
        }
        //log sortie de la méthode updateLocataire du service LocataireService
        log.info("Sortie de la méthode updateLocataire du service LocataireService");
        //retourner le locataire
        return locataire;
    }

    //supprimer un locataire
    public void deleteLocataire(String cni) {
        //log entrée dans la méthode deleteLocataire du service LocataireService
        log.info("Entrée dans la méthode deleteLocataire du service LocataireService");
        //try catch pour supprimer le locataire
        try {
            //suppression du locataire
            locataireRepo.deleteByCni(cni);
            //log suppression du locataire
            log.info("Suppression du locataire");
        } catch (Exception e) {
            //log erreur suppression du locataire
            log.error("Erreur lors de la suppression du locataire : {}", e.getMessage());
        }
        //log sortie de la méthode deleteLocataire du service LocataireService
        log.info("Sortie de la méthode deleteLocataire du service LocataireService");
    }

    //récupérer les locataires d'une propriété
    public List<Locataire> getLocatairesByPropriete(String idPropriete) {
        //log entrée dans la méthode getLocatairesByPropriete du service LocataireService
        log.info("Entrée dans la méthode getLocatairesByPropriete du service LocataireService");
        //initialisation de la liste des locataires
        List<Locataire> locataires = new ArrayList<>();
        //try catch pour récupérer les locataires
        try {
            //récupération des locataires
            locataires = locataireRepo.findByLoyer_Propriete_Id(UUID.fromString(idPropriete));
            //log récupération des locataires
            log.info("Récupération des locataires");
        } catch (Exception e) {
            //log erreur récupération des locataires
            log.error("Erreur lors de la récupération des locataires : {}", e.getMessage());
        }

        //si la liste est vide
        if (locataires.isEmpty()) {
            //log aucun locataire trouvé dans la base de données
            log.error("Aucun locataire trouvé dans la base de données");
        }
        //log sortie de la méthode getLocatairesByPropriete du service LocataireService
        log.info("Sortie de la méthode getLocatairesByPropriete du service LocataireService");
        //retourner la liste des locataires
        return locataires;
    }

}
