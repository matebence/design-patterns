package Pattern;

public class VegNonBothRestaurant implements Hotel {

    @Override
    public Menus getMenus() {
        return new Both();
    }
}
