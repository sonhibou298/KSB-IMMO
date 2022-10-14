package sn.ksb.immo.propertyservice.property.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sn.ksb.immo.propertyservice.property.enums.MeterType;
import sn.ksb.immo.propertyservice.property.enums.PropertyType;
import sn.ksb.immo.propertyservice.property.model.Property;
import sn.ksb.immo.propertyservice.property.model.dto.PropertyDTO;
import sn.ksb.immo.propertyservice.property.repos.PropertyRepo;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PropertyService {

    private final PropertyRepo repo;

    private final ModelMapper mapper;

    private final RestTemplate template;

    public PropertyService(PropertyRepo repo, ModelMapper mapper, RestTemplate template) {
        this.repo = repo;
        this.mapper = mapper;
        this.template = template;
    }

    public List<Property> getPropertyByLandlordId(String landlordId) {
        //log entering the service with the landlordId
        log.info("Entering in getPropertyByLandlordId method of PropertyService with the landlordId: {}", landlordId);
        //convert the landlordId to UUID
        UUID landlordUUID = UUID.fromString(landlordId);
        List<Property> properties = null;
        //try to get the properties from the repository by the landlord UUID
        try {
            properties = repo.findByLandlordId(landlordUUID);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while getting the properties by the landlord UUID : " + e.getMessage());
        }
        //return the properties if the list is not empty
        if (properties != null && !properties.isEmpty()) {
            return properties;
        }
        //if the list is empty, log it and return null
        log.info("No properties found for the landlord with the UUID: {}", landlordUUID);
        return null;
    }

    //get properties list with pagination
    public List<Property> getProperties(int page) {
        //log entering the service
        log.info("Entering in getProperties method of PropertyService");
        List<Property> properties = null;
        //try to get the properties from the repository
        try {
            //get the properties from the repository with pagination
            properties = repo.findAll(PageRequest.of(page, 25)).getContent();
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while getting the properties : " + e.getMessage());
        }
        //return the properties if the list is not empty
        if (properties != null && !properties.isEmpty()) {
            return properties;
        }
        //if the list is empty, log it and return null
        log.info("No properties found");
        return null;
    }

    //get a property by its UUID
    public Property getPropertyById(String propertyId) {
        //log entering the service with the propertyId
        log.info("Entering in getPropertyById method of PropertyService with the propertyId: {}", propertyId);
        //convert the propertyId to UUID
        UUID propertyUUID = UUID.fromString(propertyId);
        Property property = null;
        //try to get the property from the repository by the property UUID
        try {
            property = repo.findById(propertyUUID).orElse(null);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while getting the property by the property UUID : " + e.getMessage());
        }
        //return the property if it is not null
        if (property != null) {
            return property;
        }
        //if the property is null, log it and return null
        log.info("No property found for the property with the UUID: {}", propertyUUID);
        return null;
    }

    //create a property from a propertyDTO and a landlordId
    public Property createProperty(PropertyDTO dto, String landlordId) {
        //log entering the service with the propertyDTO and the landlordId
        log.info("Entering in createProperty method of PropertyService with the propertyDTO: {} and the landlordId: {}", dto, landlordId);
        //convert the landlordId to UUID
        UUID landlordUUID = UUID.fromString(landlordId);
        Property property = null;
        //try to create the property from the propertyDTO and the landlord UUID
        try {
            //map the propertyDTO to a property
            property = mapper.map(dto, Property.class);
            //set the landlord UUID to the property
            property.setLandlordId(landlordUUID);
            //set the created at date to the property
            property.setCreatedAt(new Date());
            //set the updated at date to the property
            property.setUpdatedAt(new Date());
            //set the enum values of water and electricity based on the dto values
            property.setWater(MeterType.valueOf(dto.getWater().toUpperCase()));
            property.setElectricity(MeterType.valueOf(dto.getElectricity().toUpperCase()));
            property.setType(PropertyType.valueOf(dto.getType().toUpperCase()));
            //save the property in the repository
            property = repo.save(property);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while creating the property : " + e.getMessage());
        }
        //return the property if it is not null
        if (property != null) {
            return property;
        }
        //if the property is null, log it and return null
        log.info("The property could not be created");
        return null;
    }

    //update a Property
    public Property updateProperty(Property property) {
        //log entering the service with the property
        log.info("Entering in updateProperty method of PropertyService with the property: {}", property);
        Property updatedProperty = null;
        //try to update the property
        try {
            //save the property in the repository
            updatedProperty = repo.save(property);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while updating the property : " + e.getMessage());
        }
        //return the property if it is not null
        if (updatedProperty != null) {
            return updatedProperty;
        }
        //if the property is null, log it and return null
        log.info("The property could not be updated");
        return null;
    }

    //delete a property by its UUID
    public void deleteProperty(String propertyId) {
        //log entering the service with the propertyId
        log.info("Entering in deleteProperty method of PropertyService with the propertyId: {}", propertyId);
        //convert the propertyId to UUID
        UUID propertyUUID = UUID.fromString(propertyId);
        //try to delete the property by the property UUID
        try {
            //find the property by the property UUID
            Property property = repo.findById(propertyUUID).orElse(null);
            //if the property is not null
            if (property != null) {
                //set the deleted flag to true and delete date to now
                property.setDeleted(true);
                property.setDeletedAt(new Date());
                //save the property in the repository
                repo.save(property);
            }
            //if the property is null, log it
            else {
                log.info("No property found for the property with the UUID: {}", propertyUUID);
            }
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the property by the property UUID : " + e.getMessage());
        }
    }

    //create properties from a list of propertyDTOs and a landlordId
    public List<UUID> createProperties(List<PropertyDTO> dtos, String landlordId) {
        //log entering the service with the propertyDTOs and the landlordId
        log.info("Entering in createProperties method of PropertyService with the propertyDTOs: {} and the landlordId: {}", dtos, landlordId);
        //convert the landlordId to UUID
        UUID landlordUUID = UUID.fromString(landlordId);
        List<Property> properties = null;
        //try to create the properties from the propertyDTOs and the landlord UUID
        try {
            //loop through the propertyDTOs and call the createProperty method for each propertyDTO
            properties = dtos.stream().map(dto -> createProperty(dto, landlordId)).collect(Collectors.toList());
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while creating the properties : " + e.getMessage());
        }
        //return the properties if the list is not empty
        if (properties != null && !properties.isEmpty()) {
            return properties.stream().map(Property::getId).collect(Collectors.toList());
        }
        //if the list is empty, log it and return null
        log.info("The properties could not be created");
        return null;
    }

    public void deletePropertiesByLandlordId(String landlordUUID) {
        //log entering the service with the landlordUUID
        log.info("Entering in deletePropertiesByLandlordId method of PropertyService with the landlordUUID: {}", landlordUUID);
        //try to delete the properties by the landlord UUID
        try {
            //find the properties by the landlord UUID
            List<Property> properties = repo.findByLandlordId(UUID.fromString(landlordUUID));
            //if the properties are not empty
            if (properties != null && !properties.isEmpty()) {
                //set the deleted flag to true and delete date to now
                properties.forEach(property -> {
                    property.setDeleted(true);
                    property.setDeletedAt(new Date());
                });
                //save the properties in the repository
                repo.saveAll(properties);
            }
            //if the properties are empty, log it
            else {
                log.info("No properties found for the landlord with the UUID: {}", landlordUUID);
            }
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while deleting the properties by the landlord UUID : " + e.getMessage());
        }
    }
}
