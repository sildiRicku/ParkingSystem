package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.entities.Rule;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.repositories.ParkingSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSystemService {
    private final ParkingSystemRepo parkingSystemRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ParkingSystemService(ParkingSystemRepo parkingSystemRepo, ModelMapper modelMapper) {
        this.parkingSystemRepo = parkingSystemRepo;
        this.modelMapper = modelMapper;
    }


    public List<ParkingSystemDTO> getAllParkingSystems() {
        List<ParkingSystem> parkingSystems = parkingSystemRepo.findAll();
        List<ParkingSystemDTO> parkingSystemDTOS = new ArrayList<>();
        for (ParkingSystem parkingSystem : parkingSystems) {
            ParkingSystemDTO parkingSystemDTO = modelMapper.map(parkingSystem, ParkingSystemDTO.class);
            parkingSystemDTOS.add(parkingSystemDTO);
        }
        return parkingSystemDTOS;
    }

    public Optional<ParkingSystemDTO> getParkingSystemById(int id) {
        Optional<ParkingSystem> parkingSystem = parkingSystemRepo.findById(id);
        if (parkingSystem.isPresent()) {
            ParkingSystemDTO parkingSystemDTO = modelMapper.map(parkingSystem.get(), ParkingSystemDTO.class);
            return Optional.of(parkingSystemDTO);
        } else {
            return Optional.empty();
        }
    }


    public ParkingSystemDTO addParkingSystem(ParkingSystemDTO parkingSystemDTO) {
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        parkingSystemRepo.save(parkingSystem);
        parkingSystemDTO = modelMapper.map(parkingSystem, ParkingSystemDTO.class);
        return parkingSystemDTO;
    }

    private LocalDateTime calulateRemainTimePerRule(LocalDateTime now, Rule rule, MutableDouble remainMoney) {

        if (rule.getCost() == 0) {
            double dif = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
            return roundToMinutes(now.plusNanos((long) dif));
        } else {
            double nanoDiff = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
            remainMoney.setValue(remainMoney.getValue() - (nanoDiff / 3600000000000.0) * rule.getCost());
            if (remainMoney.getValue() < 0) {
                long nanoToRemove = (long) ((remainMoney.getValue() * 3600000000000.0) / rule.getCost());
                remainMoney.setValue(0.0);
                now = now.plusNanos((long) nanoDiff + nanoToRemove);
                if (now.toLocalTime().equals(LocalTime.MAX)) {
                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
                }
                return roundToMinutes(now);
            } else {
                now = now.plusNanos((long) nanoDiff);
                if (now.toLocalTime().equals(LocalTime.MAX)) {
                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
                }
                return roundToMinutes(now);
            }
        }

    }

    private boolean isBetween(LocalTime myTime, LocalTime start, LocalTime end) {
        return (myTime.equals(start) || myTime.isAfter(start)) && (myTime.equals(end) || myTime.isBefore(end));
    }

    private boolean sameRangeTimes(LocalTime t1, LocalTime t2, LocalTime start, LocalTime end) {
        return isBetween(t1, start, end) && isBetween(t2, start, end);
    }

    public LocalDateTime calExitTime(LocalDateTime now, MutableDouble money, List<Rule> rules) {

        List<Rule> newRules = new ArrayList<>();
        for (Rule rule : rules) {
            if (!(sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.MIN, LocalTime.NOON)
                    || sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.NOON, LocalTime.MAX))) {

                if (isBetween(rule.getStartTime(), LocalTime.MIN, LocalTime.NOON)) {
                    newRules.add(new Rule(rule.getCost(), rule.getStartTime(), LocalTime.NOON));
                } else {
                    newRules.add(new Rule(rule.getCost(), rule.getStartTime(), LocalTime.MAX));
                }

                if (isBetween(rule.getEndTime(), LocalTime.MIN, LocalTime.NOON)) {
                    newRules.add(new Rule(rule.getCost(), LocalTime.MIN, rule.getEndTime()));
                } else {
                    newRules.add(new Rule(rule.getCost(), LocalTime.NOON, rule.getEndTime()));
                }

            } else {
                newRules.add(rule);
            }
        }

        boolean remainMoney = true;
        while (remainMoney) {
            for (Rule rule : newRules) {
                if (!((now.toLocalTime().equals(rule.getStartTime()) || now.toLocalTime().isAfter(rule.getStartTime())) && (now.toLocalTime().equals(rule.getEndTime()) || now.toLocalTime().isBefore(rule.getEndTime())))) {
                    continue;
                }
                if (money.getValue() == 0 && rule.getCost() != 0) {
                    remainMoney = false;
                    break;
                }
                now = calulateRemainTimePerRule(now, rule, money);

            }
        }

        return now;
    }

    private LocalDateTime roundToMinutes(LocalDateTime time) {
        if (time.getSecond() >= 30 && time.getMinute() == 59) {
            time = time.plusMinutes(1);
        }
        return time.withSecond(0).withNano(0);
    }


    public LocalDateTime getExitTime(ParkingSystemDTO parkingSystemDTO, MutableDouble money) {
        List<Rule> rules = parkingSystemDTO.getRules();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime exittime = calExitTime(now, money, rules);
        return exittime;
    }
}
