package com.example.system.services;

import com.example.system.dto.HolidayDTO;
import com.example.system.models.Holiday;
import com.example.system.repositories.HolidayRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    @Autowired
    private final HolidayRepo holidayRepo;
    @Autowired
    private final ModelMapper modelMapper;

    public HolidayService(HolidayRepo holidayRepo, ModelMapper modelMapper) {
        this.holidayRepo = holidayRepo;
        this.modelMapper = modelMapper;
    }

    public List<HolidayDTO> getAllHolidays() {
        List<Holiday> holidays = holidayRepo.findAll();
        List<HolidayDTO> holidayDTOS = new ArrayList<>();
        for (Holiday holiday : holidays) {
            HolidayDTO holidayDTO = modelMapper.map(holiday, HolidayDTO.class);
            holidayDTOS.add(holidayDTO);
        }
        return holidayDTOS;
    }

    public Optional<HolidayDTO> getHolidayById(int id) {
        Optional<Holiday> holiday = holidayRepo.findById(id);
        if (holiday.isPresent()) {
            HolidayDTO holidayDTO = modelMapper.map(holiday.get(), HolidayDTO.class);
            return Optional.of(holidayDTO);
        } else {
            return Optional.empty();
        }
    }

    public HolidayDTO addHoliday(HolidayDTO holidayDTO) {
        Holiday holiday = modelMapper.map(holidayDTO, Holiday.class);
        holiday = holidayRepo.save(holiday);
        holidayDTO = modelMapper.map(holiday, HolidayDTO.class);
        return holidayDTO;

    }
}
