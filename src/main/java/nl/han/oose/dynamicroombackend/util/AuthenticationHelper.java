package nl.han.oose.dynamicroombackend.util;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
@Component
public class AuthenticationHelper {

    public int generatePinForReservation(){
        SecureRandom random = new SecureRandom();
        return 100000 + random.nextInt(900000);
    }

    public ByteArrayOutputStream generateQRCodeFromPin(Integer pinCode){
       return QRCode.from(pinCode.toString()).to(ImageType.PNG).withSize(250, 250).stream();
    }
}
