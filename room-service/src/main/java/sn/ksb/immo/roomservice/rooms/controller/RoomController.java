package sn.ksb.immo.roomservice.rooms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.roomservice.rooms.service.RoomService;

@RestController
@Slf4j
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    //get rooms list by property id
    @GetMapping("/{propertyId}/rooms")
    public void getRoomsByPropertyId(@PathVariable String propertyId) {
        //log entering the controller
        log.info("Entering in getRoomsByPropertyId method of RoomController");
        //try to get the list of rooms from the service
        try {
            service.getRoomsByPropertyId(propertyId);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while getting the list of rooms", e);
        }
        //log exiting the controller
        log.info("Exiting from getRoomsByPropertyId method of RoomController");
    }

    //get a room by its UUID
    @GetMapping("/{roomUUID}")
    public void getRoomByUUID(@PathVariable String roomUUID) {
        //log entering the controller with the room UUID
        log.info("Entering in getRoomByUUID method of RoomController with room UUID {}", roomUUID);
        //try to get the room from the service
        try {
            service.getRoomByUUID(roomUUID);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while getting the room", e);
        }
        //log exiting the controller
        log.info("Exiting from getRoomByUUID method of RoomController");
    }

    //create a room by property id, number of rooms and type
    @PostMapping("/{propertyId}/{numberOfRooms}/{type}")
    public void createRoom(@PathVariable String propertyId, @PathVariable int numberOfRooms, @PathVariable String type) {
        //log entering the controller with the property id, number of rooms and type
        log.info("Entering in createRoom method of RoomController with property id {}, number of rooms {} and type {}", propertyId, numberOfRooms, type);
        //try to create the room from the service
        try {
            service.createRoom(propertyId, numberOfRooms, type);
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while creating the room", e);
        }
        //log exiting the controller
        log.info("Exiting from createRoom method of RoomController");
    }
}
