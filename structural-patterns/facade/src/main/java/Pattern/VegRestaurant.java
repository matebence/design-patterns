package Pattern;

public class VegRestaurant implements Hotel {

    @Override
    public Menus getMenus() {
        return new VegMenu();
    }
}
