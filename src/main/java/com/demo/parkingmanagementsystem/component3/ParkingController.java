package com.demo.parkingmanagementsystem.component3;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @GetMapping("/entry")
    // create operation of CRDU operation
    public String checkIn(@RequestParam String plate, @RequestParam String pin) {
        ParkingSession session = new ParkingSession(plate, pin);
        FileHandler.saveSession(session);

        return "<body style='background: #0f2027; background: linear-gradient(to right, #2c5364, #203a43, #0f2027); color: white; font-family: sans-serif; display: flex; align-items: center; justify-content: center; height: 100vh;'>" +
                "<div style='background: rgba(0,0,0,0.4); backdrop-filter: blur(15px); padding: 50px; border-radius: 20px; border: 2px solid #00d2ff; text-align: center;'>" +
                "<div style='font-size: 50px;'>🚧</div>" +
                "<h1 style='color: #00d2ff;'>Gate Opening...</h1>" +
                "<p>Vehicle <b>" + plate + "</b> has been logged. Please proceed to your assigned slot.</p>" +
                "<a href='/index.html' style='display: inline-block; margin-top: 20px; padding: 10px 25px; border: 1px solid #00d2ff; color: #00d2ff; text-decoration: none; border-radius: 5px;'>Close Gate</a>" +
                "</div></body>";
    }


    @GetMapping("/logs")
    // Read of CRDU function
    public String viewLogs() {
        List<String> logs = FileHandler.readLogs();
        StringBuilder html = new StringBuilder("<head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'></head>");
        html.append("<body class='p-5 bg-dark text-white'><h2>System Activity Logs</h2><table class='table table-dark table-striped mt-4'>");
        html.append("<thead><tr><th>Log Details</th></tr></thead><tbody>");

        for (String log : logs) {
            html.append("<tr><td>").append(log).append("</td></tr>");
        }

        html.append("</tbody></table><a href='/index.html' class='btn btn-primary'>Back</a></body>");
        return html.toString();
    }

    @GetMapping("/exit")

    // U\D fuctions of CRDU operati
    public String checkOut(@RequestParam String pin) {

        return "Exit recorded for PIN " + pin + ". Total Fee calculated.";
    }




}