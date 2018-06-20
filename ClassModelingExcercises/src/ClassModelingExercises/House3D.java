package ClassModelingExercises;

public class House3D {

    //properties
    private String name;
    private double squareFootage;
    private double numberOfAcres;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfFloors;
    private Garage aGarage;

    //constructor
    public House3D(String blueprintName, double blueprintSquareFootage) {

        this.name = blueprintName;
        this.squareFootage = blueprintSquareFootage;
    }

    //getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public double getNumberOfAcres() {
        return numberOfAcres;
    }

    public void setNumberOfAcres(double numberOfAcres) {
        this.numberOfAcres = numberOfAcres;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Garage getaGarage() {
        return aGarage;
    }

    public void setaGarage(Garage aGarage) {
        this.aGarage = aGarage;
    }
}
