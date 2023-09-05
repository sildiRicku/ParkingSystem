package com.example.system.factory;

import com.example.system.interfaces.IParkingSystemService;
import com.example.system.serviceimplementations.Calculations;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import com.example.system.serviceimplementations.TimeUtils;
import com.example.system.serviceimplementations.Validations;

public class ParkingSystemServiceFactory {
    private final Calculations calculations;
    private final TimeUtils timeUtils;
    private final Validations validations;

    public ParkingSystemServiceFactory(Calculations calculations, TimeUtils timeUtils, Validations validations) {
        this.calculations = calculations;
        this.timeUtils = timeUtils;
        this.validations = validations;
    }

    public static IParkingSystemService createParkingSystemService() {
        return new ParkingSystemServiceImpl();
    }
}
