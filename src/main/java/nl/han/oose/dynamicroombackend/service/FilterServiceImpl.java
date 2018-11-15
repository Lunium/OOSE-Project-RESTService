package nl.han.oose.dynamicroombackend.service;


import nl.han.oose.dynamicroombackend.config.RoomMappings;
import nl.han.oose.dynamicroombackend.dto.FilterDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.exception.WrongParametersException;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    RoomRepository roomRepository;

    RoomService roomService;

    ReservationRepository reservationRepository;

    ModelMapper mapper;

    @Autowired
    public FilterServiceImpl(RoomRepository roomRepository, RoomService roomService, ReservationRepository reservationRepository, ModelMapper mapper) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoomDTO> applyFilter(FilterDTO request) {
        try {
            List<Room> rooms = null;
            if(request == null){
                throw new RuntimeException();
            }
            if ((request.getFacilities() == null || request.getFacilities().isEmpty()) &&
                    (request.getWingCode() == null || request.getWingCode().isEmpty())) {
                rooms = roomRepository.filterRoomsNoExtraParams(request.getBeginTime(),
                        request.getEndTime(), request.getDate(), request.getCapacity());
                System.out.println("FILTER NOTHING EXTRA");

            } else if (request.getFacilities() != null && request.getWingCode() != null) {
               rooms = roomRepository.filterRoomsFacilitiesAndWing(request.getBeginTime(), request.getEndTime(),
                        request.getDate(), request.getCapacity(), request.getFacilities(), request.getFacilities().size(), request.getWingCode());
                System.out.println("FILTER ALL");

            } else if (request.getFacilities() != null) {
               rooms = roomRepository.filterRoomsFacilities(request.getBeginTime(), request.getEndTime(),
                        request.getDate(), request.getCapacity(), request.getFacilities().size() ,request.getFacilities());
                System.out.println("FILTER FACILITIES");

            } else if (request.getWingCode() != null) {
               rooms = roomRepository.filterRoomsWing(request.getBeginTime(), request.getEndTime(),
                        request.getDate(), request.getCapacity(),request.getWingCode());
                System.out.println("FILTER WING");
            }
            assert rooms != null;

            RoomDTO[] result = mapper.map(rooms, RoomDTO[].class);
            List<Reservation> reservations = reservationRepository.findAllForDate(request.getDate());
            roomService.addReservationsToRoomDto(result, reservations);
            return Arrays.asList(result);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new WrongParametersException("Parameters incorrect, maybe wrong format");
        }
    }
}
