package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.model.Reservation;

public interface MailMessagingService {
    void sendReservationConfirmationMail(Reservation reservation);
}
