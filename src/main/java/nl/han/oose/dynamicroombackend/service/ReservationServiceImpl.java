package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.ReservationDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.id.ReservationId;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import nl.han.oose.dynamicroombackend.repository.RoomRepository;
import nl.han.oose.dynamicroombackend.util.AuthenticationHelper;
import nl.han.oose.dynamicroombackend.util.DateHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component("reservationService")
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private DateHelper dateHelper;
    private AuthenticationHelper authenticationHelper;
    private MailMessagingService mailMessagingService;
    private ModelMapper mapper;
    private RoomService roomService;


    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RoomRepository roomRepository,
                                  DateHelper dateHelper,
                                  AuthenticationHelper authenticationHelper,
                                  MailMessagingService mailMessagingService,
                                  ModelMapper mapper,
                                  RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.dateHelper = dateHelper;
        this.authenticationHelper = authenticationHelper;
        this.mailMessagingService = mailMessagingService;
        this.mapper = mapper;
        this.roomService = roomService;
    }


    public List<Reservation> findAllReservationsForRoomForToday(String roomCode, String wingCode) {
        return this.reservationRepository.findByRoomCodeAndWingCode(roomCode, wingCode);
    }

    @Transactional
    public List<RoomDTO> findAllReservationForDay() {
        List<Reservation> reservations = reservationRepository.findAllOfToday();
        RoomDTO[] rooms = mapper.map(roomRepository.findAll(), RoomDTO[].class);
        roomService.addReservationsToRoomDto(rooms, reservations);
        return Arrays.asList(rooms);
    }

    @Transactional
    @Override
    public boolean addReservation(Reservation reservation) {
        try {
            if (reservation == null) {
                throw new RuntimeException("No value in request");
            }

            ReservationId id = mapper.map(reservation, ReservationId.class);
            if (reservationRepository.exists(id)) {
                throw new RuntimeException("Already exists or is not valid");
            }
            reservation.setPinCode(authenticationHelper.generatePinForReservation());
            Reservation r = reservationRepository.saveAndFlush(reservation);
            mailMessagingService.sendReservationConfirmationMail(r);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Reservation> findAllReservationOfWeek(int week, int year, String wingCode, String roomCode) {
        try {
            Date firstDayOfTheWeek = dateHelper.localDateToUtilDate(dateHelper.getFirstDayOfWeek(week, year));
            Date lastDayOfTheWeek = dateHelper.localDateToUtilDate(dateHelper.getLastDayOfWeek(week, year));
            return reservationRepository
                    .findAllOfWeek(
                            roomCode,
                            wingCode,
                            firstDayOfTheWeek,
                            lastDayOfTheWeek);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean cancelReservation(ReservationDTO dto) {
        if (dto == null) {
            return false;
        }
        ReservationId id = mapper.map(dto, ReservationId.class);
        Reservation r = reservationRepository.findOne(id);
        if (r != null && r.getStatus().equals(Reservation.STATUS_BOOKED)) {
            r.setStatus(Reservation.STATUS_CANCELLED);
            return true;
        }
        return false;
    }

    @Override
    public List<Reservation> getAllActualReservationByUsername(String username) {
        return reservationRepository.findActualReservationByUsername(username);
    }
}
