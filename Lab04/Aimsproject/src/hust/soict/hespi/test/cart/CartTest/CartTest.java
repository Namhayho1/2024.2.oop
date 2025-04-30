package hust.soict.hespi.test.cart.CartTest;
import hust.soict.hespi.aims.cart.Cart; // Import lớp Cart cần test
import hust.soict.hespi.aims.media.*; // Import các lớp media
import java.util.List;

public class CartTest {

    public static void main(String[] args) {
        // 1. Tạo một đối tượng Cart mới
        Cart myCart = new Cart();

        // 2. Tạo các đối tượng Media mẫu
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 120, 24.95f);
        Book book1 = new Book("Aladdin", "Animation", 18.99f); // Dùng tên giống để test remove/filter
        book1.addAuthor("Disney");
        CompactDisc cd1 = new CompactDisc("Frozen Soundtrack", "Animation", 15.50f, "Various Artists");
        cd1.addTrack(new Track("Let It Go", 4));
        cd1.addTrack(new Track("Do You Want to Build a Snowman?", 3));
        Book book2 = new Book("Star Wars", "Novelization", 10.00f); // Cùng tên với DVD
        book2.addAuthor("George Lucas");


        // 3. Kiểm tra addMedia()
        System.out.println("--- Testing addMedia() ---");
        myCart.addMedia(dvd1);
        myCart.addMedia(dvd2);
        myCart.addMedia(book1);
        myCart.addMedia(cd1);
        // Thử thêm lại media đã có (theo title)
        System.out.println("\nAttempting to add existing media (The Lion King):");
        myCart.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f)); // Không được thêm
         // Thêm media khác có cùng title
         System.out.println("\nAttempting to add different media type with same title (Star Wars Book):");
         myCart.addMedia(book2); // Vẫn thêm được vì khác object ID (nếu equals chỉ dựa trên title)

        // 4. In giỏ hàng và tổng tiền
        System.out.println("\n--- Cart Contents after Adding ---");
        myCart.printCart();


        // 5. Kiểm tra removeMedia()
        System.out.println("\n--- Testing removeMedia() ---");
        // Tạo đối tượng đại diện để xóa (chỉ cần title vì equals của Media dựa vào title)
        Media mediaToRemove = new Book("Aladdin");
        System.out.println("Attempting to remove 'Aladdin':");
        myCart.removeMedia(mediaToRemove);

        // Thử xóa media không có trong giỏ
        System.out.println("\nAttempting to remove non-existent media ('Mulan'):");
        myCart.removeMedia(new Book("Mulan"));

        System.out.println("\n--- Cart Contents after Removing ---");
        myCart.printCart();


        // 6. Kiểm tra các phương thức tìm kiếm/lọc
        System.out.println("\n--- Testing Search/Filter ---");
        System.out.println("Searching for ID 1 (The Lion King):");
        myCart.searchById(dvd1.getId()); // Tìm theo ID của dvd1
        System.out.println("\nSearching for ID 99 (Non-existent):");
        myCart.searchById(99);
        System.out.println("\nSearching for title 'Star Wars':");
        List<Media> found = myCart.searchByTitle("Star Wars");
        System.out.println("Found " + found.size() + " item(s) matching 'Star Wars'.");
        System.out.println("\nSearching for title 'Tangled':");
        myCart.searchByTitle("Tangled");

        // 7. Kiểm tra các phương thức sắp xếp
        System.out.println("\n--- Testing Sorting ---");
        System.out.println("\nSorting by Title:");
        myCart.sortByTitle();
        myCart.printCart();

        System.out.println("\nSorting by Cost:");
        myCart.sortByCost();
        myCart.printCart();

        // 8. Kiểm tra đặt hàng (làm rỗng giỏ)
        System.out.println("\n--- Testing Place Order ---");
        System.out.println("Placing order...");
        myCart.emptyCart(); // Mô phỏng việc đặt hàng bằng cách làm rỗng
        myCart.printCart(); // In giỏ hàng rỗng

        System.out.println("\n--- Cart Test Completed ---");
    }
}