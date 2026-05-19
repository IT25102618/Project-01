package com.demo.parkingmanagementsystem.component3;
//call the input /output library of java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "parking_logs.txt";


    private static final String FILE_PATH = "C:UsersNithuDesktopParkingManagementSystemparking_logs.txt";
    // create function of CRDU operation

    public static void saveSession(ParkingSession session) {
        // without this nez cars will overide the old
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(session.toString());
            writer.newLine();
            System.out.println("Data successfully written to templates/parking_logs.txt");
        // error handler
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read of CRDU function
    public static List<String> readLogs() {
        //initialize empty list
        List<String> logs = new ArrayList<>();
        //closes automatically even if error
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            //read ech line until empty and store it to list
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) { /* File might not exist yet */ }
        return logs;
    }

}
