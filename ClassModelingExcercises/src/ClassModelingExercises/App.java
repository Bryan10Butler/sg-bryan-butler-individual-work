package ClassModelingExercises;

public class App {

    public static void main(String[] args) {

        HouseGps myHouseGPS = new HouseGps();

        myHouseGPS.setHouseNumber("25");
        myHouseGPS.setStreetNumber("Main St");
        myHouseGPS.setCityName("Portsmouth");
        myHouseGPS.setZipCode("03801");

        System.out.println("House Number: " + myHouseGPS.getHouseNumber());
        System.out.println("Street: " + myHouseGPS.getStreetNumber());
        System.out.println("City: " + myHouseGPS.getCityName());
        System.out.println("Zip Code: " + myHouseGPS.getZipCode());

        Garage myGarage = new Garage();

        myGarage.setSquareFootage(400.50);
        myGarage.setNumberOfParkingSpots(3);

        System.out.println("Square Footage : " + myGarage.getSquareFootage());
        System.out.println("Parking Spots: " + myGarage.getNumberOfParkingSpots());

        House3D myHouse3D = new House3D("Project One", 150);

        myHouse3D.setNumberOfRooms(5);
        myHouse3D.setNumberOfBathrooms(3);
        myHouse3D.setNumberOfFloors(3);
        myHouse3D.setNumberOfAcres(60.5);

        System.out.println("Blueprint Name: " + myHouse3D.getName());
        System.out.println("Blueprint Square Footage: " + myHouse3D.getSquareFootage());
        System.out.println("Number of Rooms :" + myHouse3D.getNumberOfRooms());
        System.out.println("Number of Bathrooms :" + myHouse3D.getNumberOfBathrooms());
        System.out.println("Number of Floors :" + myHouse3D.getNumberOfFloors());
        System.out.println("Number of Acres: " + myHouse3D.getNumberOfAcres());






    }//end of main
}//end of class app
