package com.demo.parkingmanagementsystem.component5;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @GetMapping("/submit")
    public String submit(@RequestParam String name, @RequestParam int rating, @RequestParam String msg) {
        Feedback fb = new Feedback(name, rating, msg);
        FeedbackFileHandler.saveFeedback(fb);

        return "<body style='background: #f8f9fa; font-family: sans-serif; display: flex; align-items: center; justify-content: center; height: 100vh;'>" +
                "<div style='background: white; padding: 40px; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); text-align: center; max-width: 400px;'>" +
                "<h1 style='color: #ffc107;'>⭐ Thank You!</h1>" +
                "<p>We appreciate your feedback, <b>" + name + "</b>. You rated us " + rating + "/5 stars.</p>" +
                "<a href='/index.html' style='color: #6c757d; text-decoration: none; font-weight: bold;'>← Back to Home</a>" +
                "</div></body>";
    }

    @GetMapping("/view")
    public List<String> view() {
        return FeedbackFileHandler.getAllFeedback();
    }
}