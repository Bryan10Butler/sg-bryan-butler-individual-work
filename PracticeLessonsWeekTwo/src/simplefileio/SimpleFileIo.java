package simplefileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleFileIo {

    public static void main(String[] args) throws Exception {
        //how to declare a print writer with new file OutFile
        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));

        //write out to your new created file
        out.println("This is line 1");
        out.println("This is line 2");

        //when done, flush and close print writer
        //flush forces to write to file
        out.flush();
        //always want to close as you could run out of resources
        out.close();

        //Read from the file we created
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("OutFile.txt")));

        //code to reach from the file itself
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            //print out current line
            System.out.println(currentLine);
        }
        scanner.close();
    }

}
