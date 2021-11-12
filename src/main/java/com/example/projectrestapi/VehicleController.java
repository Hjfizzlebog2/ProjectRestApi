package com.example.projectrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class VehicleController {

    @RequestMapping(value = "/addVehicle",method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        //ObjectMapper provides functionality for reading and writing JSON
        ObjectMapper mapper = new ObjectMapper();

        //Create a FileWrite to write to inventory.txt and APPEND mode is true
        FileWriter output = new FileWriter("./inventory.txt",true);

        //Serialize object to JSON and write to file
        mapper.writeValue(output,newVehicle);

        //Append a new line character to the file
        //Above FileWriter "output" is automatically closed by the mapper
        FileUtils.writeStringToFile(new File("./inventory.txt"),
                System.lineSeparator(),
                CharEncoding.UTF_8,
                true);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        Vehicle currentVehicle = null;
        ObjectMapper mapper = new ObjectMapper();

        Scanner fileScan = new Scanner(new File("./inventory.txt"));
        while (fileScan.hasNextLine()) {
            String currentLine = fileScan.nextLine();
            Vehicle vehicleInFile = mapper.readValue(currentLine,Vehicle.class);
            if (vehicleInFile.getId() == id) {
                currentVehicle = vehicleInFile;
            }
        }
        return currentVehicle;
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        return null;
    }

    //NOTE: CURRENT METHOD IS SLOW, BUT IT WORKS. CAN REFACTOR LATER
    // In order to delete, just read file line by line and do nothing if it is the vehicle you want to delete
    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inventoryFile = new File("./inventory.txt");
        File tempInventoryFile = new File("./tempInventory.txt");

        // MAKE TEMP COPY OF FILE
        FileUtils.copyFile(inventoryFile, tempInventoryFile);

        // CLEAR OLD FILE
        PrintWriter writer = new PrintWriter(inventoryFile);
        writer.print("");
        writer.close();

        //TODO: Actual recreating and deletion is WIP

        // START TO ADD BACK LINES TO FILE ONE BY ONE
        boolean deletionIsSuccessful = false;
        FileWriter output = new FileWriter(tempInventoryFile,true);
            // EXCEPT IN THE CASE OF FILE
            // USE BOOLEAN FLAG FOR DENOTING DELETION

        // DELETE TEMP COPY
        FileUtils.deleteQuietly(tempInventoryFile);

        //RETURN ResponseEntity DEPENDING ON DELETION STATUS
        if (deletionIsSuccessful) {
            return new ResponseEntity<>("Deletion successful.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deletion unsuccessful." +
                    " ID not in inventory.", HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/getLatestVehicles", method=RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        return null;
    }



}
