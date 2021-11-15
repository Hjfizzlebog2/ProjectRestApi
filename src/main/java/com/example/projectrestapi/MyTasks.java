package com.example.projectrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyTasks extends VehicleController{

    public static int idCounter = 0;

    //SCHEDULED METHODS
    @Scheduled(cron = "* * * 0/ * *")
    public void addVehicle() throws IOException {
        //Randomly Generate New Vehicle Data
        Random rand = new Random();

        List<String> lines = Files.readAllLines(Paths.get("randvehiclesrc.txt"));
        String makeModel = lines.get(rand.nextInt(lines.size()-1));

        int year = rand.nextInt(30) + 1986;
        int price = rand.nextInt(30000) + 15000;

        //Check next Id is available
        while(getVehicle(idCounter) != null) {
            idCounter++;
        }

        //Create new Vehicle
        Vehicle newVehicle = new Vehicle(idCounter, makeModel, year, price);

        //ObjectMapper provides functionality for reading and writing JSON
        ObjectMapper mapper = new ObjectMapper();

        //Create a FileWrite to write to inventory.txt and APPEND mode is true
        FileWriter output = new FileWriter("./inventory.txt",true);

        //Serialize object to JSON and write to file
        mapper.writeValue(output,newVehicle);

        //Append a new line character to the file
        //Above FileWriter "output" is automatically closed by the mapper
        FileUtils.writeStringToFile(new File("./inventory.txt"),
                System.lineSeparator(), CharEncoding.UTF_8, true);
    }

    Random random = new Random();
    static int startingID = 0;

    @Scheduled(cron = "* */10 * * * *")
    public void deleteVehicle() throws IOException {
        //Generate random vehicle ID. Starting ID is incremented since adding vehicles is assumed.
        int vehicleId = random.nextInt(idCounter);
        //Make delete request
        deleteVehicle(vehicleId);
        startingID++;
    }

    @Scheduled(cron = "")
    public void updateVehicle() {

    }

    @Scheduled(cron = "")
    public void latestVehiclesReport() {

    }
}
