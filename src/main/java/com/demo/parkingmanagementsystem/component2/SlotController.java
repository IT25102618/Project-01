package com.demo.parkingmanagementsystem.component2;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private static List<ParkingSlot> slots = new ArrayList<>(Arrays.asList(
            new ParkingSlot("Slot-01"), new ParkingSlot("Slot-02"),
            new ParkingSlot("Slot-03"), new ParkingSlot("Slot-04"),
            new ParkingSlot("Slot-05"), new ParkingSlot("Slot-06")
    ));

    @GetMapping("/view-all")
    public List<ParkingSlot> viewSlots() {
        return slots;
    }

    public static String getOtpForPlate(String plate) {
        for (ParkingSlot slot : slots) {
            if (slot.getPlate() != null && slot.getPlate().equalsIgnoreCase(plate)) {
                return slot.getAssignedOtp() != null ? slot.getAssignedOtp() : "N/A";
            }
        }
        return "N/A";
    }

    @GetMapping("/confirm-booking")
    public String confirm(@RequestParam String slotId, @RequestParam String plate) {
        // Find slot
        for (ParkingSlot slot : slots) {
            if (slot.getSlotId().equalsIgnoreCase(slotId) && !slot.isOccupied()) {
                slot.setOccupied(true);
                slot.setPlate(plate);

                // Generate 4-digit OTP
                String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
                slot.setAssignedOtp(otp);

                return "SUCCESS:" + otp;
            }
        }
        return "ERROR: Slot unavailable";
    }

    @GetMapping("/gate-control")
    public Map<String, String> gateControl(@RequestParam String otp, @RequestParam String action) {
        Map<String, String> response = new HashMap<>();
        for (ParkingSlot slot : slots) {
            if (slot.getAssignedOtp() != null && slot.getAssignedOtp().equals(otp)) {
                if (action.equals("entry")) {
                    slot.setStartTime(LocalDateTime.now());
                    // Set Expiry: 1 Hour 30 Minutes from now
                    slot.setExpiryTime(LocalDateTime.now().plusMinutes(90));
                    response.put("status", "ENTRY_SUCCESS");
                    response.put("slotId", slot.getSlotId());
                    response.put("expiry", slot.getExpiryTime().toString());
                    return response;
                }
                if (action.equals("exit")) {
                    double finalFare = calculateFare(slot.getStartTime());
                    // Reset slot
                    slot.setOccupied(false);
                    slot.setAssignedOtp(null);
                    slot.setPlate(null);
                    response.put("status", "EXIT_SUCCESS");
                    response.put("fare", String.format("%.2f", finalFare));
                    return response;
                }
            }
        }
        response.put("status", "INVALID_OTP");
        return response;
    }

    private double calculateFare(LocalDateTime start) {
        if (start == null) return 200.0;
        long mins = Duration.between(start, LocalDateTime.now()).toMinutes();
        return 200.0 + (mins * 5.0); // Base 200 + 5 LKR per minute
    }
}