package nl.han.oose.dynamicroombackend.controller;


import nl.han.oose.dynamicroombackend.dto.FilterDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;
import nl.han.oose.dynamicroombackend.exception.WrongParametersException;
import nl.han.oose.dynamicroombackend.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/filter")
@RestController
public class FilterController {

    FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity<List<RoomDTO>>  applyFiltersTestNoResults(@RequestBody FilterDTO request){
        List<RoomDTO> results = filterService.applyFilter(request);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @ExceptionHandler({WrongParametersException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity applyFiltersExceptionHandler(WrongParametersException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
