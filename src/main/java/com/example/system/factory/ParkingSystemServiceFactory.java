package com.example.system.factory;

import com.example.system.interfaces.IParkingSystemService;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;


public class ParkingSystemServiceFactory {
    private ParkingSystemServiceFactory() {
    }

    public static IParkingSystemService createParkingSystemService() {
        return new ParkingSystemServiceImpl();
    }
}
