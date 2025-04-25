package hust.soict.hespi.aims.store; // Package riêng cho test Store

import hust.soict.hespi.aims.media.*; // Import các lớp media
import hust.soict.hespi.aims.store.Store; // Import lớp Store cần test

public class StoreTest {

    public static void main(String[] args) {
        // 1. Tạo một đối tượng Store mới
        Store myStore = new Store();

        // 2. Tạo các đối tượng Media mẫu (DVD, CD, Book)
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Matrix", "Science Fiction", "Wachowski", 136, 15.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Interstellar", "Science Fiction", "Christopher Nolan", 169, 22.50f);
        // Tạo CD với Tracks
        CompactDisc cd1 = new CompactDisc("Dark Side of the Moon", "Progressive Rock", 18.50f, "Pink Floyd");
        cd1.addTrack(new Track("Speak to Me/Breathe", 4));
        cd1.addTrack(new Track("Time", 7));
        cd1.addTrack(new Track("Money", 6));
        // Tạo Book với Authors
        Book book1 = new Book("Dune", "Science Fiction", 20.00f);
        book1.addAuthor("Frank Herbert");
        Book book2 = new Book("1984", "Dystopian", 12.50f);
        book2.addAuthor("George Orwell");

        // 3. Kiểm tra phương thức addMedia()
        System.out.println("--- Testing addMedia() ---");
        myStore.addMedia(dvd1);
        myStore.addMedia(cd1);
        myStore.addMedia(book1);
        myStore.addMedia(dvd2); // Thêm DVD thứ 2
        myStore.addMedia(book2); // Thêm Book thứ 2
        // Thử thêm lại media đã có (dựa trên equals của Media - theo title)
        System.out.println("\nAttempting to add existing media (Dune):");
        myStore.addMedia(new Book("Dune", "Sci-Fi", 19.0f)); // Title giống, nên không được thêm

        // 4. In nội dung Store để kiểm tra
        System.out.println("\n--- Store Contents after Adding ---");
        myStore.printStore();

        // 5. Kiểm tra phương thức removeMedia()
        System.out.println("\n--- Testing removeMedia() ---");
        // Tìm media cần xóa trước (ví dụ: xóa "Interstellar")
        // Lưu ý: removeMedia hoạt động dựa trên equals() (theo title)
        Media mediaToRemove = new DigitalVideoDisc("Interstellar"); // Chỉ cần title để so sánh
        System.out.println("\nAttempting to remove 'Interstellar':");
        myStore.removeMedia(mediaToRemove);

        // Thử xóa media không tồn tại
        System.out.println("\nAttempting to remove non-existent media ('Avatar'):");
        myStore.removeMedia(new DigitalVideoDisc("Avatar"));

        // 6. In lại nội dung Store sau khi xóa
        System.out.println("\n--- Store Contents after Removing ---");
        myStore.printStore();

         // 7. Kiểm tra findMediaByTitle()
        System.out.println("\n--- Testing findMediaByTitle() ---");
        String titleToFind = "Dark Side of the Moon";
        System.out.println("Finding '" + titleToFind + "':");
        Media found = myStore.findMediaByTitle(titleToFind);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("'" + titleToFind + "' not found.");
        }

        titleToFind = "Avatar";
        System.out.println("\nFinding '" + titleToFind + "':");
        found = myStore.findMediaByTitle(titleToFind);
         if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("'" + titleToFind + "' not found.");
        }

        System.out.println("\n--- Store Test Completed ---");
    }
}