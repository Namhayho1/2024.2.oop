package hust.soict.hespi.aims.Aims; // Đổi package thành package gốc của Aims

import hust.soict.hespi.aims.cart.Cart; // Import lớp Cart từ đúng package
import hust.soict.hespi.aims.media.*; // Import tất cả các lớp trong package media
import hust.soict.hespi.aims.store.Store; // Import lớp Store từ đúng package

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Aims {

    private static Store store = new Store(); // Tạo đối tượng Store
    private static Cart cart = new Cart();   // Tạo đối tượng Cart
    private static Scanner scanner = new Scanner(System.in); // Scanner để đọc input

    public static void main(String[] args) {
        // --- Thêm một số dữ liệu mẫu vào Store ---
        addSampleData();
        // --- Kết thúc thêm dữ liệu mẫu ---

        int choice;
        do {
            showMenu(); // Hiển thị menu chính
            choice = getUserChoice(); // Lấy lựa chọn người dùng

            switch (choice) {
                case 1:
                    viewStore(); // Xem cửa hàng
                    break;
                case 2:
                    updateStore(); // Cập nhật cửa hàng
                    break;
                case 3:
                    viewCart(); // Xem giỏ hàng
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close(); // Đóng Scanner khi kết thúc chương trình
    }

    // ========== MENU CHÍNH ==========
    public static void showMenu() {
        System.out.println("\nAIMS Main Menu: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-3): ");
    }

    // ========== XEM CỬA HÀNG ==========
    public static void viewStore() {
        store.printStore(); // In các mặt hàng trong cửa hàng

        int storeChoice;
        do {
            storeMenu(); // Hiển thị menu của cửa hàng
            storeChoice = getUserChoice();

            switch (storeChoice) {
                case 1: // See details
                    seeMediaDetails();
                    break;
                case 2: // Add to cart
                    addMediaToCartFromStore();
                    break;
                case 3: // Play media
                    playMediaFromStore();
                    break;
                case 4: // See current cart
                    viewCart();
                    break;
                case 0: // Back
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice in store menu.");
            }
        } while (storeChoice != 0);
    }

    public static void storeMenu() {
        System.out.println("\nStore Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-4): ");
    }

    public static void seeMediaDetails() {
        System.out.print("Enter the title of the media to see details: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);

        if (media != null) {
            System.out.println("\n--- Media Details ---");
            System.out.println(media.toString()); // In thông tin chi tiết
            System.out.println("--------------------");
            mediaDetailsMenuLoop(media); // Hiển thị menu chi tiết media
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }

     public static void mediaDetailsMenuLoop(Media media) {
        int detailChoice;
        do {3
            // Kiểm tra xem media có Playable không để hiển thị đúng menu
            boolean canPlay = media instanceof Playable;
            mediaDetailsMenu(canPlay);
            detailChoice = getUserChoice();

            switch (detailChoice) {
                case 1: // Add to cart
                    cart.addMedia(media);
                    System.out.println("Current items in cart: " + cart.getItemCount());
                    break;
                case 2: // Play
                    if (canPlay) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media type cannot be played.");
                        // Nếu lựa chọn 2 không hợp lệ thì không làm gì cả, chờ nhập lại
                        detailChoice = -1; // Đặt lại để vòng lặp tiếp tục nếu chọn 2 mà không play được
                    }
                    break;
                case 0: // Back
                    System.out.println("Returning to store menu...");
                    break;
                default:
                    System.out.println("Invalid choice in media details menu.");
            }
             // Chỉ thoát vòng lặp nếu chọn 0 hoặc nếu chọn 2 mà không thể play (để tránh kẹt)
        } while (detailChoice != 0 && !(detailChoice == 2 && ! (media instanceof Playable)));
    }


    public static void mediaDetailsMenu(boolean showPlayOption) {
        System.out.println("\nMedia Details Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (showPlayOption) {
            System.out.println("2. Play");
        }
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-1" + (showPlayOption ? "-2" : "") + "): ");
    }

    public static void addMediaToCartFromStore() {
        System.out.print("Enter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Current items in cart: " + cart.getItemCount());
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }

     public static void playMediaFromStore() {
        System.out.print("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            } else {
                System.out.println("This media type (" + media.getClass().getSimpleName() + ") cannot be played.");
            }
        } else {
            System.out.println("Media with title '" + title + "' not found in store.");
        }
    }


    // ========== CẬP NHẬT CỬA HÀNG ==========
    public static void updateStore() {
        System.out.println("\nUpdate Store Options:");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
        int updateChoice = getUserChoice();

        switch (updateChoice) {
            case 1:
                // --- Add Media (Đơn giản hóa - chỉ thêm item mẫu) ---
                System.out.println("Note: Add Media functionality is basic in this version.");
                // Ví dụ: Thêm một cuốn sách mới
                Book newBook = new Book("Sample New Book", "Fiction", 15.5f);
                newBook.addAuthor("Jane Doe");
                store.addMedia(newBook);
                 System.out.println("Added '"+ newBook.getTitle() + "' to the store.");
                break;
            case 2:
                // --- Remove Media ---
                System.out.print("Enter the title of the media to remove: ");
                String titleToRemove = scanner.nextLine();
                Media mediaToRemove = store.findMediaByTitle(titleToRemove); // Tìm media trong store
                if (mediaToRemove != null) {
                    store.removeMedia(mediaToRemove); // Xóa khỏi store
                     System.out.println("Removed '" + mediaToRemove.getTitle() + "' from the store.");
                } else {
                    System.out.println("Media with title '" + titleToRemove + "' not found in store.");
                }
                break;
            case 0:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice in update store menu.");
        }
    }


    // ========== XEM GIỎ HÀNG ==========
    public static void viewCart() {
        cart.printCart(); // In giỏ hàng hiện tại

        int cartChoice;
        do {
            cartMenu(); // Hiển thị menu của giỏ hàng
            cartChoice = getUserChoice();

            switch (cartChoice) {
                case 1: // Filter
                    filterCart();
                    break;
                case 2: // Sort
                    sortCart();
                    break;
                case 3: // Remove
                    removeMediaFromCart();
                    break;
                case 4: // Play
                    playMediaFromCart();
                    break;
                case 5: // Place Order
                    System.out.println("Order placed successfully!");
                    cart.emptyCart(); // Làm rỗng giỏ hàng
                    cartChoice = 0; // Tự động quay lại menu chính sau khi đặt hàng
                    break;
                case 0: // Back
                     System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice in cart menu.");
            }
        } while (cartChoice != 0);
    }

    public static void cartMenu() {
        System.out.println("\nCart Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-5): ");
    }

     public static void filterCart() {
        System.out.println("\nFilter Options:");
        System.out.println("1. By ID");
        System.out.println("2. By Title");
        System.out.println("0. Back");
        System.out.print("Choose filter type: ");
        int filterChoice = getUserChoice();

        switch (filterChoice) {
            case 1:
                System.out.print("Enter ID to filter by: ");
                int id = getUserChoice(); // Dùng lại hàm đọc số
                cart.searchById(id); // Hàm searchById trong Cart đã in kết quả
                break;
            case 2:
                System.out.print("Enter Title to filter by: ");
                String title = scanner.nextLine();
                cart.searchByTitle(title); // Hàm searchByTitle trong Cart đã in kết quả
                break;
            case 0:
                 break; // Quay lại menu giỏ hàng
            default:
                System.out.println("Invalid filter choice.");
        }
    }

    public static void sortCart() {
        System.out.println("\nSort Options:");
        System.out.println("1. By Title (then Cost descending)");
        System.out.println("2. By Cost (descending, then Title)");
        System.out.println("0. Back");
         System.out.print("Choose sort type: ");
        int sortChoice = getUserChoice();

        switch (sortChoice) {
            case 1:
                cart.sortByTitle();
                cart.printCart(); // In lại giỏ hàng đã sắp xếp
                break;
            case 2:
                cart.sortByCost();
                cart.printCart(); // In lại giỏ hàng đã sắp xếp
                break;
             case 0:
                 break; // Quay lại menu giỏ hàng
            default:
                System.out.println("Invalid sort choice.");
        }
    }

    public static void removeMediaFromCart() {
        System.out.print("Enter the title of the media to remove from cart: ");
        String title = scanner.nextLine();
        // Tìm kiếm trước khi xóa vì removeMedia cần đối tượng Media
        List<Media> foundMediaList = cart.searchByTitle(title); // Tìm tất cả các media khớp tên

        if (foundMediaList.isEmpty()) {
            // Đã có thông báo từ searchByTitle
        } else if (foundMediaList.size() == 1) {
            // Chỉ tìm thấy 1, xóa luôn
            cart.removeMedia(foundMediaList.get(0));
             System.out.println("Removed '" + title + "' from cart.");
        } else {
            // Tìm thấy nhiều, yêu cầu chọn bằng ID
            System.out.println("Multiple items found with that title. Please remove by ID.");
            System.out.print("Enter the ID of the item to remove: ");
            int idToRemove = getUserChoice();
            Media mediaToRemove = cart.searchById(idToRemove); // Tìm bằng ID
            if(mediaToRemove != null) {
                 cart.removeMedia(mediaToRemove); // Xóa đối tượng cụ thể tìm được bằng ID
                 System.out.println("Removed item with ID " + idToRemove + " from cart.");
            }
            // Thông báo không tìm thấy ID đã được xử lý trong searchById
        }
    }

     public static void playMediaFromCart() {
        System.out.print("Enter the title of the media to play from cart: ");
        String title = scanner.nextLine();
        List<Media> foundMediaList = cart.searchByTitle(title); // Tìm kiếm trong giỏ hàng

         if (foundMediaList.isEmpty()) {
             // Thông báo không tìm thấy đã có
         } else if (foundMediaList.size() == 1) {
             // Chỉ có 1, kiểm tra và phát
             Media mediaToPlay = foundMediaList.get(0);
             if (mediaToPlay instanceof Playable) {
                 ((Playable) mediaToPlay).play();
             } else {
                 System.out.println("This media type (" + mediaToPlay.getClass().getSimpleName() + ") cannot be played.");
             }
         } else {
              // Tìm thấy nhiều, yêu cầu chọn ID
             System.out.println("Multiple items found with that title. Please select by ID to play.");
             System.out.print("Enter the ID of the item to play: ");
             int idToPlay = getUserChoice();
             Media mediaToPlay = cart.searchById(idToPlay); // Tìm bằng ID
             if (mediaToPlay != null) {
                  if (mediaToPlay instanceof Playable) {
                     ((Playable) mediaToPlay).play();
                 } else {
                     System.out.println("This media type (" + mediaToPlay.getClass().getSimpleName() + ") cannot be played.");
                 }
             }
             // Thông báo không tìm thấy ID đã có
         }
    }


    // ========== HÀM HỖ TRỢ ==========
    /**
     * Đọc lựa chọn số nguyên từ người dùng, xử lý lỗi nhập sai.
     * @return Lựa chọn của người dùng hoặc -1 nếu nhập không hợp lệ.
     */
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            // Xử lý lỗi ở đây nếu cần
        } finally {
             scanner.nextLine(); // Luôn đọc dòng mới sau khi đọc số
        }
        return choice;
    }

     /**
      * Thêm dữ liệu mẫu vào cửa hàng để demo.
      */
     private static void addSampleData() {
        System.out.println("Initializing sample data...");

        // DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 120, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Inception", "Science Fiction", "Christopher Nolan", 148, 22.50f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        // CDs
        CompactDisc cd1 = new CompactDisc("Thriller", "Pop", 9.99f, "Michael Jackson");
        Track track1_cd1 = new Track("Wanna Be Startin' Somethin'", 6);
        Track track2_cd1 = new Track("Thriller", 6);
        Track track3_cd1 = new Track("Beat It", 4);
        cd1.addTrack(track1_cd1);
        cd1.addTrack(track2_cd1);
        cd1.addTrack(track3_cd1);
        store.addMedia(cd1);

        CompactDisc cd2 = new CompactDisc("Abbey Road", "Rock", 12.50f, "The Beatles");
        cd2.addTrack(new Track("Come Together", 4));
        cd2.addTrack(new Track("Something", 3));
        cd2.addTrack(new Track("Here Comes the Sun", 3));
        store.addMedia(cd2);

        // Books
        Book book1 = new Book("The Lord of the Rings", "Fantasy", 25.00f);
        book1.addAuthor("J.R.R. Tolkien");
        store.addMedia(book1);

        Book book2 = new Book("Pride and Prejudice", "Romance", 14.95f);
        book2.addAuthor("Jane Austen");
        store.addMedia(book2);

         Book book3 = new Book("The Hitchhiker's Guide to the Galaxy", "Science Fiction", 18.00f);
        book3.addAuthor("Douglas Adams");
        store.addMedia(book3);

        System.out.println("Sample data added.");
     }
}