package nl.han.oose.dynamicroombackend.config;

import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Room;
import org.modelmapper.PropertyMap;

public class RoomMappings extends PropertyMap<Room, RoomDTO> {

    @Override
    protected void configure() {
        map().setCapacity(source.getCapacity());
        map().setRoomCode(source.getRoomCode());
        map().setWingCode(source.getWingCode());
        skip().setRoomFacilities(null);
        skip().setRoomReservations(null);
    }
}
