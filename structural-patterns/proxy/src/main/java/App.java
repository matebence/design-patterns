import Pattern.Image;
import Pattern.ProxyImage;

public class App {

    public static void main(String args[]){
        Image image = new ProxyImage("test_10mb.jpg");

        //image will be loaded from disk
        image.display();
        System.out.println("");

        //image will not be loaded from disk
        image.display();
    }
}