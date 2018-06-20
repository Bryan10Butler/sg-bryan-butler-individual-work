package vehicle;

//extend an interface Vehicle
//we want everything of existing vehicle, but add more due to new vehicles we have
public interface EmergencyVehicle extends Vehicle {

    public void flashersOn();
    public void flashersOff();
    public void sirenOn();
    public void sirenOff();

}

