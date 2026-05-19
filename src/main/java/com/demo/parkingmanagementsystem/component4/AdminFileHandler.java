package com.demo.parkingmanagementsystem.component4;

import java.io.*;
import java.util.*;

public class AdminFileHandler {
    private static final String FILE_PATH = "src/main/resources/templates/admins.txt";

    // Create: Save new staff/admin account
    public static void saveAdmin(Admin admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(admin.toString());
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Read: View system activity (can read from parking_logs.txt created in Comp 03)
    public static List<String> getSystemActivity() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/templates/parking_logs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) { logs.add(line); }
        } catch (IOException e) { }
        return logs;
    }
}