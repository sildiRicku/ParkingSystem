package com.example.system.controllers;

import com.example.system.dto.HolidayDTO;
import com.example.system.entities.Holiday;
import com.example.system.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/new")
    public Holiday add(@RequestBody Holiday holiday) {
        return holidayService.addHoliday(holiday);
    }
}
