package com.example.system.services;

import com.example.system.entities.Holiday;
import com.example.system.repositories.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepo holidayRepo;

    @Autowired

    public HolidayService(HolidayRepo holidayRepo) {
        this.holidayRepo = holidayRepo;
    }

    public List<Holiday> getAllHolidays() {
        return holidayRepo.findAll();
    }

    public Optional<Holiday> getHolidayById(int id) {
        return holidayRepo.findById(id);
    }

    public Holiday addHoliday(Holiday holiday) {
        return holidayRepo.save(holiday);
    }

}
