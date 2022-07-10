import Pattern.NetOrder;
import Pattern.OrderProcessTemplate;
import Pattern.StoreOrder;

public class App {

    public static void main(String args[]){
        OrderProcessTemplate netOrder = new NetOrder();
        netOrder.processOrder(true);
        System.out.println();
        OrderProcessTemplate storeOrder = new StoreOrder();
        storeOrder.processOrder(true);
    }
}
