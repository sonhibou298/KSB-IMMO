package sn.ksb.immo.ksbimmo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.ksbimmo.application.models.Agence;

import java.util.List;
import java.util.UUID;

@Repository
public interface AgenceRepo extends JpaRepository<Agence, UUID> {

}
