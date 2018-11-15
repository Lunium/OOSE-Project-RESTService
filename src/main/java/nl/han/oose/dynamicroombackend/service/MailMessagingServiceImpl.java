package nl.han.oose.dynamicroombackend.service;

import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.util.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;


@Service
public class MailMessagingServiceImpl implements MailMessagingService {
    private JavaMailSender mailSender;
    private AuthenticationHelper authenticationHelper;
    private static final String EMAIL_TO = "hogeschool.dynr@gmail.com";


    @Autowired
    public MailMessagingServiceImpl(JavaMailSender mailSender, AuthenticationHelper authenticationHelper) {
        this.mailSender = mailSender;
        this.authenticationHelper = authenticationHelper;
    }


    @Override
    @Async
    public void sendReservationConfirmationMail(Reservation reservation) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            DataSource attachment = new ByteArrayDataSource(authenticationHelper
                    .generateQRCodeFromPin(reservation.getPinCode()).toByteArray(), "application/octet-stream");
            helper.addAttachment("QR.png", attachment);
            helper.addTo(EMAIL_TO);
            helper.setSubject("Bevestiging reservering ruimte: " + reservation.getRoomCode()
                    + " " + reservation.getWingCode());
            helper.setText("Pincode: " + reservation.getPinCode() + "\n" +
                    "Datum: " + reservation.getDate() + "\n" +
                    "Starttijd: " + reservation.getBeginTime() + "\n" +
                    "Eindtijd: " + reservation.getEndTime() + "\n");
            mailSender.send(message);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Mail sending failed");
        }
    }
}
