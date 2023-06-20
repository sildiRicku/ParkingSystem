package com.example.system.factory;

import com.example.system.interfaces.IParkingSystemService;
import com.example.system.serviceimplementations.Calculations;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import com.example.system.serviceimplementations.TimeUtils;
import com.example.system.serviceimplementations.Validations;

public class ParkingSystemServiceFactory {
    public static IParkingSystemService createParkingSystemService() {
        return new ParkingSystemServiceImpl(new Calculations(new TimeUtils()),new Validations());
    }
}
