package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public int emptySlot(int slots[], int parkingSize){
        int itr = 0;
        while(slots[itr]!=0 && itr<parkingSize){
            itr++;
        }
        return itr;
    }

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("file_input.txt")));
        String iterations[] = contents.split("\r|\n");
        int numOfCommands = iterations.length;
        contents = contents.replace("\r", " ");
        contents = contents.replace("\n", " ");
        String[] commands = contents.split(" ");


        int capacity = Integer.parseInt(commands[1]);
        int slots[] = new int[capacity+1];

        LinkedHashMap<String, Car> parking = null;      //LinkedHashMap for maintaining the order of insertions in the output.

        int charge = 0;
        if (capacity > 0){
            parking = new LinkedHashMap<String, Car>(capacity);
            System.out.println("Created parking lot with "+ capacity + " slots.");
        }
        else{
            System.out.println("Invalid parking size!!");
            return;
        }


        int nextEmptySlot = 1;      //initial empty location in the parking lot

        for (int i = 1; i <= numOfCommands*2; i++) {

            if(commands[i].equals("park")) {
                if(parking.size() < capacity) {
                    Car c = new Car(commands[i + 1], "red", nextEmptySlot);

                    //testing if car is already present in the parking lot
                    if (parking.containsKey(commands[i+1])){
                        System.out.println("This car is already parked!!");
                        continue;
                    }

                    slots[nextEmptySlot] = 1;       //1 represents that the slot is no longer empty.
                    parking.put(commands[i + 1], c);

                    System.out.println("Allocated slot number : " + nextEmptySlot);
                    nextEmptySlot++;
                }else{
                    System.out.println("Sorry Parking lot is full.");
                }
            }

            else if(commands[i].equals("leave")){
                if(parking.containsKey(commands[i+1])){
                    nextEmptySlot = parking.get(commands[i+1]).getSlotNumber();      //position where the car is located in the parking lot

                    slots[nextEmptySlot] = 0;        //marking the position available for parking
                    parking.remove(commands[i+1]);      //remove the car with given registration number
                    int hours = Integer.parseInt(commands[i+2]);

                    //calculation the charges for parking
                    if(hours>2){
                        charge = (hours-2)*10 +10;
                    }else {
                        charge = 10;
                    }

                    System.out.println("\nRegistration number "+ commands[i+1] +" with slot no. "+ nextEmptySlot +" is free with charge " + charge+"\n");
                }
                else {
                    System.out.println("Vehicle not present.");
                }
            }

            else if(commands[i].equals("status")){
                System.out.println("Slot No. \t Registration No.\n");
                Iterator itr = parking.entrySet().iterator();
                while(itr.hasNext()){
                    Map.Entry parkedCar = (Map.Entry)itr.next();
                    System.out.println( parkedCar.getValue() );
                }
            }
        }
    }
}
