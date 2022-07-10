import Pattern.HotelKeeper;
import Pattern.NonVegMenu;
import Pattern.VegMenu;
import Pattern.Both;

public class App {

    public static void main(String[] args) {
        HotelKeeper keeper = new HotelKeeper();

        VegMenu v = keeper.getVegMenu();
        NonVegMenu nv = keeper.getNonVegMenu();
        Both both = keeper.getVegNonMenu();
    }
}