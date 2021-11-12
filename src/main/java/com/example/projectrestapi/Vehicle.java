package com.example.projectrestapi;

import java.io.Serializable;

/**
 * This is a super class called Vehicle. It holds all the basic properties of any
 * vehicle type like the make, model, model year, retail price etc.
 */
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1864726920921172988L;
    private String make;
    private String model;
    private String makeModel;
    private int modelYear;
    private int retailPrice;
    private int milesPerGallon;
    private boolean isFourWheelDrive;
    private int id;

    // constructor
    public Vehicle(String make, String model, int modelYear, int price, int mpg, boolean isFourWheelDrive) {
        this.make = make;
        this. model = model;
        this.modelYear = modelYear;
        this.retailPrice = price;
        this.milesPerGallon = mpg;
        this.isFourWheelDrive = isFourWheelDrive;
    }

    // second constructor
    public Vehicle(String makeModel,int modelYear,int retailPrice,boolean isFourWheelDrive) {
        this.makeModel = makeModel;
        this. modelYear = modelYear;
        this.retailPrice = retailPrice;
        this.isFourWheelDrive = isFourWheelDrive;
    }

    // third constructor
    public Vehicle(int id,String makeModel,int modelYear, int retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.modelYear = modelYear;
        this.retailPrice = retailPrice;
    }

    // getter for id
    public int getId() {
        return id;
    }

    // getter for make
    public String getMake(){
        return make;
    }

    // getter for model
    public String getModel() {
        return model;
    }

    // getter for makeModel
    public String getMakeModel() {
        return makeModel;
    }

    // getter for model year
    public int getModelYear() {
        return modelYear;
    }

    // getter for price
    public int getRetailPrice() {
        return retailPrice;
    }

    // getter for miles per gallon
    public int getMilesPerGallon() {
        return milesPerGallon;
    }

    // getter for if the vehicle has four wheel drive
    public boolean isFourWheelDrive() {
        return isFourWheelDrive;
    }

    // setter method for make of vehicle
    public void setMake(String make) {
        this.make = make;
    }

    // setter method for model of vehicle
    public void setModel(String model){
        this.model = model;
    }

    // setter method for makeModel of vehicle
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    // setter method for model year of vehicle
    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    // setter method for price of vehicle
    public void setRetailPrice(int price) {
        this.retailPrice = price;
    }

    // setter method for miles per gallon of vehicle
    public void setMilesPerGallon(int mpg) {
        this.milesPerGallon = mpg;
    }

    // setter for if vehicle has four wheel drive
    public void setFourWheelDrive(boolean isFourWheelDrive) {
        this.isFourWheelDrive = isFourWheelDrive;
    }

    /**
     * This method prints out the correct string data to console as well as returns the string
     */
    public String printVehicle(){
        String vehicleStr = modelYear + " " + make + " " + model + "\n" +
                "$" + retailPrice + ",000" + "\n" + milesPerGallon + "MPG";

        if(isFourWheelDrive) {
            vehicleStr = modelYear + " " + make + " " + model + "\n" + "4WD" + "\n" +
                    "$" + retailPrice + ",000" + "\n" + milesPerGallon + "MPG";
        }

        System.out.println(vehicleStr);

        return vehicleStr;
    }

    /**
     * This method prints out the correct string data to console when
     * using the second constructor as well as returns the string
     */
    public String printVehicle2(){
        String vehicleStr = modelYear + " " + makeModel + "\n" +
                "$" + retailPrice;

        if(isFourWheelDrive) {
            vehicleStr = modelYear + " " + makeModel + "\n" + "4WD" + "\n" +
                    "$" + retailPrice;
        }

        System.out.println(vehicleStr);

        return vehicleStr;
    }

    // This is a toString method to print to the user the year, make, and model of the vehicle
    public String toString() {
        return modelYear + " " + make + " " + model;
    }

}
