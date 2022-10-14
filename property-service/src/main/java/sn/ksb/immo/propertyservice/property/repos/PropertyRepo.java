package sn.ksb.immo.propertyservice.property.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.propertyservice.property.model.Property;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepo extends JpaRepository<Property, UUID> {
    List<Property> findByLandlordId(UUID landlordUUID);
}
