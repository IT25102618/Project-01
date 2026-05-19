package com.demo.parkingmanagementsystem.component3;

import java.time.LocalDateTime;
import java.time.Duration;

public abstract class ParkingSession {
    protected String plate;
    protected String pin;
    protected LocalDateTime entryTime;

    public ParkingSession(String plate, String pin) {
        this.plate = plate;
        this.pin = pin;
        this.entryTime = LocalDateTime.now();
    }

    public abstract double calculateFee(LocalDateTime exitTime);

    public String toFileFormat() {
        String type = (this instanceof MonthlySession) ? "MONTHLY" : "HOURLY";
        return plate + "," + pin + "," + entryTime.toString() + "," + type;
    }
}

class HourlySession extends ParkingSession {
    public HourlySession(String plate, String pin) { super(plate, pin); }
    @Override
    public double calculateFee(LocalDateTime exitTime) {
        long mins = Duration.between(this.entryTime, exitTime).toMinutes();
        return (mins < 1 ? 1 : mins) * 10.0;
    }
}

class MonthlySession extends ParkingSession {
    public MonthlySession(String plate, String pin) { super(plate, pin); }
    @Override
    public double calculateFee(LocalDateTime exitTime) {
        long mins = Duration.between(this.entryTime, exitTime).toMinutes();
        return (mins < 1 ? 1 : mins) * 3.0;
    }
}
