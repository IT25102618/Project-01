package com.component1.component1;

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
        saveDriver(driver); // Call the fixed method below

        return "<body style='background: #121212; color: white; font-family: sans-serif; display: flex; align-items: center; justify-content: center; height: 100vh;'>" +
                "<div style='background: rgba(255,255,255,0.1); padding: 50px; border-radius: 20px; text-align: center;'>" +
                "<h1 style='color: #28a745;'>✔️ Registration Complete</h1>" +
                "<p style='font-size: 1.2rem;'>Welcome, <b>" + name + "</b>! Your vehicle <b>" + plate + "</b> is authorized.</p>" +
                "<a href='/parking-area.html' style='display: inline-block; margin-top: 20px; padding: 15px 25px; background: #03d2ff; color: white; text-decoration: none; border-radius: 10px; font-weight: bold;'>Proceed to Slot Selection</a>" +
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