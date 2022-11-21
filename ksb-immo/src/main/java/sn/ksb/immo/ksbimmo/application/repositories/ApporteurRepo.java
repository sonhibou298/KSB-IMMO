package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Apporteur;

@Repository
public interface ApporteurRepo extends JpaRepository<Apporteur, String> {

    Apporteur findByCni(String cni);

    void deleteByCni(String cni);
}
