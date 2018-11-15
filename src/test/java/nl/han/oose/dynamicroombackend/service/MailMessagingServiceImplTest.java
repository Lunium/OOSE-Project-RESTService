package nl.han.oose.dynamicroombackend.service;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import nl.han.oose.dynamicroombackend.model.Reservation;
import nl.han.oose.dynamicroombackend.util.AuthenticationHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MailMessagingServiceImplTest {

    @InjectMocks
    MailMessagingServiceImpl testService;

    @Mock
    JavaMailSender mailSender;

    @Mock
    AuthenticationHelper authenticationHelper;

    @Test(expected = RuntimeException.class)
    public void sendReservationConfirmationMailRuntimeException() throws Exception {
        MimeMessage message = mock(MimeMessage.class);
        testService.sendReservationConfirmationMail(new Reservation());
    }

    @Test
    public void sendReservationConfirmationMail() throws Exception {
        int pincode = 123456;
        Reservation r = new Reservation();
        r.setPinCode(pincode);

        MimeMessage message = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(message);
        when(authenticationHelper.generateQRCodeFromPin(pincode)).thenReturn(new ByteArrayOutputStream());

        testService.sendReservationConfirmationMail(r);

        verify(mailSender).createMimeMessage();
        verify(authenticationHelper).generateQRCodeFromPin(pincode);
    }

}