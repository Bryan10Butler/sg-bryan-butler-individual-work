package vehicle;

//new interface
//declare with "interface"
public interface Vehicle {
    //interfaces only
    public void moveForward(int milesPerHour);
    public void moveBackward(int milesPerHour);
    public void stop();
    public void turnLeft();
    public void turnRight();
    public void engineOn();
    public void engineOff();
}
