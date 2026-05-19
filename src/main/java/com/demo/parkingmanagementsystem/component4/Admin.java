package com.demo.parkingmanagementsystem.component4;
import com.demo.parkingmanagementsystem.component1.User;

public class Admin extends User {
    private String adminId;
    private String role; // "Manager" or "Attending Staff"
    private String password;

    public Admin(String name, String contact, String adminId, String role, String password) {
        super(name, contact);
        this.adminId = adminId;
        this.role = role;
        this.password = password;
    }

    public String getAdminId() { return adminId; }
    public String getRole() { return role; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        // Format: ID, Name, Role, Contact, Password
        return adminId + "," + name + "," + role + "," + contact + "," + password;
    }
}