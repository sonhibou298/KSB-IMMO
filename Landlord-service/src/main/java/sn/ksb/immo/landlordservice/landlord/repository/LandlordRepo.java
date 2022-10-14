package sn.ksb.immo.landlordservice.landlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ksb.immo.landlordservice.landlord.model.Landlord;

import java.util.UUID;

@Repository
public interface LandlordRepo extends JpaRepository<Landlord, UUID> {
    Landlord findByPassportNumber(String passportNumber);
    Landlord findByIdCardNumber(String idCardNumber);

    boolean existsByPassportNumber(String passportNumber);

    boolean existsByIdCardNumber(String idCardNumber);

    void deleteByPassportNumber(String passportNumber);

    void deleteByIdCardNumber(String idCardNumber);

    Landlord findByPassportNumberAndIdCardNumber(String passportNumber, String idCardNumber);
}
