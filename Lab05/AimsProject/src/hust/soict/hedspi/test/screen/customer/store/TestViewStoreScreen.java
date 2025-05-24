package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Create a concrete implementation of Media since Media is abstract
class GenericMedia extends Media {
    public GenericMedia(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    @Override
    public void display() {
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Category: " + getCategory());
        System.out.println("Cost: " + getCost() + "$");
    }
}

public class TestViewStoreScreen extends Application {
    private static Store store;
    private Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        cart = new Cart();
        ViewStoreController viewStoreController = new ViewStoreController(store,cart);
        fxmlLoader.setController(viewStoreController);
        Parent root	= fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();

        // 5 Media có thể play
        Media dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f);
        Media dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f);
        Media cd1 = new CompactDisc(3, "Thriller", "Pop", 15.95f);

        Media cd2 = new CompactDisc(4, "Back in Black", "Rock", 18.99f);


        Media cd3 = new CompactDisc(5,"CD 3","category",15f);

        // 5 Media không thể play
        Media media6 = new GenericMedia(6, "Inception", "Thriller", 18.99f);
        Media media7 = new GenericMedia(7, "The Hobbit", "Fantasy", 12.99f);
        Media media8 = new GenericMedia(8, "To Kill a Mockingbird", "Classic", 9.99f);
        Media media9 = new GenericMedia(9, "Clean Code", "Technical", 32.50f);
        Media media10 = new GenericMedia(10, "Design Patterns", "Technical", 39.99f);


        // Thêm media vào store
        store.addMedia(dvd1, dvd2, cd1, cd2, cd3, media6, media7, media8, media9,media10);

        launch(args);
    }
}