package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Propriete;

import java.util.UUID;

@Repository
public interface ProprieteRepo extends JpaRepository<Propriete, UUID> {

}
