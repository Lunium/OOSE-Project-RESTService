package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.Wing;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import nl.han.oose.dynamicroombackend.repository.FacilityRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import nl.han.oose.dynamicroombackend.repository.WingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    FacilityRepository facilityRepository;
    WingRepository wingRepository;
    ModelMapper mapper;

    RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(FacilityRepository facilityRepository, WingRepository wingRepository, ModelMapper mapper, RoomRepository roomRepository) {
        this.facilityRepository = facilityRepository;
        this.wingRepository = wingRepository;
        this.mapper = mapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Facility> findAllFacilities() {
        return (List<Facility>) facilityRepository.findAll();
    }

    @Override
    public List<Wing> findAllWings() {
        return (List<Wing>) wingRepository.findAll();
    }

    @Override
    public Room findRoom(RoomId id) {
        return roomRepository.findOne(id);
    }

    @Override
    public void addReservationsToRoomDto(RoomDTO[] rooms, List<Reservation> reservations) {
        for (RoomDTO room : rooms) {
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation r : reservations) {
                if (room.getRoomCode().equals(r.getRoomCode()) &&
                        room.getWingCode().equals(r.getWingCode())) {
                    reservationDTOS.add(mapper.map(r, ReservationDTO.class));
                }
            }
            room.setRoomReservations(reservationDTOS);
        }
    }
}