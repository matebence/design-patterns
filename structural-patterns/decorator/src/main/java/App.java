import Pattern.BasicCar;
import Pattern.Car;
import Pattern.LuxuryCar;
import Pattern.SportsCar;

public class App {

    public static void main(String args[]){
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}