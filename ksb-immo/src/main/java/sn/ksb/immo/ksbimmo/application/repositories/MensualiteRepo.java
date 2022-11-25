package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Mensualite;

@Repository
public interface MensualiteRepo extends JpaRepository<Mensualite, String> {

}
