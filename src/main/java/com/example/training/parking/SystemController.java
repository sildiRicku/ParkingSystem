/*
package com.example.training.parking;

import com.example.training.rule.Rule;
import com.example.training.transaction.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/system")
public class SystemController {
    Transaction t1 = new Transaction(2690);
    Transaction t2 = new Transaction(25633);
    Transaction t3 = new Transaction(12);
    Rule r1 = new Rule("5");
    Rule r2 = new Rule("2");
    ParkingSystem p1 = new ParkingSystem("12312312", 124912939, "1.5.132", r1, t1);
    ParkingSystem p2 = new ParkingSystem("15463442", 6455345, "4.5.132,", r2, t2);
    ParkingSystem p3 = new ParkingSystem("134534", 45534534, "2.5.123", r2, t3);

    @GetMapping("/systems")
    public List<ParkingSystem> getParkingSystem() {

        return List.of(p1, p2, p3);
    }

    @GetMapping("/rules")
    public List<ParkingSystem> hasRuleR2() {
        List<ParkingSystem> list = List.of(p1, p2, p3);
        List<ParkingSystem> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRule().equals(r2)) {
                result.add(list.get(i));
            }
        }
        return result;
    }


    @GetMapping("/transactions")
    public String showTransaction() {
        return "The transaction value of Parking System 1 is: " + p1.getTransaction().getValue();
    }

    @PostMapping("/parking-systems")
    public ParkingSystem createParkingSystem(@RequestBody InputClass myClass) {
        return new ParkingSystem(myClass.getId(), myClass.getTotalMoney(), "1.2", new Rule("4"), new Transaction(22));

    }

}
*/
