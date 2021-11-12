package com.example.projectrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
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

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        return null;
    }

    @RequestMapping(value = "/getLatestVehicles", method=RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        return null;
    }



}
