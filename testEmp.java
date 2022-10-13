
import Ordering.Employee;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class testEmp extends Employee {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        Employee emp = new Staff();
        String choice;
        choice = emp.chooseEmp();

        if (choice.equals("ST")) {
            emp = new Staff();
        } else {
            emp = new Supervisor();
        }
        
        emp.logIn(choice);
    }

}
