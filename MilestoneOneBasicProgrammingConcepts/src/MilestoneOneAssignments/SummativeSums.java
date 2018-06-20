package MilestoneOneAssignments;

public class SummativeSums {

    //Start execution of Program name main
    public static void main(String[] args) {
        //Declare each array.
        //Initialize each array.
        //By initializing the array this way, each array has a set index.
        //arrayOne has an index of [9], meaning 9 slots reserved for elements to be stored.
        int arrayOne[] = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int arrayTwo[] = {999, -60, -77, 14, 160, 301};
        int arrayThree[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99};

        //Call the method addArrayONe that I created outside of main.
        //Specify in my parameter what I want executed.
        //In this case, I want to output the elements in each array.
        System.out.print("#1 Array Sum: ");
        addArrayOne(arrayOne);
        System.out.print("#2 Array Sum: ");
        addArrayOne(arrayTwo);
        System.out.print("#3 Array Sum: ");
        addArrayOne(arrayThree);

    }
    //Build out my method
    //Syntax as follows <access modifier><static><Return Type><Method Name><Parameter List><{Code Block}>
    public static void addArrayOne(int arraySum[]) {
        //Set int arrayTotal to 0 we will be totalling the elements of each array.
        //The total of all elements in each array will be stored in arrayTotal
        int arrayTotal = 0;
        //Create my For Loop
        //Increment by 1 as long as my counter is less than the length of my array
        for (int counter = 0; counter < arraySum.length; counter++) {
            arrayTotal+=arraySum[counter];
        }
        //I added this "print" to print the totals vertically
        System.out.print(arrayTotal);
        //I added this print line to print each array on a new line after For Loop is executed
        System.out.println();
    }
}


