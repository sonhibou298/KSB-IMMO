package sn.ksb.immo.propertyservice.property.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.propertyservice.property.model.dto.PropertyDTO;
import sn.ksb.immo.propertyservice.property.service.PropertyService;
import sn.ksb.immo.propertyservice.property.model.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/properties")
@Slf4j
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    //get properties list with pagination
    @GetMapping
    public List<Property> getAllProperties(@RequestParam(defaultValue = "0") int page) {
        //log entering the controller
        log.info("Entering in getAllProperties method of PropertyController");
        //create a list of properties
        List<Property> properties = null;
        //try to get the list of properties from the service
        try {
            properties = service.getProperties(page);
        } catch (Exception e) {
            //if an error occurs, log it and return an empty list
            log.error("Error while getting the list of properties", e);
            properties = new ArrayList<>();
        }
        //log exiting the controller
        log.info("Exiting from getAllProperties method of PropertyController");
        //return the list of properties
        return properties;
    }

    //get a property by its UUID
    @GetMapping("/{propertyUUID}")
    public Property getPropertyByUUID(@PathVariable String propertyUUID) {
        //log entering the controller
        log.info("Entering in getPropertyByUUID method of PropertyController");
        //create a property
        Property property = null;
        //try to get the property from the service
        try {
            property = service.getPropertyById(propertyUUID);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the property", e);
            property = null;
        }
        //log exiting the controller
        log.info("Exiting from getPropertyByUUID method of PropertyController");
        //return the property
        return property;
    }

    //get properties by their landlord UUID
    @GetMapping("/landlord/{landlordUUID}")
    public List<Property> getPropertiesByLandlordUUID(@PathVariable String landlordUUID) {
        //log entering the controller
        log.info("Entering in getPropertiesByLandlordUUID method of PropertyController");
        //create a list of properties
        List<Property> properties = null;
        //try to get the list of properties from the service
        try {
            properties = service.getPropertyByLandlordId(landlordUUID);
        } catch (Exception e) {
            //if an error occurs, log it and return an empty list
            log.error("Error while getting the list of properties", e);
            properties = new ArrayList<>();
        }
        //log exiting the controller
        log.info("Exiting from getPropertiesByLandlordUUID method of PropertyController");
        //return the list of properties
        return properties;
    }

    //create a property with a landlord UUID
    @PostMapping("/create/{landlordUUID}")
    public Property createProperty(@RequestBody PropertyDTO property, @PathVariable String landlordUUID) {
        //log entering the controller
        log.info("Entering in createProperty method of PropertyController");
        //create a property
        Property createdProperty = null;
        //try to create the property from the service
        try {
            createdProperty = service.createProperty(property, landlordUUID);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while creating the property", e);
            createdProperty = null;
        }
        //log exiting the controller
        log.info("Exiting from createProperty method of PropertyController");
        //return the property
        return createdProperty;
    }

    //create properties with a landlord UUID
    @PostMapping("/create-properties/{landlordUUID}")
    public List<UUID> createProperties(@RequestBody List<PropertyDTO> properties, @PathVariable String landlordUUID) {
        //log entering the controller
        log.info("Entering in createProperties method of PropertyController");
        //create a list of properties
        List<UUID> createdProperties = null;
        //try to create the list of properties from the service
        try {
            createdProperties = service.createProperties(properties, landlordUUID);
        } catch (Exception e) {
            //if an error occurs, log it and return an empty list
            log.error("Error while creating the list of properties", e);
            createdProperties = new ArrayList<>();
        }
        //log exiting the controller
        log.info("Exiting from createProperties method of PropertyController");
        //return the list of properties
        return createdProperties;
    }

    //update a property
    @PutMapping
    public Property updateProperty(@RequestBody Property property) {
        //log entering the controller
        log.info("Entering in updateProperty method of PropertyController");
        //create a property
        Property updatedProperty = null;
        //try to update the property from the service
        try {
            updatedProperty = service.updateProperty(property);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while updating the property", e);
            updatedProperty = null;
        }
        //log exiting the controller
        log.info("Exiting from updateProperty method of PropertyController");
        //return the property
        return updatedProperty;
    }

    //delete a property
    @DeleteMapping("/{propertyUUID}")
    public void deleteProperty(String propertyUUID) {
        //log entering the controller
        log.info("Entering in deleteProperty method of PropertyController");
        //try to delete the property from the service
        try {
            service.deleteProperty(propertyUUID);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the property", e);
        }
    }

    //delete all properties of a landlord
    @DeleteMapping("/landlord/{landlordUUID}")
    public void deletePropertiesByLandlordUUID(@PathVariable String landlordUUID) {
        //log entering the controller
        log.info("Entering in deletePropertiesByLandlordUUID method of PropertyController");
        //try to delete the properties from the service
        try {
            service.deletePropertiesByLandlordId(landlordUUID);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the properties", e);
        }
    }

}
