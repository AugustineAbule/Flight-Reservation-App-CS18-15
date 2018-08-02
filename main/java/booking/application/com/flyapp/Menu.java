package booking.application.com.flyapp;

/**
 * Created by archie on 7/17/2018.
 */

public class Menu {
    private String menu;
    private int image;

    public Menu(String menu, int image) {
        this.menu = menu;
        this.image = image;
    }

    public String getMenu() {
        return menu;
    }

    public int getImage() {
        return image;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
