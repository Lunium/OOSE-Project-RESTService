package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.dto.CheckInOutDTO;
import nl.han.oose.dynamicroombackend.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/authentication")
@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkIn(@RequestBody CheckInOutDTO request) {
        if (authenticationService.checkIn(request))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<String> checkOut(@RequestBody CheckInOutDTO request){
        if(authenticationService.checkOut(request))
           return new ResponseEntity<>("Reservering is uitgecheckt", HttpStatus.OK);
        return new ResponseEntity<>("Reservering kan niet worden uitgecheckt", HttpStatus.BAD_REQUEST);
    }
}
