
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class addEmp {

    public static void main(String[] args) {
        int size = 100;

        System.out.print("Are you a staff or supervisor?(ST/SU) ");
        Scanner character = new Scanner(System.in);
        String choice = character.next().toUpperCase();

        if (choice.equals("ST")) {
            try {
                try ( Scanner scan = new Scanner(new File("staff.txt"))) {

                    Staff[] staff = new Staff[size];
                    int count = 0;

                    while (scan.hasNextLine()) {
                        String[] data = scan.nextLine().split("\\|");
                        int ID = Integer.parseInt(data[0]);
                        String name = data[1];
                        int pass = Integer.parseInt(data[2]);
                        staff[count] = new Staff(ID, name, pass);
                        count++; //currently 2(0,1)
                    }

                    int previousID = staff[count - 1].getID();

                    String filename = "staff.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    System.out.println("Add New Staff");
                    Scanner input = new Scanner(System.in);
                    System.out.print("ID: ");
                    int inpID = previousID + 1;
                    System.out.println(inpID);
                    System.out.print("Name: ");
                    String inpName = input.next();
                    System.out.print("Password: ");
                    int inpPass = input.nextInt();
                    fw.write(inpID + "|" + inpName + "|" + inpPass + "\n");//appends the string to the file
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        } else {
            try {
                try ( Scanner scan = new Scanner(new File("supervisor.txt"))) {

                    Supervisor[] supervisor = new Supervisor[size];
                    int count = 0;

                    while (scan.hasNextLine()) {
                        String[] data = scan.nextLine().split("\\|");
                        int ID = Integer.parseInt(data[0]);
                        String name = data[1];
                        int pass = Integer.parseInt(data[2]);
                        supervisor[count] = new Supervisor(ID, name, pass);
                        count++; //currently 2(0,1)
                    }

                    int previousID = supervisor[count - 1].getID();

                    String filename = "supervisor.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    System.out.println("Add New Supervisor");
                    Scanner input = new Scanner(System.in);
                    System.out.print("ID: ");
                    int inpID = previousID + 1;
                    System.out.println(inpID);
                    System.out.print("Name: ");
                    String inpName = input.next();
                    System.out.print("Password: ");
                    int inpPass = input.nextInt();
                    fw.write(inpID + "|" + inpName + "|" + inpPass + "\n");//appends the string to the file
                    fw.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }
}
