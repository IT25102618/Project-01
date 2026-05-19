package com.demo.parkingmanagementsystem.component2;

import java.time.LocalDateTime;

public class ParkingSlot {
    private String slotId;
    private boolean isOccupied;
    private String plate;
    private String assignedOtp;
    private LocalDateTime startTime;
    private LocalDateTime expiryTime;

    // Default constructor (Needed for JSON conversion)
    public ParkingSlot() {}

    public ParkingSlot(String id) {
        this.slotId = id;
        this.isOccupied = false;
    }

    // Standard Getters and Setters
    public String getSlotId() { return slotId; }
    public void setSlotId(String slotId) { this.slotId = slotId; }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }

    public String getAssignedOtp() { return assignedOtp; }
    public void setAssignedOtp(String assignedOtp) { this.assignedOtp = assignedOtp; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}