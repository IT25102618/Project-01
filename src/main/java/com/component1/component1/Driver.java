package com.component1.component1;

public class Driver extends User {
    private String licensePlate; // Encapsulation: private [cite: 57]
    private String membershipType;
    // Member or Guest [cite: 58]

    public Driver(String name, String contact, String licensePlate, String membershipType) {
        super(name, contact);
        this.licensePlate = licensePlate;
        this.membershipType = membershipType;

    }

    // Getters and Setters
    public String getLicensePlate() { return licensePlate; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getMembershipType() { return membershipType; }

    @Override
    public String toString() {
        return name + "," + contact + "," + licensePlate + "," + membershipType;
    }
}