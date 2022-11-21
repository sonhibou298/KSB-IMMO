package sn.ksb.immo.ksbimmo.application.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ksb.immo.ksbimmo.application.dtos.ApporteurDto;
import sn.ksb.immo.ksbimmo.application.models.Apporteur;
import sn.ksb.immo.ksbimmo.application.repositories.ApporteurRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ApporteurService {

    private final ApporteurRepo apporteurRepository;

    private final ModelMapper mapper;

    public ApporteurService(ApporteurRepo apporteurRepository, ModelMapper mapper) {
        this.apporteurRepository = apporteurRepository;
        this.mapper = mapper;
    }

    //récupérer tous les apporteurs
    public List<Apporteur> getAllApporteurs() {
        //log entree dans la methode getAllApporteurs du service ApporteurService
        log.info("Entrée dans la méthode getAllApporteurs du service ApporteurService");
        //initialisation de la liste des apporteurs
        List<Apporteur> apporteurs = new ArrayList<>();
        //try catch pour la récupération des apporteurs
        try {
            //récupération des apporteurs
            apporteurs = apporteurRepository.findAll();
            //log de la récupération des apporteurs
            log.info("Récupération des apporteurs effectuée avec succès");
        } catch (Exception e) {
            //log de l'erreur
            log.error("Erreur lors de la récupération des apporteurs : {}", e.getMessage());
        }
        //if pour vérifier si la liste des apporteurs est vide
        if (apporteurs.isEmpty()) {
            //log aucun apporteur trouvé dans la base de données
            log.warn("Aucun apporteur trouvé dans la base de données");
        }
        //log sortie de la methode getAllApporteurs du service ApporteurService
        log.info("Sortie de la méthode getAllApporteurs du service ApporteurService");
        //retour de la liste des apporteurs
        return apporteurs;
    }

    //récupérer un apporteur par son cni
    public Apporteur getApporteurByCni(String cni) {
        //log entree dans la methode getApporteurByCni du service ApporteurService
        log.info("Entrée dans la méthode getApporteurByCni du service ApporteurService");
        //initialisation de l'apporteur
        Apporteur apporteur = null;
        //try catch pour la récupération de l'apporteur
        try {
            //récupération de l'apporteur
            apporteur = apporteurRepository.findByCni(cni);
            //log de la récupération de l'apporteur
            log.info("Récupération de l'apporteur effectuée avec succès");
        } catch (Exception e) {
            //log de l'erreur
            log.error("Erreur lors de la récupération de l'apporteur : {}", e.getMessage());
        }
        //if pour vérifier si l'apporteur est null
        if (apporteur == null) {
            //log aucun apporteur trouvé dans la base de données
            log.warn("Aucun apporteur trouvé dans la base de données avec le cni : {}", cni);
        }
        //log sortie de la methode getApporteurByCni du service ApporteurService
        log.info("Sortie de la méthode getApporteurByCni du service ApporteurService");
        //retour de l'apporteur
        return apporteur;
    }

    //creer un apporteur a partir d'un objet apporteurdto
    public Apporteur createApporteur(ApporteurDto dto) {
        //log entree dans la methode createApporteur du service ApporteurService
        log.info("Entrée dans la méthode createApporteur du service ApporteurService");
        //initialisation de l'apporteur
        Apporteur apporteur = null;
        //try catch pour la création de l'apporteur
        try {
            Apporteur fromDto = mapper.map(dto, Apporteur.class);
            //création de l'apporteur
            apporteur = apporteurRepository.save(fromDto);
            //log de la création de l'apporteur
            log.info("Création de l'apporteur effectuée avec succès");
        } catch (Exception e) {
            //log de l'erreur
            log.error("Erreur lors de la création de l'apporteur : {}", e.getMessage());
        }
        //if pour vérifier si l'apporteur est null
        if (apporteur == null) {
            //log aucun apporteur trouvé dans la base de données
            log.warn("Aucun apporteur trouvé dans la base de données avec le cni : {}", dto.getCni());
        }
        //log sortie de la methode createApporteur du service ApporteurService
        log.info("Sortie de la méthode createApporteur du service ApporteurService");
        //retour de l'apporteur
        return apporteur;
    }

    //modifier un apporteur a partir d'un objet apporteurdto
    public Apporteur updateApporteur(ApporteurDto dto) {
        //log entree dans la methode updateApporteur du service ApporteurService
        log.info("Entrée dans la méthode updateApporteur du service ApporteurService");
        //initialisation de l'apporteur
        Apporteur apporteur = null;
        //try catch pour la modification de l'apporteur
        try {
            Apporteur fromDto = mapper.map(dto, Apporteur.class);
            //modification de l'apporteur
            apporteur = apporteurRepository.save(fromDto);
            //log de la modification de l'apporteur
            log.info("Modification de l'apporteur effectuée avec succès");
        } catch (Exception e) {
            //log de l'erreur
            log.error("Erreur lors de la modification de l'apporteur : {}", e.getMessage());
        }
        //if pour vérifier si l'apporteur est null
        if (apporteur == null) {
            //log aucun apporteur trouvé dans la base de données
            log.warn("Aucun apporteur trouvé dans la base de données avec le cni : {}", dto.getCni());
        }
        //log sortie de la methode updateApporteur du service ApporteurService
        log.info("Sortie de la méthode updateApporteur du service ApporteurService");
        //retour de l'apporteur
        return apporteur;
    }

    //supprimer un apporteur a partir de son cni
    public void deleteApporteur(String cni) {
        //log entree dans la methode deleteApporteur du service ApporteurService
        log.info("Entrée dans la méthode deleteApporteur du service ApporteurService");
        //try catch pour la suppression de l'apporteur
        try {
            //suppression de l'apporteur
            apporteurRepository.deleteByCni(cni);
            //log de la suppression de l'apporteur
            log.info("Suppression de l'apporteur effectuée avec succès");
        } catch (Exception e) {
            //log de l'erreur
            log.error("Erreur lors de la suppression de l'apporteur : {}", e.getMessage());
        }
        //log sortie de la methode deleteApporteur du service ApporteurService
        log.info("Sortie de la méthode deleteApporteur du service ApporteurService");
    }
}
