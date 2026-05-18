package com.demo.parkingmanagementsystem.component5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Feedback {
    private String driverName; // Encapsulation: private
    private int rating;        // 1 to 5 stars
    private String comments;
    private String timestamp;

    public Feedback(String driverName, int rating, String comments) {
        this.driverName = driverName;
        this.rating = rating;
        this.comments = comments;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return driverName + " | Rating: " + rating + "/5 | Comment: " + comments + " | Date: " + timestamp;
    }

}