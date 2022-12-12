package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Loyer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoyerRepo extends JpaRepository<Loyer, UUID> {

    List<Loyer> findByDernierPaiementBetween(Date dateDebut, Date dateFin);

    List<Loyer> findByDernierPaiementBefore(Date dateDebut);

    List<Loyer> findByDernierPaiementAfter(Date dateDebut);

    List<Loyer> findByDateProchainPaiementAfter(Date today);


    @Query("SELECT l FROM Loyer l WHERE month(l.dateProchainPaiement) = ?1")
    List<Loyer> findByCurrentMonth(int mois);

    List<Loyer> findByPropriete_Id(UUID fromString);
}
