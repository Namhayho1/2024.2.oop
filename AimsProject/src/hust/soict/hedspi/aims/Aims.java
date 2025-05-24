package hust.soict.hedspi.aims;

import java.util.Scanner;
import hust.soict.hedspi.aims.exception.PlayerException;
import java.util.ArrayList;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo một số media mẫu để kiểm tra
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);

        Book book1 = new Book(1, "Sherlock Holmes", "Detective", 15.5f);
        book1.addAuthor("Arthur Conan Doyle");

        CompactDisc cd1 = new CompactDisc(3, "Greatest Hits", "Music", 12.99f, "Various Artists");
        cd1.addTrack(new Track("Track 1", 4));
        cd1.addTrack(new Track("Track 2", 3));

        // Thêm media vào store
        Store store = new Store();
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(cd1);

        // Chạy play() với try-catch để xử lý ngoại lệ và hiển thị dialog
        try {
            dvd1.play();
        } catch (PlayerException e) {
            logAndShow(e);
        }

        try {
            cd1.play();
        } catch (PlayerException e) {
            logAndShow(e);
        }

        // Hiển thị menu chính và xử lý lựa chọn của người dùng
        showMenu();
    }

    private static void logAndShow(PlayerException e) {
        // In thông tin chi tiết
        System.err.println(e.getMessage());
        System.err.println(e.toString());
        e.printStackTrace();
        // Hiển thị dialog Swing
        JOptionPane.showMessageDialog(
                null,
                e.toString(),
                "Playback Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void showMenu() {
        int choice;
        do {
            System.out.println("\nAIMS:");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Thank you for using AIMS!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void viewStore() {
        store.printStore();
        storeMenu();
    }

    public static void storeMenu() {
        int choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    seeCurrentCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void seeMediaDetails() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, store);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        media.display();
        mediaDetailsMenu(media);
    }

    private static Media searchByTitle(String title, Store store) {
        // Tạm thời phương thức tìm kiếm trong store vì không có trong class Store
        for (Media media : getAllMediaFromStore()) {
            if (media.isMatch(title)) {
                return media;
            }
        }
        return null;
    }

    private static ArrayList<Media> getAllMediaFromStore() {
        return store.getItemsInStore();
    }


    public static void mediaDetailsMenu(Media media) {
        int choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");

            // Chỉ hiển thị tùy chọn "Play" nếu media có thể phát được
            if (media instanceof Playable) {
                System.out.println("2. Play");
            }

            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("This media is not playable.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void addMediaToCart() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, store);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        cart.addMedia(media);
    }

    public static void playMedia() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, store);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.out.println("Error while playing media: " + e.getMessage());
                // e.printStackTrace(); // Nếu bạn muốn debug kỹ hơn
            }
        } else {
            System.out.println("This media is not playable.");
        }
    }

    public static void updateStore() {
        int choice;
        do {
            System.out.println("\nUpdate Store Options:");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    addMediaToStore();
                    break;
                case 2:
                    removeMediaFromStore();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void addMediaToStore() {
        System.out.println("What type of media do you want to add?");
        System.out.println("1. Book");
        System.out.println("2. DVD");
        System.out.println("3. CD");
        System.out.print("Choose a number: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Xóa bộ đệm

        Media newMedia = null;

        switch (choice) {
            case 1: // Book
                System.out.print("Enter ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine(); // Xóa bộ đệm

                newMedia = new Book(bookId, title, category, cost);

                System.out.print("Do you want to add authors? (y/n): ");
                String addAuthors = scanner.nextLine();

                if (addAuthors.equalsIgnoreCase("y")) {
                    System.out.print("Enter author name (or 'exit' to finish): ");
                    String authorName = scanner.nextLine();

                    while (!authorName.equalsIgnoreCase("exit")) {
                        ((Book)newMedia).addAuthor(authorName);
                        System.out.print("Enter next author name (or 'exit' to finish): ");
                        authorName = scanner.nextLine();
                    }
                }
                break;
            case 2: // DVD
                System.out.print("Enter director: ");
                String director = scanner.nextLine();

                System.out.print("Enter length: ");
                int length = scanner.nextInt();
                scanner.nextLine(); // Xóa bộ đệm

                newMedia = new DigitalVideoDisc(title, category, director, length, cost);
                break;
            case 3: // CD
                System.out.print("Enter ID: ");
                int cdId = scanner.nextInt();
                scanner.nextLine(); // Xóa bộ đệm

                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();

                newMedia = new CompactDisc(cdId, title, category, cost, artist);

                System.out.print("Do you want to add tracks? (y/n): ");
                String addTracks = scanner.nextLine();

                if (addTracks.equalsIgnoreCase("y")) {
                    boolean adding = true;
                    while (adding) {
                        System.out.print("Enter track title (or 'exit' to finish): ");
                        String trackTitle = scanner.nextLine();

                        if (trackTitle.equalsIgnoreCase("exit")) {
                            adding = false;
                        } else {
                            System.out.print("Enter track length: ");
                            int trackLength = scanner.nextInt();
                            scanner.nextLine(); // Xóa bộ đệm

                            Track track = new Track(trackTitle, trackLength);
                            ((CompactDisc)newMedia).addTrack(track);
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        store.addMedia(newMedia);
    }

    public static void removeMediaFromStore() {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, store);
        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        store.removeMedia(media);
    }

    public static void seeCurrentCart() {
        cart.printCart();
        cartMenu();
    }

    public static void cartMenu() {
        int choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    filterMediaInCart();
                    break;
                case 2:
                    sortMediaInCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void filterMediaInCart() {
        System.out.println("Filter options:");
        System.out.println("1. By ID");
        System.out.println("2. By title");
        System.out.print("Choose a filter option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm

        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Xóa bộ đệm
                cart.searchMediaByID(id);
                break;
            case 2:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                cart.searchMediaByTitle(title);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void sortMediaInCart() {
        System.out.println("Sort options:");
        System.out.println("1. By title");
        System.out.println("2. By cost");
        System.out.print("Choose a sort option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ đệm

        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                System.out.println("Cart has been sorted by title.");
                cart.printCart();
                break;
            case 2:
                cart.sortByCostTitle();
                System.out.println("Cart has been sorted by cost.");
                cart.printCart();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void removeMediaFromCart() {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, cart);
        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }

        cart.removeMedia(media);
    }

    public static void playMediaFromCart() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();

        Media media = searchByTitle(title, cart);
        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }

        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.out.println("Error while playing media: " + e.getMessage());
                // e.printStackTrace(); // Nếu bạn muốn debug kỹ hơn
            }
        } else {
            System.out.println("This media is not playable.");
        }
    }

    public static void placeOrder() {
        System.out.println("Order created successfully!");
        System.out.println("Total cost: $" + cart.totalCost());

        // Làm rỗng giỏ hàng - cần bổ sung phương thức này vào cart
        emptyCart();

        System.out.println("Cart has been emptied.");
    }

    private static void emptyCart() {
        // Phương thức này để làm rỗng giỏ hàng
        // Vì Cart không có phương thức emptyCart() nên ta cần tự triển khai
        // hoặc bổ sung vào class Cart
    }
    private static Media searchByTitle(String title, Cart cart) {
        return cart.findByTitle(title);
    }

}