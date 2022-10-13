
import Ordering.Product;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Product product = new Product();
        Order order = new Order();

        order.Menu();
        order.makeOrder();

    }

}
