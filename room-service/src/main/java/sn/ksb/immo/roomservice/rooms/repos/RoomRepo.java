package sn.ksb.immo.roomservice.rooms.repos;

import sn.ksb.immo.roomservice.rooms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface RoomRepo extends JpaRepository<Room, UUID> {

    List<Room> findByPropertyId(String propertyId);
}
