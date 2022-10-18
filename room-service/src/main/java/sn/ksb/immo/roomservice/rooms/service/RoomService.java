package sn.ksb.immo.roomservice.rooms.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import sn.ksb.immo.roomservice.rooms.enums.RoomType;
import sn.ksb.immo.roomservice.rooms.model.Room;
import sn.ksb.immo.roomservice.rooms.model.dto.RoomDTO;
import sn.ksb.immo.roomservice.rooms.repos.RoomRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RoomService {

    private final RoomRepo repo;

    private final ModelMapper mapper;

    public RoomService(RoomRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    //get all rooms by property id
    public List<Room> getRoomsByPropertyId(String propertyId) {
        //log entering the service
        log.info("Entering in getRoomsByPropertyId method of RoomService");
        //create a list of rooms
        List<Room> rooms;
        //try to get the list of rooms from the repo
        try {
            rooms = repo.findByPropertyId(propertyId);
        } catch (Exception e) {
            //if an error occurs, log it and return an empty list
            log.error("Error while getting the list of rooms", e);
            rooms = null;
        }
        //log empty list of rooms if the list is empty
        if (rooms.isEmpty()) {
            log.info("No rooms found for property id {}", propertyId);
        }
        //log exiting the service
        log.info("Exiting from getRoomsByPropertyId method of RoomService");
        //return the list of rooms
        return rooms;
    }

    //get a room by its UUID
    public Room getRoomByUUID(String roomUUID) {
        //log entering the service with the room UUID
        log.info("Entering in getRoomByUUID method of RoomService with room UUID {}", roomUUID);
        //create a room
        Room room;
        //try to get the room from the repo
        try {
            room = repo.findById(UUID.fromString(roomUUID)).orElse(null);
        } catch (Exception e) {
            //if an error occurs, log it and return null
            log.error("Error while getting the room", e);
            room = null;
        }
        //log no room found if the room is null
        if (room == null) {
            log.info("No room found for room UUID {}", roomUUID);
        }
        //log exiting the service
        log.info("Exiting from getRoomByUUID method of RoomService");
        //return the room
        return room;
    }


    public void createRoom(String propertyId, int numberOfRooms, String type) {
        //log entering the service with the property id, number of rooms and type
        log.info("Entering in createRoom method of RoomService with property id {}, number of rooms {} and type {}", propertyId, numberOfRooms, type);
        //create a room
        Room room;
        //try to create the room
        try {
            //create rooms based on the number of rooms
            for (int i = 0; i < numberOfRooms; i++) {
                //create a room
                room = new Room();
                //set the property id
                room.setPropertyId(UUID.fromString(propertyId));
                //set the type
                room.setType(RoomType.valueOf(type.toUpperCase()));
                //set the creation date
                room.setCreatedAt(new Date());
                //set the update date
                room.setUpdatedAt(new Date());
                room.setDeleted(false);

                //save the room
                repo.save(room);
            }
        } catch (Exception e) {
            //if an error occurs, log it
            log.error("Error while creating the room", e);
        }
        //log exiting the service
        log.info("Exiting from createRoom method of RoomService");
    }
}
