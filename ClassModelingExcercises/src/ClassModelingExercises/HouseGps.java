package ClassModelingExercises;

public class HouseGps {

    //properties
    private String houseNumber;
    private String streetNumber;
    private String cityName;
    private String state;
    private String zipCode;
    private boolean isHomeAddress;
    private double latitude;
    private double longitude;

    //constructor
    public HouseGps() {

    }//end of HouseGps

    //getter and setters
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isHomeAddress() {
        return isHomeAddress;
    }

    public void setHomeAddress(boolean homeAddress) {
        isHomeAddress = homeAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
