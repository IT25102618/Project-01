package com.demo.parkingmanagementsystem.component3;

import java.time.LocalDateTime;

public class ParkingSession {
    // Encapsulation: private variables
    private String plate;
    private String pin;
    private LocalDateTime entryTime;

    public ParkingSession(String plate, String pin) {
        this.plate = plate;
        this.pin = pin;
        this.entryTime = LocalDateTime.now();
    }

    // Abstraction: This method handles the logic of formatting [cite: 88]
    @Override
    public String toString() {
        return "Plate: " + plate + " | PIN: " + pin + " | Entered: " + entryTime;
    }
}