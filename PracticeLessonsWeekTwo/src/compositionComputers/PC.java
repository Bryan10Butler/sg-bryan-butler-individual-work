package compositionComputers;

public class PC {

    private Case theCase;
    private Monitor monitor;
    private MotherBoard motherBoard;

    public PC(Case theCase, Monitor monitor, MotherBoard motherBoard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherBoard = motherBoard;
    }


    //has a case
    public Case getTheCase() {
        return theCase;
    }


    //has a monitor
    public Monitor getMonitor() {
        return monitor;
    }

    //has a motherboard
    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void powerUp() {
    }
}
