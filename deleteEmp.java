
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class deleteEmp {

    public static void main(String args[]) throws IOException {
        File inputFile = new File("C://Users//User//Documents//NetBeansProjects//POSsystem//staff.txt");
        File tempFile = new File("C://Users//User//Documents//NetBeansProjects//POSsystem//myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        System.out.println("Delete Staff");
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        int inpID = input.nextInt();
        System.out.print("Name: ");
        String inpName = input.next();
        System.out.print("Password: ");
        int inpPass = input.nextInt();

        String lineToRemove = inpID + "|" + inpName + "|" + inpPass;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        if (inputFile.exists()) {
            inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);
            if (successful == true) {
                System.out.println("Item Successfully Deleted!");
            }
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
