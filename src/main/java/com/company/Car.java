package com.company;
public class Car{
    private String registrationNumber, color;
    private static int slotNumber;

    public Car(String registrationNumber, String color, int slotNumber) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.slotNumber = slotNumber;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public String getColor(){
        return color;
    }

    public int getSlotNumber() { return slotNumber; }

    public void setSlotNumber(int slotNumber) { this.slotNumber = slotNumber; }

    @Override
    public String toString() {
        return  slotNumber + "\t" + registrationNumber;
    }
}