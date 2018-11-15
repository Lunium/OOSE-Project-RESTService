package nl.han.oose.dynamicroombackend.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthenticationHelperTest {


    @Test
    public void RandomPinGenerator() {
        AuthenticationHelper helper = new AuthenticationHelper();
        int g = helper.generatePinForReservation();

        assertTrue("Error, value is too small", g > 99999);
        assertTrue("Error, value is too large", g <= 1000000);
    }

}