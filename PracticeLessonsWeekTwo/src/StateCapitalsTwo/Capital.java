package StateCapitalsTwo;

import java.util.Scanner;

import static sun.misc.Version.print;

public class Capital {

    private String Name;
    private int Population;
    private int mileage;

    public Capital (String Name, int Population, int mileage) {
        this.Name = Name;
        this.Population = Population;
        this.mileage = mileage;
    }

    public String getName() {
        return Name;
    }

    public int getPopulation() {
        return Population;
    }

    public int getMileage() {
        return mileage;
    }

    public String toString() {
        //%s is a place holder for what we want want to enter (s = String)
        //Replace %s with name
        //Replace %s with owner
        //owner is not a string -  we will get weird output if left like this
        //changes need to be made in Human class

        return Name + " | Pop: " + Population + " | Area: " + mileage + " sq mi";
    }
}
