package com.example.projectrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

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
    RestTemplate restTemplate = new RestTemplate();
    Random rand = new Random();

    //SCHEDULED METHODS
    @Scheduled(cron = "* * * 0/ * *")
    public void addVehicle() throws IOException {
        //Randomly Generate New Vehicle Data
        Random rand = new Random();

        List<String> linesInFile = Files.readAllLines(Paths.get("randvehiclesrc.txt"));
        String makeModel = linesInFile.get(rand.nextInt(linesInFile.size()-1));

        int randomYear = rand.nextInt(30) + 1986;
        int randomPrice = rand.nextInt(30000) + 15000;

        //Check next Id is available
        while(getVehicle(idCounter) != null) {
            idCounter++;
        }

        //Create new Vehicle
        Vehicle newVehicle = new Vehicle(idCounter, makeModel, randomYear, randomPrice);

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

    static int startingID = 0;

    @Scheduled(cron = "* */10 * * * *")
    public void deleteVehicle() throws IOException {
        //Generate random vehicle ID. Starting ID is incremented since adding vehicles is assumed.
        int vehicleId = rand.nextInt(idCounter);
        //Make delete request
        deleteVehicle(vehicleId);
        startingID++;
    }

    private void doUpdate(Vehicle newVehicle) {
        String url = "http://localhost:8080/updateVehicle";
        restTemplate.put(url, newVehicle);
    }

    private Vehicle getUpdate() {
        String getUrl = "http://localhost:8080/getVehicle/{id}";
        return restTemplate.getForObject(getUrl, Vehicle.class);
    }
    @Scheduled(cron = "*/5 * * * * *")
    public void updateVehicle() throws IOException {
        //Randomly Generate New Vehicle Data

        List<String> linesInFile = Files.readAllLines(Paths.get("randvehiclesrc.txt"));
        String makeModel = linesInFile.get(rand.nextInt(linesInFile.size()-1));

        int randomYear = rand.nextInt(30) + 1986;
        int randomPrice = rand.nextInt(30000) + 15000;

        //Check next Id is available
        while(getVehicle(idCounter) != null) {
            idCounter++;
        }

        //Create new Vehicle
        Vehicle car2 = new Vehicle(idCounter, makeModel, randomYear, randomPrice);

        doUpdate(car2);
        System.out.println(getUpdate().getId());
    }

    @Scheduled(cron = "0 0 * * * *")
    public void latestVehicleReport() throws IOException {
        // create a list by calling getLatestVehicles method
        List<Vehicle> latestList = getLatestVehicles();

        // as long as the list is not null, loop through and print
        if (!(latestList == null)) {
            for (Vehicle currentVehicle : latestList) {
                System.out.println(currentVehicle.printVehicle2());
            }
        }
    }
}
