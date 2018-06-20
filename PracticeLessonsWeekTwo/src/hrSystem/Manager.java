package hrSystem;

//Manager is a special employee
//Manager inherits some common properties and behaviours, but also extends the
//the functionality of employee
public class Manager extends Employee{
    public void hire() {
        //code to hire someone...
    }
    public void fire() {
        //code to fire someone...
    }
    public void givePerformanceReview() {
        //code to give performance review
    }

    @Override
    public void createYearlyObjectives() {
        //we're overrdiing the version of this method in Employee
        //put new code here...
        super.createYearlyObjectives();
        //put more new code here
    }
}
