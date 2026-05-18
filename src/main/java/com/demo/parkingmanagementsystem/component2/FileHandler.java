package com.demo.parkingmanagementsystem.component2;

import java.io.*;
import java.util.*;

public class FileHandler {

    //file location where slot data is stored
    private static final String FILE_PATH = "src/main/resources/templates/slots.txt";

    //save slot data

    //Save a parking slot into the text file 
    public static void saveSlot(ParkingSlot slot) {

        //add data without deleting old data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            //Convert slot object into text 
            writer.write(slot.toString());
            writer.newLine();

            //print error if something fails
        } catch (IOException e) { e.printStackTrace(); }
    }

    // Read: Get all slots records from the file
    public static List<String> getAllSlots() {

        //create empty list to store slot data
        List<String> slots = new ArrayList<>();

        //open file for reading
        //try with resources automatically closes the reader after use
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            //read file line by line
            while ((line = reader.readLine()) != null) { 

                //add each line into the list
                slots.add(line);
            }
        } catch (IOException e) { /* File might not exist yet */ }

        //return all slot records
        return slots;
    }
}
