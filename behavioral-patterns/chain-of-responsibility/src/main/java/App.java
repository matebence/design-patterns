import Pattern.Chain;
import Pattern.Number;

public class App {

    public static void main(String[] args) {
        Chain chain = new Chain();

        chain.process(new Number(90));
        chain.process(new Number(-50));
        chain.process(new Number(0));
        chain.process(new Number(91));
    }
}