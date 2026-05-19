package com.demo.parkingmanagementsystem.component3;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @GetMapping(value = "/exit", produces = "text/html") // Force browser to read as HTML
    public String checkOut(@RequestParam String pin) {
        String data = FileHandler.findAndRemoveSession(pin);

        if (data == null) {
            return "<body style='background:#121212; color:red; text-align:center; padding-top:50px; font-family:sans-serif;'>" +
                    "<h2>❌ INVALID PIN</h2><a href='/index.html' style='color:white;'>Try Again</a></body>";
        }

        String[] p = data.split(",");
        String plate = p[0];
        LocalDateTime entry = LocalDateTime.parse(p[2]);
        String type = p[3];

        ParkingSession session = type.equals("MONTHLY") ?
                new MonthlySession(plate, pin) : new HourlySession(plate, pin);

        session.entryTime = entry;
        double fee = session.calculateFee(LocalDateTime.now());

        // This returns a "Bill Card" that automatically redirects after 3 seconds
        return "<html><body style='background: #121212; color: white; font-family: sans-serif; display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0;'>" +
                "<div style='border: 2px solid #00c851; padding: 40px; border-radius: 15px; text-align: center;'>" +
                "<h1 style='color: #00c851;'>EXIT GRANTED</h1>" +
                "<h2 style='color: #00bcd4;'>Total Fare: " + String.format("%.2f", fee) + " LKR</h2>" +
                "<p style='color: #888;'>Redirecting to feedback page in 3 seconds...</p>" +
                "</div>" +
                "<script>" +
                "  setTimeout(function() { window.location.href = '/feedback.html'; }, 3000);" +
                "</script>" +
                "</body></html>";
    }
}
