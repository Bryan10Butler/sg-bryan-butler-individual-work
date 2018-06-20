package compositionComputers;

public class Main {

    public static void main(String[] args) {

        Dimensions dimensions = new Dimensions(20,20,5);
        Case theCase = new Case("220B", "Dell", "240",dimensions);

        //another way to create instance within instance
        Monitor theMonitor = new Monitor("27inch Beast","Acer", 27, new Resolution(2540,1440));

        MotherBoard theMotherBoard = new MotherBoard("BJ-200", "Asus", 2, 6, "a");

        //create our PC
        PC thePC = new PC(theCase, theMonitor, theMotherBoard);
        //accessing methods through getters and "."
        thePC.getMonitor().drawPixel(1500,1200,"Red");
        thePC.getMotherBoard().loadProgram("Windows 1.0");
        thePC.getTheCase().pressPowerButton();

        System.out.println(thePC);

    }
}
