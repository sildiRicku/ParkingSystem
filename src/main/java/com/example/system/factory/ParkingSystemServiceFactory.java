package com.example.system.factory;

import com.example.system.interfaces.ParkingSystemServicee;
import com.example.system.serviceimplementations.parkingsystem.Calculations;
import com.example.system.serviceimplementations.parkingsystem.ParkingSystemServiceImpl;
import com.example.system.serviceimplementations.parkingsystem.TimeUtils;
import com.example.system.serviceimplementations.parkingsystem.Validations;

public class ParkingSystemServiceFactory {
    public static ParkingSystemServicee createParkingSystemService() {
        return new ParkingSystemServiceImpl(new Calculations(new TimeUtils()),new Validations());
    }
}
