package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Proprietaire;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProprietaireRepo extends JpaRepository<Proprietaire, UUID> {

    Proprietaire findByCni(String cni);

    List<Proprietaire> findByDeletedFalse();

    Boolean existsByCni(String cni);
}
