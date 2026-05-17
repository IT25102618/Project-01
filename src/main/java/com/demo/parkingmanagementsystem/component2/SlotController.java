package com.demo.parkingmanagementsystem.component2;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/slots")
public class SlotController {

    //Create 6 parking slots at the beginning
    private static List<ParkingSlot> slots = new ArrayList<>(Arrays.asList(
            new ParkingSlot("Slot-01"), new ParkingSlot("Slot-02"),
            new ParkingSlot("Slot-03"), new ParkingSlot("Slot-04"),
            new ParkingSlot("Slot-05"), new ParkingSlot("Slot-06")
    ));

    //View all slots
    @GetMapping("/view-all")
    //Returns all parking slots
    public List<ParkingSlot> viewSlots() {
        return slots;
    }

    //Book a slot
    
    @GetMapping("/confirm-booking")
    public String confirm(@RequestParam String slotId, @RequestParam String plate) {
        // Find slot,Check everyslot one by one
        for (ParkingSlot slot : slots) {

            //checking correct slot and slot is not occupied
            if (slot.getSlotId().equalsIgnoreCase(slotId) && !slot.isOccupied()) {
                //mark slot as occupied
                slot.setOccupied(true);
                
                //Save vehicle number
                slot.setPlate(plate);

                // Generate 4-digit OTP
                String otp = String.valueOf((int)(Math.random() * 9000) + 1000);

                //save OTP to slot
                slot.setAssignedOtp(otp);

                //return success message with OTP
                return "SUCCESS:" + otp;
            }
        }

        //if slot is already taken
        return "ERROR: Slot unavailable";
    }

    //Entry and exit gate control

    @GetMapping("/gate-control")
    public Map<String, String> gateControl(@RequestParam String otp, @RequestParam String action) {

        //Store responce data
        Map<String, String> response = new HashMap<>();

        //Check all slots
        for (ParkingSlot slot : slots) {
            //check whether OTP matches
            if (slot.getAssignedOtp() != null && slot.getAssignedOtp().equals(otp)) {

                //ENTRY----------
                
                if (action.equals("entry")) {

                    //save vehicle entry time
                    slot.setStartTime(LocalDateTime.now());
                    
                    // Set Expiry: 1 Hour 30 Minutes from now
                    slot.setExpiryTime(LocalDateTime.now().plusMinutes(90));

                    //Send success responce
                    response.put("status", "ENTRY_SUCCESS");
                    
                    //Send slot number
                    response.put("slotId", slot.getSlotId());

                    //Send expiry time
                    response.put("expiry", slot.getExpiryTime().toString());
                    return response;
                }

                //EXIT-------------
                
                if (action.equals("exit")) {

                    //calculate parking management
                    double finalFare = calculateFare(slot.getStartTime());
                    
                    // Reset slot details
                    slot.setOccupied(false);
                    slot.setAssignedOtp(null);
                    slot.setPlate(null);

                    //send exit success
                    response.put("status", "EXIT_SUCCESS");

                    //send fare amount
                    response.put("fare", String.format("%.2f", finalFare));
                    return response;
                }
            }
        }
        //if OTP not found
        response.put("status", "INVALID_OTP");
        return response;
    }

    //FARE CALCULATION

    
    //Calculates parking fee
    private double calculateFare(LocalDateTime start) {

        //if no start time found
        if (start == null) return 200.0;

        //calculate parking duration in minutes
        long mins = Duration.between(start, LocalDateTime.now()).toMinutes();

        //Base fee=200
        //Extra=5 per minute
        return 200.0 + (mins * 5.0); // Base 200 + 5 LKR per minute
    }
}
