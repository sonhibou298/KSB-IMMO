package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Locataire;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocataireRepo extends JpaRepository<Locataire, UUID> {

    Locataire findByCni(String cni);

    List<Locataire> findByLoyer_Propriete_Id(UUID id);
    Boolean existsByCni(String cni);

    //List<Locataire> findByProprietes_id(UUID fromString);

    void deleteByCni(String cni);

    Locataire findByNomAndPrenomAndLoyer_Propriete_Id(String non, String prenom, String adresse);

    //List<Locataire> findByLoyers_Mensualites_datePaiementBetween(Date dateDebut, Date dateFin);

}
