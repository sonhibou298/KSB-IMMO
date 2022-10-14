package sn.ksb.immo.landlordservice.landlord.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.landlordservice.landlord.model.Landlord;
import sn.ksb.immo.landlordservice.landlord.model.dto.LandlordDTO;
import sn.ksb.immo.landlordservice.landlord.service.LandlordService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/landlords")
public class LandlordController {

    private final LandlordService service;

    public LandlordController(LandlordService service) {
        this.service = service;
    }

    //get landlords list with pagination
    @GetMapping
    public List<Landlord> getAllLandlords(@RequestParam(defaultValue = "0") int page) {
        //log entering the controller
        log.info("Entering in getAllLandlords method of LandlordController");
        //try to get the list of landlords from the service
        try {
            return service.findLandlords(page);
        } catch (Exception e) {
            //if an error occurs, log it and return an empty list
            log.error("Error while getting the list of landlords", e);
            return new ArrayList<>();
        }
    }

    //get a landlord UUID by his passport number
    @GetMapping("/passport/uuid/{passportNumber}")
    public UUID getLandlordIdByPassportNumber(@PathVariable String passportNumber) {
        //log entering the controller
        log.info("Entering in getLandlordIdByPassportNumber method of LandlordController");
        //try to get the landlord UUID from the service
        try {
            return service.retrieveLandlordUUIDByPassportNumber(passportNumber);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the landlord UUID", e);
            return null;
        }
    }

    //get a landlord UUID by his ID card number
    @GetMapping("/idcard/uuid/{idCardNumber}")
    public UUID getLandlordIdByIdCardNumber(@PathVariable String idCardNumber) {
        //log entering the controller
        log.info("Entering in getLandlordIdByIdCardNumber method of LandlordController");
        //try to get the landlord UUID from the service
        try {
            return service.retrieveLandlordUUIDByIdCardNumber(idCardNumber);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the landlord UUID", e);
            return null;
        }
    }

    //create a new landlord
    @PostMapping
    public Landlord createLandlord(@Validated @RequestBody LandlordDTO landlord) {
        //log entering the controller
        log.info("Entering in createLandlord method of LandlordController");
        Landlord landlord1 = null;
        //try to create the landlord
        try {
            landlord1 =  service.createLandlord(landlord);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while creating the landlord", e);
            landlord1 = null;
        }
        return landlord1;
    }

    //update a landlord
    @PutMapping
    public Landlord updateLandlord(@Validated @RequestBody LandlordDTO landlord) {
        //log entering the controller
        log.info("Entering in updateLandlord method of LandlordController");
        //try to update the landlord
        try {
            return service.updateLandlord(landlord);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while updating the landlord", e);
            return null;
        }
    }

    //delete a landlord by his passport number
    @DeleteMapping("/delete/passport/{passportNumber}")
    public void deleteLandlordByPassportNumber(@PathVariable String passportNumber) {
        //log entering the controller
        log.info("Entering in deleteLandlordByPassportNumber method of LandlordController");
        //try to delete the landlord
        try {
            service.deleteLandlordByPassportNumber(passportNumber);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the landlord", e);
        }
    }

    //delete a landlord by his ID card number
    @DeleteMapping("/delete/idcard/{idCardNumber}")
    public void deleteLandlordByIdCardNumber(@PathVariable String idCardNumber) {
        //log entering the controller
        log.info("Entering in deleteLandlordByIdCardNumber method of LandlordController");
        //try to delete the landlord
        try {
            service.deleteLandlordByIdCardNumber(idCardNumber);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the landlord", e);
        }
    }

    //get a landlord by his passport number
    @GetMapping("/passport/{passportNumber}")
    public Landlord getLandlordByPassportNumber(@PathVariable String passportNumber) {
        //log entering the controller
        log.info("Entering in getLandlordByPassportNumber method of LandlordController");
        //try to get the landlord from the service
        try {
            return service.findLandlordByPassportNumber(passportNumber);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the landlord", e);
            return null;
        }
    }

    //get a landlord by his ID card number
    @GetMapping("/idcard/{idCardNumber}")
    public Landlord getLandlordByIdCardNumber(@PathVariable String idCardNumber) {
        //log entering the controller
        log.info("Entering in getLandlordByIdCardNumber method of LandlordController");
        //try to get the landlord from the service
        Landlord landlord = null;
        try {
            landlord = service.findLandlordByIdCardNumber(idCardNumber);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the landlord :" + e.getMessage());
        }
        return landlord;
    }
}
