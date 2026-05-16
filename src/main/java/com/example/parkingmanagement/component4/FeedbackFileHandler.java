package com.example.parkingmanagement.component4;

import java.io.*;
import java.util.*;

public class FeedbackFileHandler {
    private static final String FILE_PATH = "src/main/resources/templates/feedback.txt";

    public static void saveFeedback(Feedback feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(feedback.toString());
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static List<String> getAllFeedback() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) { list.add(line); }
        } catch (IOException e) { }
        return list;
    }
}
