package com.example.system.interfaces;

import com.example.system.helperclasses.MutableDouble;
import com.example.system.models.Rule;

import java.time.LocalDateTime;
import java.util.List;

public interface IParkingSystemService {
    LocalDateTime calculateExitTime(LocalDateTime now, MutableDouble money, List<Rule> rules);
}
