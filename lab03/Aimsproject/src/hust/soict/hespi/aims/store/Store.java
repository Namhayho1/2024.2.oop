package hust.soict.hespi.aims.store; // Package riêng cho Store (ví dụ)

import hust.soict.hespi.aims.media.Media; // Import lớp cha Media
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Store đại diện cho cửa hàng chứa danh sách các Media có sẵn.
 */
public class Store {

    private List<Media> itemsInStore = new ArrayList<>();

    public Store() {
        super();
    }

    /**
     * Thêm một đối tượng Media vào kho của cửa hàng.
     * @param media Đối tượng Media cần thêm.
     */
    public void addMedia(Media media) {
        if (media != null) {
            if (itemsInStore.contains(media)) { // Dùng equals của Media (theo title)
                 System.out.println("Warning: Media with title '" + media.getTitle() + "' already exists in the store.");
            } else {
                itemsInStore.add(media);
                // System.out.println("Added '" + media.getTitle() + "' to the store.");
            }
        } else {
            System.out.println("Error: Cannot add null media to the store.");
        }
    }

    /**
     * Xóa một đối tượng Media khỏi kho của cửa hàng.
     * @param media Đối tượng Media cần xóa.
     */
    public void removeMedia(Media media) {
        if (media != null) {
            boolean removed = itemsInStore.remove(media); // Dùng equals của Media (theo title)
            if (removed) {
                // System.out.println("Removed '" + media.getTitle() + "' from the store.");
            } else {
                 System.out.println("Error: Media with title '" + media.getTitle() + "' not found in the store.");
            }
        } else {
             System.out.println("Error: Cannot remove null media from the store.");
        }
    }

    /**
     * In danh sách các Media hiện có trong cửa hàng.
     */
    public void printStore() {
         System.out.println("\n********************** STORE **********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("Available Items: The store is empty.");
        } else {
            System.out.println("Available Items:");
            int itemNumber = 1;
            for (Media media : itemsInStore) {
                 if (media != null) {
                    System.out.println(itemNumber + ". " + media.toString());
                    itemNumber++;
                 }
            }
        }
        System.out.println("***************************************************");
    }

     /**
     * Tìm kiếm Media trong cửa hàng dựa trên tiêu đề.
     * @param title Tiêu đề cần tìm.
     * @return Media tìm thấy hoặc null nếu không tìm thấy (chỉ trả về cái đầu tiên khớp).
     */
    public Media findMediaByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        for (Media media : itemsInStore) {
            if (media != null && media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                return media; // Trả về media đầu tiên tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    // Có thể thêm các phương thức khác như tìm theo ID, lấy danh sách items...
     public List<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore); // Trả về bản sao
    }
}
