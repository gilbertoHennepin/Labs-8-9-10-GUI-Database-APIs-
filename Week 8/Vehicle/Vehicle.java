package week8.vehicle;

public class Vehicle {

    private String name;
    private String make;
    private String model;
    private boolean isElectric;

    public Vehicle(String name, String make, String model, boolean isElectric) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.isElectric = isElectric;
    }

    @Override
    public String toString() {          //Print everything with toString Method
        String electricString = isElectric ? "Electric" : "Gasoline";

        return String.format ("%s: %s %s. %s", name, make, model, electricString);
    }

    public String getName() {
        return name;
    }

    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public boolean isElectric() {
        return isElectric;
    }

}
