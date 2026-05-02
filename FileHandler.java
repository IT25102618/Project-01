package com.demo.parkingmanagementsystem.component3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "parking_logs.txt";

    // Create: Save a new transaction [cite: 78]
    // Inside FileHandler.java
    private static final String FILE_PATH = "src/main/resources/templates/parking_logs.txt";

    public static void saveSession(ParkingSession session) {
        // try-with-resources automatically saves and closes the file [cite: 8]
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(session.toString());
            writer.newLine();
            System.out.println("Data successfully written to templates/parking_logs.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read: View currently parked vehicles [cite: 79]
    public static List<String> readLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) { /* File might not exist yet */ }
        return logs;
    }

}