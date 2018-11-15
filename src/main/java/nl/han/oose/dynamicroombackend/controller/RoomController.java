package nl.han.oose.dynamicroombackend.controller;

import nl.han.oose.dynamicroombackend.model.Facility;
import nl.han.oose.dynamicroombackend.model.Room;
import nl.han.oose.dynamicroombackend.model.Wing;
import nl.han.oose.dynamicroombackend.model.id.RoomId;
import nl.han.oose.dynamicroombackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
public class RoomController {
    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/facilities")
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> result = roomService.findAllFacilities();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/wings", method = RequestMethod.GET)
    public ResponseEntity<List<Wing>> getAllWings(){
        List<Wing> wings = roomService.findAllWings();
        return new ResponseEntity<>(wings, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Room> getRoomInformation(@ModelAttribute RoomId id){
        return new ResponseEntity<>(roomService.findRoom(id),
                HttpStatus.OK);
    }
}
