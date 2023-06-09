package com.example.system.serviceimplementations;



import java.time.LocalTime;
public class Validations {
    boolean isBetween(LocalTime myTime, LocalTime start, LocalTime end) {
        return (myTime.equals(start) || myTime.isAfter(start)) && (myTime.equals(end) || myTime.isBefore(end));
    }

    boolean sameRangeTimes(LocalTime t1, LocalTime t2, LocalTime start, LocalTime end) {
        return isBetween(t1, start, end) && isBetween(t2, start, end);
    }
}
