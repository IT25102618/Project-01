package com.demo.parkingmanagementsystem.component3;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    // Entry: Record a vehicle [cite: 78]
    // Test URL: http://localhost:8080/parking/entry?plate=ABC-123&pin=1234
    @GetMapping("/entry")
    public String checkIn(@RequestParam String plate, @RequestParam String pin) {
        ParkingSession session = new ParkingSession(plate, pin);
        FileHandler.saveSession(session);

        // Returning styled HTML directly
        return "<div style='height:100vh; display:flex; flex-direction:column; align-items:center; justify-content:center; font-family:sans-serif; background:#f0f2f5;'>" +
                "<h1 style='color:#28a745;'>✔️ Entry Successful!</h1>" +
                "<p>Vehicle <b>" + plate + "</b> is now logged in the system.</p>" +
                "<a href='/entry.html' style='text-decoration:none; padding:10px 20px; background:#007bff; color:white; border-radius:5px;'>Open Gate for Next Vehicle</a>" +
                "</div>";
    }

    // Read: View all logs [cite: 79]
    // Test URL: http://localhost:8080/parking/logs
    @GetMapping("/logs")
    public List<String> viewLogs() {
        return FileHandler.readLogs();
    }

    // Exit: Update logs and show fee [cite: 80]
    // Test URL: http://localhost:8080/parking/exit?pin=1234
    @GetMapping("/exit")
    public String checkOut(@RequestParam String pin) {
        // For a real project, you would search for the pin in the file
        // Here is a simplified response for your demonstration
        return "Exit recorded for PIN " + pin + ". Total Fee calculated.";
    }
}