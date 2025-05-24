package hust.soict.hedspi.test.cart;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
	public static void main(String[] args) {
        // Create a new cart
        Cart cart = new Cart();

        // Create new DVD objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        cart.addMedia(dvd3);

        // Test the print method
        cart.printCart();

        // Test search by ID
        System.out.println("Testing search by ID:");
        cart.searchMediaByID(dvd1.getId()); // Should find dvd1
        cart.searchMediaByID(999); // Should not find

        // Test search by title
        System.out.println("Testing search by title:");
        cart.searchMediaByTitle("Lion"); // Should find dvd1
        cart.searchMediaByTitle("Wars"); // Should find dvd2
        cart.searchMediaByTitle("Aladdin"); // Should not find
    }
}	
