import Pattern.Cricket;
import Pattern.Football;
import Pattern.Game;

public class App {

    public static void main(String args[]){

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
