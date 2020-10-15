import Pattern.Computer;
import Pattern.ComputerPart;
import Pattern.ComputerPartDisplayVisitor;

public class App {

    public static void main(String args[]){
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}