
import Ordering.Product;
import Ordering.Employee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
public class Supervisor extends Employee {

    public Supervisor() {
    }

    public Supervisor(int ID, String name, int password) {
        super(ID, name, password);
    }

    public static void addEmp() throws FileNotFoundException, IOException {
        int size = 100;
        Employee[] empArray = new Employee[size];
        String file;
        char yesno;

        do {
            System.out.println("");
            System.out.println("Add Employee");
            System.out.println("===============");
            System.out.print("Add new staff or supervisor?(ST/SU) ");
            Scanner character = new Scanner(System.in);
            String choice2 = character.next().toUpperCase();

            if (choice2.equals("ST")) {
                file = "src/staff.txt";
            } else {
                file = "src/supervisor.txt";
            }

            int count = 0;

            Scanner scan = new Scanner(new File(file));

            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int pass = Integer.parseInt(data[2]);

                if (choice2.equals("ST")) {
                    empArray[count] = new Staff(id, name, pass);
                } else {
                    empArray[count] = new Supervisor(id, name, pass);
                }

                count++; //currently 2(0,1)
            }

            int previousID = empArray[count - 1].getID();

            FileWriter fw = new FileWriter(file, true); //the true will append the new data

            Scanner input = new Scanner(System.in);
            System.out.print("ID: ");
            int inpID = previousID + 1;
            System.out.println(inpID);
            System.out.print("Name: ");
            String inpName = input.nextLine();
            System.out.print("Password: ");
            int inpPass = input.nextInt();

            System.out.print("Sure to add " + inpID + "|" + inpName + "|" + inpPass + "?(Y/N) ");
            yesno = input.next().charAt(0);
            yesno = Character.toUpperCase(yesno);

            if (yesno == 'Y') {
                fw.write(inpID + "|" + inpName + "|" + inpPass + "\n");//appends the string to the file
                fw.close();
                System.out.println("Added successfully!");
            }

            System.out.print("Continue adding?(Y/N) ");
            yesno = input.next().charAt(0);
            yesno = Character.toUpperCase(yesno);
        } while (yesno == 'Y');
    }

    public static void deleteEmp() throws FileNotFoundException, IOException {

        int size = 100;
        Employee[] empArray = new Employee[size];
        String file;
        char yesno;

        do {
            System.out.println("");
            System.out.println("Delete Employee");
            System.out.println("===============");
            System.out.print("Delete staff or supervisor?(ST/SU) ");
            Scanner character = new Scanner(System.in);
            String choice2 = character.next().toUpperCase();

            if (choice2.equals("ST")) {
                file = "src/staff.txt";
            } else {
                file = "src/supervisor.txt";
            }
            File tempFile = new File("src/myTempFile.txt");

            int count = 0;

            Scanner scan = new Scanner(new File(file));
//            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int pass = Integer.parseInt(data[2]);

                if (choice2.equals("ST")) {
                    empArray[count] = new Staff(id, name, pass);
                } else {
                    empArray[count] = new Supervisor(id, name, pass);
                }

                count++;
            }

            if (choice2.equals("ST")) {
                System.out.println("");
                System.out.println("Staff List");
                System.out.println("==========");
                System.out.println("ID  Name                Password");
                System.out.println("=== =================== =========");
            } else {
                System.out.println("");
                System.out.println("Supervisor List");
                System.out.println("===============");
                System.out.println("ID  Name                Password");
                System.out.println("=== =================== =========");
            }
            for (int i = 0; i < count; i++) {
                System.out.printf("%-4d%-20s%d\n", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
            }

            System.out.println("");
            Scanner input = new Scanner(System.in);
            System.out.print("ID: ");
            int inpID = input.nextInt(); //1
            System.out.print("Name: ");
            String inpName = empArray[inpID - 1].getName(); //empArray[0].getName()
            System.out.println(inpName);
            System.out.print("Password: ");
            int inpPass = empArray[inpID - 1].getPassword();
            System.out.println(inpPass);

            System.out.print("Sure to delete?(Y/N) ");
            yesno = input.next().charAt(0);
            yesno = Character.toUpperCase(yesno);

            int index = 0;

            for (int i = 0; i < count; i++) {
                if (inpID == empArray[i].getID()) {
                    index = i;
                }
            }

            if (yesno == 'Y') {
                for (int i = index; i < count - 1; i++) {
                    empArray[i].setID(empArray[i + 1].getID());
                    empArray[i].setName(empArray[i + 1].getName());
                    empArray[i].setPassword(empArray[i + 1].getPassword());
                }

                try ( PrintWriter pw = new PrintWriter(file)) {
                    for (int i = 0; i < count - 1; i++) {
                        pw.printf("%d|%s|%d", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
                        pw.println();
                    }

                    pw.close();

                    boolean successful = true;
                    if (successful = true) {
                        System.out.println("Delete successfully");
                    } else {
                        System.out.println("Delete failed");
                    }
                }

            }

            System.out.print("Continue deleting?(Y/N) ");
            yesno = input.next().charAt(0);
            yesno = Character.toUpperCase(yesno);
        } while (yesno == 'Y');
    }

    public static void modifyEmp() throws FileNotFoundException, IOException {

        int size = 100;
        Employee[] empArray = new Employee[size];
        String file;
        char yesno;
        boolean error = false;

        do {
            System.out.println("");
            System.out.println("Modify Employee");
            System.out.println("===============");
            System.out.print("Modify staff or supervisor?(ST/SU) ");
            Scanner character = new Scanner(System.in);
            String choice2 = character.next().toUpperCase();

            if (choice2.equals("ST")) {
                file = "src/staff.txt";
            } else {
                file = "src/supervisor.txt";
            }

            int count = 0;

            Scanner scan = new Scanner(new File(file));

            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int pass = Integer.parseInt(data[2]);

                if (choice2.equals("ST")) {
                    empArray[count] = new Staff(id, name, pass);
                } else {
                    empArray[count] = new Supervisor(id, name, pass);
                }

                count++;
            }

            if (choice2.equals("ST")) {
                System.out.println("");
                System.out.println("Staff List");
                System.out.println("==========");
                System.out.println("ID  Name                Password");
                System.out.println("=== =================== =========");
            } else {
                System.out.println("");
                System.out.println("Supervisor List");
                System.out.println("===============");
                System.out.println("ID  Name                Password");
                System.out.println("=== =================== =========");
            }
            for (int i = 0; i < count; i++) {
                System.out.printf("%-4d%-20s%d\n", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
            }

            System.out.println("");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter ID to view for edit: ");
            int inpID = input.nextInt(); //1

            int index = 0;
            char modiChoice;

            for (int i = 0; i < count; i++) {
                if (inpID == empArray[i].getID()) {
                    index = i;
                    System.out.print("Do you want to modify name/password/both?(N/P/B) ");
                    modiChoice = input.next().charAt(0);
                    modiChoice = Character.toUpperCase(modiChoice);
                    switch (modiChoice) {
                        case 'N': {
                            input = new Scanner(System.in);
                            System.out.print("Enter name: ");
                            String inpName = input.nextLine();

                            System.out.print("Sure to modify?(Y/N) ");
                            yesno = input.next().charAt(0);
                            yesno = Character.toUpperCase(yesno);
                            if (yesno == 'Y') {
                                empArray[i].setID(empArray[i].getID());
                                empArray[i].setName(inpName);
                                empArray[i].setPassword(empArray[i].getPassword());
                                try ( PrintWriter pw = new PrintWriter(file)) {
                                    for (i = 0; i < count; i++) {
                                        pw.printf("%d|%s|%d", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
                                        pw.println();
                                    }

                                    pw.close();

                                    boolean successful = true;
                                    if (successful = true) {
                                        System.out.println("Modify successfully");
                                    } else {
                                        System.out.println("Modify failed");
                                    }
                                }
                            }
                            break;
                        }
                        case 'P': {
                            System.out.print("Enter password: ");
                            int inpPass = input.nextInt();
                            System.out.print("Sure to modify?(Y/N) ");
                            yesno = input.next().charAt(0);
                            yesno = Character.toUpperCase(yesno);
                            if (yesno == 'Y') {
                                empArray[i].setID(empArray[i].getID());
                                empArray[i].setName(empArray[i].getName());
                                empArray[i].setPassword(inpPass);
                                try ( PrintWriter pw = new PrintWriter(file)) {
                                    for (i = 0; i < count; i++) {
                                        pw.printf("%d|%s|%d", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
                                        pw.println();
                                    }
                                    pw.close();

                                    boolean successful = true;
                                    if (successful = true) {
                                        System.out.println("Modify successfully");
                                    } else {
                                        System.out.println("Modify failed");
                                    }
                                }
                            }
                            break;
                        }
                        case 'B': {
                            input = new Scanner(System.in);
                            System.out.print("Enter name: ");
                            String inpName = input.nextLine();
                            System.out.print("Enter password: ");
                            int inpPass = input.nextInt();
                            System.out.print("Sure to modify?(Y/N) ");
                            yesno = input.next().charAt(0);
                            yesno = Character.toUpperCase(yesno);
                            if (yesno == 'Y') {
                                empArray[i].setID(empArray[i].getID());
                                empArray[i].setName(inpName);
                                empArray[i].setPassword(inpPass);
                                try ( PrintWriter pw = new PrintWriter(file)) {
                                    for (i = 0; i < count; i++) {
                                        pw.printf("%d|%s|%d", empArray[i].getID(), empArray[i].getName(), empArray[i].getPassword());
                                        pw.println();
                                    }

                                    pw.close();

                                    boolean successful = true;
                                    if (successful = true) {
                                        System.out.println("Modify successfully");
                                    } else {
                                        System.out.println("Modify failed");
                                    }
                                }
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            }

            System.out.print("Continue modifying?(Y/N) ");
            yesno = input.next().charAt(0);
            yesno = Character.toUpperCase(yesno);
        } while (yesno == 'Y');
    }

    @Override
    public void menu(String choice, String name) throws FileNotFoundException, IOException {
        Scanner keyboard = new Scanner(System.in);
        int number;
        char yesno;

        if (choice.equals("SU")) {

            do {
                do {
                    System.out.println("");
                    System.out.println("Menu");
                    System.out.println("=======");
                    System.out.println("1. Product management");
                    System.out.println("2. Employee management");
                    System.out.println("3. Exit");
                    System.out.print("Enter choice: ");
                    number = keyboard.nextInt();
                    switch (number) {
                        case 1:
                            Product.prodManagement();
                            break;
                        case 2:
                            empManagement();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid choice, please enter again.");
                            break;

                    }
                } while (number != 3);
                System.out.print("Do you want to quit?(Y/N) ");
                yesno = keyboard.next().charAt(0);
                yesno = Character.toUpperCase(yesno);
            } while (yesno != 'Y');
        }

    }
}

