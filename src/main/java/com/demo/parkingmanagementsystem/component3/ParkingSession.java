package com.demo.parkingmanagementsystem.component3;

import java.time.LocalDateTime;

public class ParkingSession {
    // Encapsulation: private
    private String plate;
    private String pin;
    //automatic entry time
    private LocalDateTime entryTime;

    public ParkingSession(String plate, String pin) {
        this.plate = plate;
        this.pin = pin;
        this.entryTime = LocalDateTime.now();
    }

    // Abstraction,polymorphism
    @Override
    //complex data to simple format abstract
    public String toString() {
        
        return "Plate: " + plate + " | PIN: " + pin + " | Entered: " + entryTime;
    }
}
