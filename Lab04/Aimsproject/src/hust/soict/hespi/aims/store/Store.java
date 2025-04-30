package hust.soict.hespi.aims.store; // Package riêng cho Store (ví dụ)

import hust.soict.hespi.aims.media.Media; // Import lớp cha Media
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger; // Dùng AtomicInteger cho thread-safe ID generation (tốt hơn)

/**
 * Lớp Store đại diện cho cửa hàng chứa danh sách các Media có sẵn.
 */
public class Store {

    private List<Media> itemsInStore = new ArrayList<>();
    // Biến static để tạo ID duy nhất, dùng AtomicInteger để an toàn trong môi trường đa luồng (nếu có)
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    public Store() {
        super();
    }

    /**
     * Tạo và trả về một ID duy nhất cho Media mới.
     * @return ID duy nhất tiếp theo.
     */
    public int generateId() {
        return idCounter.incrementAndGet(); // Tăng và lấy giá trị mới
    }

    /**
     * Thêm một đối tượng Media vào kho của cửa hàng.
     * @param media Đối tượng Media cần thêm.
     */
    public void addMedia(Media media) {
        if (media != null) {
            // Kiểm tra bằng equals (theo title) - có thể xem xét kiểm tra bằng ID nếu ID là duy nhất thực sự
            if (itemsInStore.contains(media)) {
                 System.out.println("Warning: Media with title '" + media.getTitle() + "' might already exist in the store (based on title).");
                 // Optional: Nếu muốn ID là duy nhất tuyệt đối, bạn có thể kiểm tra ID ở đây nữa.
                 // boolean idExists = itemsInStore.stream().anyMatch(m -> m.getId() == media.getId());
                 // if (idExists) { /* xử lý lỗi ID trùng */ }
                 itemsInStore.add(media); // Vẫn thêm nếu chỉ cảnh báo theo title
            } else {
                itemsInStore.add(media);
                // System.out.println("Added '" + media.getTitle() + "' to the store.");
            }
        } else {
            System.out.println("Error: Cannot add null media to the store.");
        }
    }

    /**
     * Xóa một đối tượng Media khỏi kho của cửa hàng dựa trên đối tượng (dùng equals - title).
     * @param media Đối tượng Media cần xóa.
     */
    public void removeMedia(Media media) {
        if (media != null) {
            boolean removed = itemsInStore.remove(media); // Dùng equals của Media (theo title)
            if (removed) {
                // System.out.println("Removed media matching '" + media.getTitle() + "' from the store.");
            } else {
                 System.out.println("Error: Media matching title '" + media.getTitle() + "' not found in the store.");
            }
        } else {
             System.out.println("Error: Cannot remove null media from the store.");
        }
    }

     /**
     * Xóa một đối tượng Media khỏi kho của cửa hàng dựa trên ID.
     * @param id ID của Media cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy ID.
     */
    public boolean removeMediaById(int id) {
        boolean removed = itemsInStore.removeIf(media -> media != null && media.getId() == id);
        if (!removed) {
            System.out.println("Error: Media with ID " + id + " not found in the store.");
        }
        return removed;
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
            // Sắp xếp trước khi in cho đẹp (ví dụ theo Title) - Optional
            // itemsInStore.sort(Media.COMPARE_BY_TITLE_COST);
            for (Media media : itemsInStore) {
                 if (media != null) {
                    System.out.println(itemNumber + ". " + media.toString()); // Dùng toString đã override
                    itemNumber++;
                 }
            }
        }
        System.out.println("***************************************************");
    }

     /**
     * Tìm kiếm Media trong cửa hàng dựa trên tiêu đề (không phân biệt hoa thường).
     * @param title Tiêu đề cần tìm.
     * @return Media tìm thấy đầu tiên hoặc null nếu không tìm thấy.
     */
    public Media findMediaByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        String titleLower = title.toLowerCase(); // Tìm kiếm hiệu quả hơn
        for (Media media : itemsInStore) {
            if (media != null && media.getTitle() != null && media.getTitle().toLowerCase().contains(titleLower)) { // Có thể dùng contains thay vì equalsIgnoreCase nếu muốn tìm gần đúng
            // Hoặc dùng equalsIgnoreCase nếu muốn khớp chính xác:
            // if (media != null && media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                return media; // Trả về media đầu tiên tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    /**
     * Tìm kiếm Media trong cửa hàng dựa trên ID.
     * @param id ID cần tìm.
     * @return Media tìm thấy hoặc null nếu không tìm thấy.
     */
    public Media findMediaById(int id) {
       for (Media media : itemsInStore) {
           if (media != null && media.getId() == id) {
               return media;
           }
       }
       return null; // Không tìm thấy
    }


    /**
     * Lấy danh sách các Media hiện có trong Store.
     * Trả về một bản sao (shallow copy) của danh sách để tránh sửa đổi trực tiếp từ bên ngoài.
     * @return Một List<Media> mới chứa các media trong store.
     */
     public List<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore); // Trả về bản sao
    }
}