package com.example.system.repositories;

import com.example.system.models.ParkingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ParkingSystemRepoTest {
    @Mock
    private ParkingSystemRepo parkingSystemRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllWithSort() {
        List<ParkingSystem> parkingSystems = new ArrayList<>();
        parkingSystems.add(new ParkingSystem());
        parkingSystems.add(new ParkingSystem());

        Sort sort = Sort.by(Sort.Direction.ASC, "someProperty");

        when(parkingSystemRepo.findAll(sort)).thenReturn(parkingSystems);

        List<ParkingSystem> result = parkingSystemRepo.findAll(sort);

        assertEquals(parkingSystems.size(), result.size());
        assertEquals(parkingSystems, result);
    }
}