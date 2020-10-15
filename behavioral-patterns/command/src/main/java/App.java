import Pattern.Broker;
import Pattern.BuyStock;
import Pattern.SellStock;
import Pattern.Stock;

public class App {

    public static void main(String args[]){
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
