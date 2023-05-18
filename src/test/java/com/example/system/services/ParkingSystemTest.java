//package com.example.system.services;
//
//import com.example.system.dto.RuleDTO;
//import com.example.system.helperclasses.MutableDouble;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ParkingSystemTest {
//
//
//    private LocalDateTime calulateRemainTimePerRule(LocalDateTime now, RuleDTO rule, MutableDouble remainMoney) {
//
//        if (rule.getCost() == 0) {
//            double dif = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
//            return roundToMinutes(now.plusNanos((long) dif));
//        } else {
//            double nanoDiff = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
//            remainMoney.setValue(remainMoney.getValue() - (nanoDiff / 3600000000000.0) * rule.getCost());
//            if (remainMoney.getValue() < 0) {
//                long nanoToRemove = (long) ((remainMoney.getValue() * 3600000000000.0) / rule.getCost());
//                remainMoney.setValue(0.0);
//                now = now.plusNanos((long) nanoDiff + nanoToRemove);
//                if (now.toLocalTime().equals(LocalTime.MAX)) {
//                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
//                }
//                return roundToMinutes(now);
//            } else {
//                now = now.plusNanos((long) nanoDiff);
//                if (now.toLocalTime().equals(LocalTime.MAX)) {
//                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
//                }
//                return roundToMinutes(now);
//            }
//        }
//
//    }
//
//    private boolean isBetween(LocalTime myTime, LocalTime start, LocalTime end) {
//        return (myTime.equals(start) || myTime.isAfter(start)) && (myTime.equals(end) || myTime.isBefore(end));
//    }
//
//    private boolean sameRangeTimes(LocalTime t1, LocalTime t2, LocalTime start, LocalTime end) {
//        return isBetween(t1, start, end) && isBetween(t2, start, end);
//    }
//
//    public LocalDateTime calExitTime(LocalDateTime now, MutableDouble money, List<RuleDTO> rules) {
//
//        List<RuleDTO> newRules = new ArrayList<>();
//        for (RuleDTO rule : rules) {
//            if (!(sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.MIN, LocalTime.NOON)
//                    || sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.NOON, LocalTime.MAX))) {
//
//                if (isBetween(rule.getStartTime(), LocalTime.MIN, LocalTime.NOON)) {
//                    newRules.add(new RuleDTO(rule.getCost(), rule.getStartTime(), LocalTime.NOON));
//                } else {
//                    newRules.add(new RuleDTO(rule.getCost(), rule.getStartTime(), LocalTime.MAX));
//                }
//
//                if (isBetween(rule.getEndTime(), LocalTime.MIN, LocalTime.NOON)) {
//                    newRules.add(new RuleDTO(rule.getCost(), LocalTime.MIN, rule.getEndTime()));
//                } else {
//                    newRules.add(new RuleDTO(rule.getCost(), LocalTime.NOON, rule.getEndTime()));
//                }
//
//            } else {
//                newRules.add(rule);
//            }
//        }
//
////        LocalDateTime now = LocalDateTime.now();
//        boolean remainMoney = true;
//        while (remainMoney) {
//            for (RuleDTO rule : newRules) {
//                if (!((now.toLocalTime().equals(rule.getStartTime()) || now.toLocalTime().isAfter(rule.getStartTime())) && (now.toLocalTime().equals(rule.getEndTime()) || now.toLocalTime().isBefore(rule.getEndTime())))) {
//                    System.out.printf("Time %s is not in this rule range.\n", now);
//                    continue;
//                }
//                if (money.getValue() == 0 && rule.getCost() != 0) {
//                    remainMoney = false;
//                    break;
//                }
//                now = calulateRemainTimePerRule(now, rule, money);
//
//            }
//        }
//
//        return now;
//    }
//
//    private LocalDateTime roundToMinutes(LocalDateTime time) {
//        if (time.getSecond() >= 30 && time.getMinute() == 59) {
//            time = time.plusMinutes(1);
//        }
//        return time.withSecond(0).withNano(0);
//    }
//
//
//    public static void main(String[] args) {
//
//        RuleDTO r1 = new RuleDTO();
//        r1.setCost(1);
//        r1.setId(1);
//        r1.setDetails("R1");
//        r1.setName("R1");
//        r1.setStartTime(LocalTime.of(8, 0));
//        r1.setEndTime(LocalTime.of(20, 0));
//
//        RuleDTO r2 = new RuleDTO();
//        r2.setCost(0.0);
//        r2.setId(1);
//        r2.setDetails("R1");
//        r2.setName("R1");
//        r2.setStartTime(LocalTime.of(20, 0));
//        r2.setEndTime(LocalTime.of(8, 0));
////
////        RuleDTO r3 =  new RuleDTO();
////        r3.setCost(1);
////        r3.setId(1);
////        r3.setDetails("R1");
////        r3.setName("R1");
////        r3.setStartTime(LocalTime.of(23,0));
////        r3.setEndTime(LocalTime.of(8, 0));
//
//
//        List<RuleDTO> rules = new ArrayList<RuleDTO>(Arrays.asList(r1, r2
//        ));
//
//
//        MutableDouble total_Money = new MutableDouble(0);
//
//
//        ParkingSystemTest test = new ParkingSystemTest();
//        LocalDateTime now = LocalDateTime.now().withHour(5);
//
//        LocalDateTime exittime = test.calExitTime(now, total_Money, rules);
//        System.out.println(exittime);
//    }
//}
