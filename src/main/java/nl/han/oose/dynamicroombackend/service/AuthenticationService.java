package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.dto.CheckInOutDTO;

public interface AuthenticationService {
    boolean checkIn(CheckInOutDTO request);
    boolean checkOut(CheckInOutDTO request);
}
