
import Ordering.Employee;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Staff extends Employee {

    public Staff() {
    }

    public Staff(int ID, String name, int password) {
        super(ID, name, password);
    }

    @Override
    public void menu(String choice, String name) throws FileNotFoundException, IOException {
        Scanner keyboard = new Scanner(System.in);
        int number;
        char yesno;

        if (choice.equals("ST")) {
            do {
                do {
                    System.out.println("");
                    System.out.println("Menu");
                    System.out.println("=======");
                    System.out.println("1. Sales transactions");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    number = keyboard.nextInt();
                    switch (number) {
                        case 1:
                            salesTransaction(name);
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid choice, please enter again.");
                            break;
                    }
                } while (number != 2);
                System.out.print("Do you want to quit?(Y/N) ");
                yesno = keyboard.next().charAt(0);
                yesno = Character.toUpperCase(yesno);
            } while (yesno != 'Y');
        }

    }
}
