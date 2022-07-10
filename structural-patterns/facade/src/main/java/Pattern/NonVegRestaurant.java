package Pattern;

public class NonVegRestaurant implements Hotel {

    @Override
    public Menus getMenus() {
        return new NonVegMenu();
    }
}