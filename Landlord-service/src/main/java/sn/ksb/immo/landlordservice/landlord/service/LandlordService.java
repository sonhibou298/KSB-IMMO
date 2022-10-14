package sn.ksb.immo.landlordservice.landlord.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import sn.ksb.immo.landlordservice.landlord.model.Landlord;
import sn.ksb.immo.landlordservice.landlord.model.dto.LandlordDTO;
import sn.ksb.immo.landlordservice.landlord.repository.LandlordRepo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LandlordService {
    private final LandlordRepo landlordRepo;

    private final ModelMapper mapper;

    private final RestTemplate restTemplate;

    @Value("${app.property-service.url}")
    private String propertyServiceUrl;

    public LandlordService(LandlordRepo landlordRepo, ModelMapper mapper, RestTemplate restTemplate) {
        this.landlordRepo = landlordRepo;
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    //find landlords list with pagination and size of 15
    public List<Landlord> findLandlords(int page) {
        //log entering method with page number
        log.info("Entering findLandlords method with page number: " + page);
        //try to find landlords list or log an error
        List<Landlord> landlords = null;
        try {
            //find landlords list with pagination
            landlords = landlordRepo.findAll(PageRequest.of(page, 15)).getContent();
            //log landlords list size
            log.info("Landlords list size: " + landlords.size());

        } catch (Exception e) {
            //log error
            log.error("Error while finding landlords list: " + e.getMessage());

        }
        //log exiting method with landlords DTO list size
        log.info("Exiting findLandlords method with landlords DTO list size: " + landlords.size());
        return landlords;
    }

    //find landlord by passport number
    public Landlord findLandlordByPassportNumber(String passportNumber) {
        //log entering method with passport number
        log.info("Entering findLandlordByPassportNumber method with passport number: " + passportNumber);
        //try to find landlord or log an error
        Landlord landlord = null;
        try {
            //find landlord by passport number
            landlord = landlordRepo.findByPassportNumber(passportNumber);
            //return null if landlord is not found
            if (landlord == null) {
                log.info("Landlord not found");
                return null;
            }
            //log landlord
            log.info("Landlord: " + landlord);
        } catch (Exception e) {
            //log error
            log.error("Error while finding landlord by passport number: " + e.getMessage());
        }
        //log exiting method with landlord DTO
        log.info("Exiting findLandlordByPassportNumber method with landlord: " + landlord.getFirstName() + " " + landlord.getLastName());
        return landlord;
    }

    //find landlord by id card number
    public Landlord findLandlordByIdCardNumber(String idCardNumber) {
        //log entering method with id card number
        log.info("Entering findLandlordByIdCardNumber method with id card number: " + idCardNumber);
        Landlord landlordDTO = null;
        //try to find landlord or log an error
        try {
            //find landlord by id card number
            Landlord landlord = landlordRepo.findByIdCardNumber(idCardNumber);
            //return null if landlord is not found
            if (landlord == null) {
                log.info("Landlord not found");
                return null;
            }
            //log landlord
            log.info("Landlord: " + landlord);
        } catch (Exception e) {
            //log error
            log.error("Error while finding landlord by id card number: " + e.getMessage());
        }
        //log exiting method with landlord DTO
        log.info("Exiting findLandlordByIdCardNumber method with landlord DTO: " + landlordDTO);
        return landlordDTO;
    }

    //create landlord
    public Landlord createLandlord(LandlordDTO landlordDTO) {
        //log entering method with landlord DTO
        log.info("Entering createLandlord method with landlord DTO: " + landlordDTO);
        //try to create landlord or log an error
        Landlord landlord = null;
        UUID landlordUUID;
        try {
            //convert landlord DTO to landlord
            landlord = mapper.map(landlordDTO, Landlord.class);
            //check if landlord already exists
            if (landlordRepo.existsByPassportNumber(landlord.getPassportNumber()) || landlordRepo.existsByIdCardNumber(landlord.getIdCardNumber())) {
                //log error
                log.error("Landlord already exists");
                //get landlord UUID by passport number and id card number
                landlordUUID = landlordRepo.findByPassportNumberAndIdCardNumber(landlord.getPassportNumber(), landlord.getIdCardNumber()).getId();
            }else {
                //check if landlordDto property list is not empty
                if (landlordDTO.getProperties().isEmpty()) {
                    //log error
                    log.error("Landlord property list is empty");
                    return null;
                }
                //fill landlord created at date and updated at date
                landlord.setCreatedAt(new Date());
                landlord.setUpdatedAt(new Date());
                landlordUUID = UUID.randomUUID();
                //check if UUID is already used
                while (landlordRepo.existsById(landlordUUID)) {
                    //generate new UUID
                    landlordUUID = UUID.randomUUID();
                }
            }
            if (landlordUUID != null) {
                //try to create landlord properties or log an error
                try {
                    //create landlord properties and get landlord properties UUID list
                    List<UUID> landlordPropertiesUUIDList = restTemplate.postForObject(propertyServiceUrl + "create-properties/" + landlordUUID, landlordDTO.getProperties(), List.class);
                    //check if landlord properties UUID list is not empty
                    if (landlordPropertiesUUIDList.isEmpty()) {
                        //remove landlord
                        landlordRepo.deleteById(landlordUUID);
                    }
                    //log landlord properties UUID list
                    log.info("Landlord properties UUID list: " + landlordPropertiesUUIDList);
                    //log landlord properties created
                    log.info("Landlord properties created");
                    landlord.setId(landlordUUID);
                    //save landlord
                    landlordRepo.save(landlord);

                } catch (ResourceAccessException e) {
                    //log error
                    log.error("Error while creating landlord properties: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            //log error
            log.error("Error while creating landlord: " + e.getMessage());
        }
        //log exiting method with landlord DTO
        log.info("Exiting createLandlord method ");
        return landlord;
    }

    //update landlord
    public Landlord updateLandlord(LandlordDTO landlordDTO) {
        //log entering method with landlord DTO
        log.info("Entering updateLandlord method with landlord DTO: " + landlordDTO);
        //try to update landlord or log an error
        Landlord landlord = null;
        try {
            //convert landlord DTO to landlord
            landlord = mapper.map(landlordDTO, Landlord.class);
            //check if landlord already exists
            if (landlordRepo.existsByPassportNumber(landlord.getPassportNumber()) || landlordRepo.existsByIdCardNumber(landlord.getIdCardNumber())) {
                //log error
                log.error("Landlord already exists");
                return null;
            }
            //fill landlord updated at date
            landlord.setUpdatedAt(new Date());
            //update landlord
            landlord = landlordRepo.save(landlord);

        } catch (Exception e) {
            //log error
            log.error("Error while updating landlord: " + e.getMessage());
        }
        //log exiting method with landlord DTO
        log.info("Exiting updateLandlord method ");
        return landlord;
    }

    //delete landlord by passport number
    public void deleteLandlordByPassportNumber(String passportNumber) {
        //log entering method with passport number
        log.info("Entering deleteLandlordByPassportNumber method with passport number: " + passportNumber);
        //try to delete landlord or log an error
        try {
            //check if landlord exists
            if (landlordRepo.existsByPassportNumber(passportNumber)) {
                //retrieve landlord by passport number
                Landlord landlord = landlordRepo.findByPassportNumber(passportNumber);
                //set landlord deleted at date
                landlord.setDeletedAt(new Date());
                //set deleted boolean to true
                landlord.setDeleted(true);
                landlordRepo.save(landlord);
            } else {
                //log error
                log.error("Landlord does not exist");
            }
        } catch (Exception e) {
            //log error
            log.error("Error while deleting landlord by passport number: " + e.getMessage());
        }
        //log exiting method
        log.info("Exiting deleteLandlordByPassportNumber method ");
    }

    //delete landlord by id card number
    public void deleteLandlordByIdCardNumber(String idCardNumber) {
        //log entering method with id card number
        log.info("Entering deleteLandlordByIdCardNumber method with id card number: " + idCardNumber);
        //try to delete landlord or log an error
        try {
            //check if landlord exists
            if (landlordRepo.existsByIdCardNumber(idCardNumber)) {
                //retrieve landlord by id card number
                Landlord landlord = landlordRepo.findByIdCardNumber(idCardNumber);
                //set landlord deleted at date
                landlord.setDeletedAt(new Date());
                //set deleted boolean to true
                landlord.setDeleted(true);
                landlordRepo.save(landlord);
            } else {
                //log error
                log.error("Landlord does not exist");
            }
        } catch (Exception e) {
            //log error
            log.error("Error while deleting landlord by id card number: " + e.getMessage());
        }
        //log exiting method
        log.info("Exiting deleteLandlordByIdCardNumber method ");
    }

    //retrieve landlord UUID by passport number
    public UUID retrieveLandlordUUIDByPassportNumber(String passportNumber) {
        //log entering method with passport number
        log.info("Entering retrieveLandlordUUIDByPassportNumber method with passport number: " + passportNumber);
        UUID landlordUUID = null;
        //try to retrieve landlord UUID or log an error
        try {
            //check if landlord exists
            if (landlordRepo.existsByPassportNumber(passportNumber)) {
                //retrieve landlord by passport number
                Landlord landlord = landlordRepo.findByPassportNumber(passportNumber);
                //log exiting method with landlord UUID
                log.info("Exiting retrieveLandlordUUIDByPassportNumber method with landlord UUID: " + landlord.getId());
                landlordUUID = landlord.getId();
            } else {
                //log error
                log.error("Landlord does not exist");
            }
        } catch (Exception e) {
            //log error
            log.error("Error while retrieving landlord UUID by passport number: " + e.getMessage());
        }
        //log exiting method with null
        log.info("Exiting retrieveLandlordUUIDByPassportNumber method with null");
        return landlordUUID;
    }

    //retrieve landlord UUID by id card number
    public UUID retrieveLandlordUUIDByIdCardNumber(String idCardNumber) {
        //log entering method with id card number
        log.info("Entering retrieveLandlordUUIDByIdCardNumber method with id card number: " + idCardNumber);
        UUID landlordUUID = null;
        //try to retrieve landlord UUID or log an error
        try {
            //check if landlord exists
            if (landlordRepo.existsByIdCardNumber(idCardNumber)) {
                //retrieve landlord by id card number
                Landlord landlord = landlordRepo.findByIdCardNumber(idCardNumber);
                //log exiting method with landlord UUID
                log.info("Exiting retrieveLandlordUUIDByIdCardNumber method with landlord UUID: " + landlord.getId());
                landlordUUID = landlord.getId();
            } else {
                //log error
                log.error("Landlord does not exist");
            }
        } catch (Exception e) {
            //log error
            log.error("Error while retrieving landlord UUID by id card number: " + e.getMessage());
        }
        //log exiting method with null
        log.info("Exiting retrieveLandlordUUIDByIdCardNumber method with null");
        return landlordUUID;
    }

}
