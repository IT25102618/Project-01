package com.demo.parkingmanagementsystem.component2;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "src/main/resources/templates/slots.txt";

    // Create: Save a new slot
    public static void saveSlot(ParkingSlot slot) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(slot.toString());
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Read: Get all slots to check availability
    public static List<String> getAllSlots() {
        List<String> slots = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) { slots.add(line); }
        } catch (IOException e) { /* File might not exist yet */ }
        return slots;
    }
}