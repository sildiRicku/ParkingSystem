package com.example.system.controllers;

import com.example.system.dto.HolidayDTO;
import com.example.system.entities.Holiday;
import com.example.system.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
    private final HolidayService holidayService;


    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/all")
    public List<HolidayDTO> getAllHolidayDTOs() {
        return holidayService.getAllHolidayDTOs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HolidayDTO getHolidaysById(@PathVariable int id) {
        Optional<HolidayDTO> holidayDTO = holidayService.getHolidayById(id);
        if (holidayDTO.isPresent()) {
            return holidayDTO.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public Holiday add(@RequestBody HolidayDTO holidayDTO) {
        return holidayService.addHoliday(holidayDTO);
    }
}
