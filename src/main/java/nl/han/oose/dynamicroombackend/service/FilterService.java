package nl.han.oose.dynamicroombackend.service;


import nl.han.oose.dynamicroombackend.dto.FilterDTO;
import nl.han.oose.dynamicroombackend.dto.RoomDTO;

import java.util.List;

public interface FilterService {

    List<RoomDTO> applyFilter(FilterDTO request);
}
