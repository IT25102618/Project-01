package com.demo.parkingmanagementsystem.component1;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "src/main/resources/templates/drivers.txt";

    public static void saveDriver(Driver driver) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(driver.toString());
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static List<String> searchDriver(String license) {
        List<String> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(license)) { results.add(line); }
            }
        } catch (IOException e) { }
        return results;
    }
}