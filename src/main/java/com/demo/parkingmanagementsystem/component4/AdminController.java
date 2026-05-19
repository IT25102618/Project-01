package com.demo.parkingmanagementsystem.component4;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;
import com.demo.parkingmanagementsystem.component2.SlotController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final String ADMIN_FILE = "admins.txt";

    @PostMapping("/login")
    public String login(@RequestParam String adminId,
                        @RequestParam String password,
                        @RequestParam String role) {
        File file = new File(ADMIN_FILE);
        boolean userFound = false;

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] details = line.split(",");
                    if (details.length >= 5 && details[0].equals(adminId)) {
                        userFound = true;
                        if (details[4].equals(password) && details[2].equalsIgnoreCase(role)) {
                            // Returns a unique code for the frontend to handle the UI change
                            return role.equalsIgnoreCase("Manager") ? "SHOW_MANAGER_UI" : "SHOW_STAFF_UI";
                        } else {
                            return "INVALID_CREDENTIALS";
                        }
                    }
                }
            } catch (IOException e) { return "ERROR"; }
        }

        // Automatic Registration if User Not Found
        try (PrintWriter out = new PrintWriter(new FileWriter(ADMIN_FILE, true))) {
            Admin newAdmin = new Admin("New User", "N/A", adminId, role, password);
            out.println(newAdmin.toString());
            return role.equalsIgnoreCase("Manager") ? "SHOW_MANAGER_UI" : "SHOW_STAFF_UI";
        } catch (IOException e) { return "ERROR_SAVING"; }
    }

    @GetMapping("/search-driver")
    public String searchDriver(@RequestParam String plate) {
        File file = new File("drivers.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");

                // Index 2 is the License Plate
                if (details.length >= 3 && details[2].equalsIgnoreCase(plate.trim())) {
                    String otp = SlotController.getOtpForPlate(plate.trim());
                    // Frontend expects: Name,Contact,Plate,Type,OTP
                    String type = details.length > 3 ? details[3] : "Unknown";
                    return details[0] + "," + details[1] + "," + details[2] + "," + type + "," + otp;
                }
            }
        } catch (IOException e) {
            return "ERROR";
        }

        return "NOT_FOUND";
    }
    // CRUD: Delete
    @PostMapping("/delete-driver")
    public String deleteDriver(@RequestParam String plate) {
        File inputFile = new File("drivers.txt");
        File tempFile = new File("drivers_temp.txt");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 3 && details[2].equalsIgnoreCase(plate)) {
                    found = true; // Skip this line (deleting it)
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) { return "ERROR"; }

        if (found) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return "DELETE_SUCCESS";
        }
        tempFile.delete();
        return "NOT_FOUND";
    }

    @PostMapping("/update-driver")
    public String updateDriver(@RequestParam String plate, @RequestParam String newContact) {
        File inputFile = new File("drivers.txt");
        File tempFile = new File("drivers_temp.txt");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                // Plate is at Index 2
                if (details.length >= 3 && details[2].equalsIgnoreCase(plate)) {
                    // We keep Index 0 (Name), update Index 1 (Contact), keep Index 2 (Plate) and Index 3 (Type)
                    writer.println(details[0] + "," + newContact + "," + details[2] + "," + details[3]);
                    found = true;
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) { return "ERROR"; }

        if (found) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return "UPDATE_SUCCESS";
        }
        tempFile.delete();
        return "NOT_FOUND";
    }
}