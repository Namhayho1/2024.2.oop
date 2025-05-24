package hust.soict.hedspi.test.store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
	public static void main(String[] args) {
        Store store = new Store();

        // Tạo một số DVD mẫu
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);

        // Thêm DVD vào store
        System.out.println("-- Adding DVDs --");
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        // In danh sách hiện tại
        System.out.println();
        store.printStore();

        // Xóa một DVD
        System.out.println("\n-- Removing a DVD --");
        store.removeMedia(dvd2);

        // In lại danh sách
        System.out.println();
        store.printStore();

        // Thử xóa DVD không tồn tại
        System.out.println("\n-- Removing non-existing DVD --");
        DigitalVideoDisc fake = new DigitalVideoDisc("Matrix", "Action", "Wachowskis", 136, 20.0f);
        store.removeMedia(fake);
    }
}
