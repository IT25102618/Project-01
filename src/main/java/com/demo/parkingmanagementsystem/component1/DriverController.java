package com.demo.parkingmanagementsystem.component1;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @GetMapping("/register")
    public String register(@RequestParam String name, @RequestParam String contact,
                           @RequestParam String plate, @RequestParam String type) {
        Driver driver = new Driver(name, contact, plate, type);
        saveDriver(driver);

        return "<body style='background: #000; color: white; font-family: serif; display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0;'>" +
                "<div style='border: 2px solid #c5a059; padding: 50px; border-radius: 15px; text-align: center; background: #111;'>" +
                "<h1 style='color: #c5a059; letter-spacing: 2px;'>REGISTRATION COMPLETE</h1>" +
                "<p style='font-size: 1.2rem; border-bottom: 1px solid #333; padding-bottom: 20px;'>Welcome, <b>" + name + "</b>. Vehicle <b>" + plate + "</b> is now authorized.</p>" +
                "<a href='/parking-area.html' style='display: inline-block; margin-top: 20px; padding: 15px 30px; background: #000; color: #c5a059; text-decoration: none; border: 1px solid #c5a059; font-weight: bold; text-transform: uppercase;'>Proceed to Slot Selection</a>" +
                "</div></body>";
    }

    public static void saveDriver(Driver driver) {
        try (PrintWriter out = new PrintWriter(new FileWriter("drivers.txt", true))) {
            // FIXED ORDER: Name(0), Contact(1), PLATE(2), Type(3)
            out.println(driver.getName().trim() + "," +
                    driver.getContact().trim() + "," +
                    driver.getLicensePlate().trim() + "," +    // <--- THIS WAS WRONG
                    driver.getMembershipType().trim());           // <--- THIS WAS WRONG
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/search")
    public List<String> search(@RequestParam String plate) {
        return FileHandler.searchDriver(plate);
    }


}