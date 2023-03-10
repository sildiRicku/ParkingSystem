package com.example.system.services;

import com.example.system.dto.HolidayDTO;
import com.example.system.entities.Holiday;
import com.example.system.repositories.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepo holidayRepo;

    @Autowired

    public HolidayService(HolidayRepo holidayRepo) {
        this.holidayRepo = holidayRepo;
    }

    public List<HolidayDTO> getAllHolidayDTOs() {
        List<Holiday> holidays = holidayRepo.findAll();
        List<HolidayDTO> holidayDTOS = new ArrayList<>();
        for (Holiday holiday : holidays) {
            HolidayDTO holidayDTO = new HolidayDTO();
            holidayDTO.setHolidayId(holiday.getHolidayId());
            holidayDTO.setHolidayDate(holiday.getHolidayDate());
            holidayDTO.setHolidayName(holiday.getHolidayName());
            holidayDTOS.add(holidayDTO);

        }
        return holidayDTOS;
    }

    public Optional<HolidayDTO> getHolidayById(int id) {
        Optional<Holiday> holiday = holidayRepo.findById(id);
        if (holiday.isPresent()) {
            HolidayDTO holidayDTO = new HolidayDTO();
            holidayDTO.setHolidayDate(holiday.get().getHolidayDate());
            holidayDTO.setHolidayName(holiday.get().getHolidayName());
            holidayDTO.setHolidayId(holiday.get().getHolidayId());
            return Optional.of(holidayDTO);
        } else {
            return Optional.empty();
        }
    }

    public Holiday addHoliday(HolidayDTO holidayDTO) {
        Holiday holiday = new Holiday();
        holiday.setHolidayDate(holidayDTO.getHolidayDate());
        holiday.setHolidayName(holidayDTO.getHolidayName());
        holiday.setHolidayId(holidayDTO.getHolidayId());
        return holidayRepo.save(holiday);
    }

}
