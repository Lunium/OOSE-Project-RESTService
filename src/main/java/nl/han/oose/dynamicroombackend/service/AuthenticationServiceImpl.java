package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.CheckInOutDTO;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public AuthenticationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public boolean checkIn(CheckInOutDTO request) {
        Reservation r  = reservationRepository.checkInReservation(request.getRoomCode(),
                request.getWingCode(),
                request.getPinCode());
        if(r != null){
            r.setStatus(Reservation.STATUS_ACTIVE);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean checkOut(CheckInOutDTO request) {
        Reservation r = reservationRepository.checkOutReservation(request.getRoomCode(), request.getWingCode());
        if(r != null){
            r.setStatus(Reservation.STATUS_ENDED);
            return true;
        }
        return false;
    }
}
