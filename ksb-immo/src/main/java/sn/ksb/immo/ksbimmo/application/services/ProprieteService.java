package sn.ksb.immo.ksbimmo.application.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sn.ksb.immo.ksbimmo.application.dtos.ProprieteDto;
import sn.ksb.immo.ksbimmo.application.models.Agence;
import sn.ksb.immo.ksbimmo.application.models.Proprietaire;
import sn.ksb.immo.ksbimmo.application.models.Propriete;
import sn.ksb.immo.ksbimmo.application.repositories.AgenceRepo;
import sn.ksb.immo.ksbimmo.application.repositories.ProprietaireRepo;
import sn.ksb.immo.ksbimmo.application.repositories.ProprieteRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProprieteService {

    private final ProprieteRepo proprieteRepo;

    private final ProprietaireRepo proprietaireRepo;

    private final AgenceRepo agenceRepo;

    private final ModelMapper mapper;

    public ProprieteService(ProprieteRepo proprieteRepo, ProprietaireRepo proprietaireRepo, AgenceRepo agenceRepo, ModelMapper mapper) {
        this.proprieteRepo = proprieteRepo;
        this.proprietaireRepo = proprietaireRepo;
        this.agenceRepo = agenceRepo;
        this.mapper = mapper;
    }

    //récupérer les propriétés d'une agence
    public List<Propriete> getProprietesByAgence(String agenceId) {
        //log entrée dans la méthode getProprietesByAgence du service ProprieteService
        log.info("Entrée dans la méthode getProprietesByAgence du service ProprieteService");
        //initialisation de la liste des propriétés
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteRepo.findByAgence_Id(UUID.fromString(agenceId));
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //si la liste est vide
        if (proprietes.isEmpty()) {
            //log aucune propriété trouvée dans la base de données
            log.error("Aucune propriété trouvée dans la base de données pour l'agence d'id : {}" , agenceId);
        }
        //log sortie de la méthode getProprietesByAgence du service ProprieteService
        log.info("Sortie de la méthode getProprietesByAgence du service ProprieteService");
        //retourner la liste des propriétés
        return proprietes;
    }

    //récupérer les propriétés d'un propriétaire
    public List<Propriete> getProprietesByProprietaire(String proprietaireId) {
        //log entrée dans la méthode getProprietesByProprietaire du service ProprieteService
        log.info("Entrée dans la méthode getProprietesByProprietaire du service ProprieteService");
        //initialisation de la liste des propriétés
        List<Propriete> proprietes = new ArrayList<>();
        //try catch pour récupérer les propriétés
        try {
            //récupération des propriétés
            proprietes = proprieteRepo.findByProprietaire_Id(UUID.fromString(proprietaireId));
            //log récupération des propriétés
            log.info("Récupération des propriétés");
        } catch (Exception e) {
            //log erreur récupération des propriétés
            log.error("Erreur lors de la récupération des propriétés");
        }
        //si la liste est vide
        if (proprietes.isEmpty()) {
            //log aucune propriété trouvée dans la base de données
            log.error("Aucune propriété trouvée dans la base de données pour le propriétaire d'id : {}" , proprietaireId);
        }
        //log sortie de la méthode getProprietesByProprietaire du service ProprieteService
        log.info("Sortie de la méthode getProprietesByProprietaire du service ProprieteService");
        //retourner la liste des propriétés
        return proprietes;
    }

    //récupérer une propriété par son id
    public Propriete getProprieteById(String proprieteId) {
        //log entrée dans la méthode getProprieteById du service ProprieteService
        log.info("Entrée dans la méthode getProprieteById du service ProprieteService");
        //initialisation de la propriété
        Propriete propriete = new Propriete();
        //try catch pour récupérer la propriété
        try {
            //récupération de la propriété
            propriete = proprieteRepo.findById(UUID.fromString(proprieteId)).orElse(null);
            //log récupération de la propriété
            log.info("Récupération de la propriété");
        } catch (Exception e) {
            //log erreur récupération de la propriété
            log.error("Erreur lors de la récupération de la propriété");
        }
        //si la propriété est vide
        if (propriete == null) {
            //log aucune propriété trouvée dans la base de données
            log.error("Aucune propriété trouvée dans la base de données pour l'id : {}" , proprieteId);
        }
        //log sortie de la méthode getProprieteById du service ProprieteService
        log.info("Sortie de la méthode getProprieteById du service ProprieteService");
        //retourner la propriété
        return propriete;
    }

    //ajouter une propriété
    public Propriete addPropriete(ProprieteDto propriete) {
        //log entrée dans la méthode addPropriete du service ProprieteService
        log.info("Entrée dans la méthode addPropriete du service ProprieteService");
        //try catch pour ajouter la propriété
        Propriete proprieteToSave = null;
        try {
            //mapper la propriété
            proprieteToSave = mapper.map(propriete, Propriete.class);
            //récupérer l'agence
            Agence agence = agenceRepo.findById(UUID.fromString(propriete.getAgenceId())).orElse(null);
            if (agence == null) {
                //log aucune agence trouvée dans la base de données
                throw new Exception("Aucune agence trouvée dans la base de données pour l'id : {}" + propriete.getAgenceId());
            }
            //récupérer le propriétaire
            Proprietaire proprietaire = proprietaireRepo.findById(UUID.fromString(propriete.getProprietaireId())).orElse(null);
            if (proprietaire == null) {
                //log aucun propriétaire trouvé dans la base de données
                throw new Exception("Aucun propriétaire trouvé dans la base de données pour l'id : {}" + propriete.getProprietaireId());
            }
            proprieteToSave.setAgence(agence);
            proprieteToSave.setProprietaire(proprietaire);
            //ajouter la propriété
            proprieteToSave = proprieteRepo.save(proprieteToSave);
        } catch (Exception e) {
            //log erreur ajout de la propriété
            log.error("Erreur lors de l'ajout de la propriété");
        }
        ;
        if (proprieteToSave != null && proprieteToSave.getId() != null) {
            //log enregistrement de la propriété avec l'id
            log.info("Enregistrement de la propriété avec l'id : {}" , proprieteToSave.getId());
        }
        //log sortie de la méthode addPropriete du service ProprieteService
        log.info("Sortie de la méthode addPropriete du service ProprieteService");
        //retourner la propriété
        return proprieteToSave;
    }

    //modifier une propriété
    public Propriete updatePropriete(Propriete propriete) {
        //log entrée dans la méthode updatePropriete du service ProprieteService
        log.info("Entrée dans la méthode updatePropriete du service ProprieteService");
        try {


            //modifier la propriété
            propriete= proprieteRepo.save(propriete);
        } catch (Exception e) {
            //log erreur modification de la propriété
            log.error("Erreur lors de la modification de la propriété");
        }
        ;
        if (propriete != null && propriete.getId() != null) {
            //log modification de la propriété avec l'id
            log.info("Modification de la propriété avec l'id : {}" , propriete.getId());
        }
        //log sortie de la méthode updatePropriete du service ProprieteService
        log.info("Sortie de la méthode updatePropriete du service ProprieteService");
        //retourner la propriété
        return propriete;
    }

    //supprimer une propriété
    public void deletePropriete(String proprieteId) {
        //log entrée dans la méthode deletePropriete du service ProprieteService
        log.info("Entrée dans la méthode deletePropriete du service ProprieteService");
        //try catch pour supprimer la propriété
        try {
            //supprimer la propriété
            proprieteRepo.deleteById(UUID.fromString(proprieteId));
            //log suppression de la propriété
            log.info("Suppression de la propriété");
        } catch (Exception e) {
            //log erreur suppression de la propriété
            log.error("Erreur lors de la suppression de la propriété");
        }
        //log sortie de la méthode deletePropriete du service ProprieteService
        log.info("Sortie de la méthode deletePropriete du service ProprieteService");
    }
}
