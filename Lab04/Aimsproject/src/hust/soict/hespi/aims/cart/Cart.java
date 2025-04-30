package hust.soict.hespi.aims.cart; // Sửa lại package name cho phù hợp (bỏ .Cart ở cuối)

// Import các lớp cần thiết
import hust.soict.hespi.aims.media.Media; // Import lớp cha Media
// Không cần import DigitalVideoDisc nữa trừ khi dùng ở đâu đó rất cụ thể
import java.util.ArrayList;              // Import ArrayList
import java.util.List;                   // Import List (nên dùng List làm kiểu khai báo)
public class Cart {

    private List<Media> itemsOrdered = new ArrayList<>();

    public Cart() {
        super();
    }

    /**
     * Thêm một đối tượng Media vào giỏ hàng. Tránh trùng lặp dựa trên equals() của Media (theo title).
     * @param media Đối tượng Media cần thêm.
     * @return true nếu thêm thành công, false nếu media là null hoặc đã tồn tại.
     */
    public boolean addMedia(Media media) {
        if (media != null) {
            if (itemsOrdered.contains(media)) { // contains() dùng equals() mới của Media (so sánh title)
                System.out.println("Warning: Media with title '" + media.getTitle() + "' is already in the cart.");
                return false;
            } else {
                itemsOrdered.add(media);
                // System.out.println("The media '" + media.getTitle() + "' has been added."); // Có thể bỏ bớt để đỡ rối
                return true;
            }
        }
        System.out.println("Error: Cannot add null media to the cart.");
        return false;
    }

    /**
     * Xóa một đối tượng Media khỏi giỏ hàng dựa trên equals() của Media (theo title).
     * @param media Đối tượng Media cần xóa.
     * @return true nếu xóa thành công, false nếu media là null hoặc không tìm thấy.
     */
    public boolean removeMedia(Media media) {
        if (media != null) {
            boolean removed = itemsOrdered.remove(media); // Dùng remove của ArrayList, dựa trên equals() mới
            if (removed) {
                 // System.out.println("The media '" + media.getTitle() + "' has been removed."); // Bỏ bớt
                return true;
            } else {
                System.out.println("Error: Media with title '" + media.getTitle() + "' was not found in the cart.");
                return false;
            }
        }
        System.out.println("Error: Cannot remove null media from the cart.");
        return false;
    }

    /**
     * Tính tổng chi phí của tất cả các Media trong giỏ hàng.
     * @return Tổng chi phí.
     */
    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
             if (media != null) {
                total += media.getCost();
             }
        }
        return total;
    }

    /**
     * In nội dung của giỏ hàng ra console.
     */
    public void printCart() {
        System.out.println("\n*********************** CART ***********************");
        if (itemsOrdered.isEmpty()) {
            System.out.println("Ordered Items: The cart is empty.");
        } else {
            System.out.println("Ordered Items:");
            int itemNumber = 1;
            for (Media media : itemsOrdered) {
                 if (media != null) {
                    System.out.println(itemNumber + ". " + media.toString()); // Gọi toString() đa hình
                    itemNumber++;
                 }
            }
             System.out.printf("Total cost: %.2f $\n", totalCost());
        }
        System.out.println("***************************************************");
    }

    /**
     * Tìm kiếm Media trong giỏ hàng dựa trên ID.
     * @param idToSearch ID cần tìm.
     * @return Media tìm thấy hoặc null nếu không tìm thấy.
     */
     public Media searchById(int idToSearch) {
            System.out.println("\n--- Searching Cart for Media with ID: " + idToSearch + " ---");
            for (Media media : itemsOrdered) {
                if (media != null && media.getId() == idToSearch) {
                    System.out.println("Found: " + media.toString());
                    return media; // Trả về đối tượng tìm thấy
                }
            }
            System.out.println("No Media found with ID: " + idToSearch);
            return null; // Không tìm thấy
        }

    /**
     * Tìm kiếm Media trong giỏ hàng dựa trên tiêu đề (không phân biệt hoa thường).
     * @param titleToSearch Tiêu đề cần tìm.
     * @return Danh sách các Media tìm thấy (có thể rỗng).
     */
    public List<Media> searchByTitle(String titleToSearch) {
        List<Media> results = new ArrayList<>();
        System.out.println("\n--- Searching Cart for Media matching title: \"" + titleToSearch + "\" ---");
        if (titleToSearch == null || titleToSearch.trim().isEmpty()) {
             System.out.println("Search title cannot be empty.");
             return results; // Trả về danh sách rỗng
        }

        for (Media media : itemsOrdered) {
            if (media != null && media.getTitle() != null && media.getTitle().equalsIgnoreCase(titleToSearch)) {
                System.out.println("Found: " + media.toString());
                results.add(media); // Thêm vào danh sách kết quả
            }
        }
        if (results.isEmpty()) {
            System.out.println("No Media found matching title: \"" + titleToSearch + "\"");
        }
        return results; // Trả về danh sách kết quả
    }

     // --- Sorting Methods (Yêu cầu 17) ---

     /**
      * Sắp xếp các mục trong giỏ hàng theo tiêu đề (tăng dần) rồi đến giá (giảm dần).
      */
     public void sortByTitle() {
         if (itemsOrdered != null) {
             // Sử dụng Comparator tĩnh từ lớp Media
             itemsOrdered.sort(Media.COMPARE_BY_TITLE_COST);
             System.out.println("Cart sorted by title (then cost descending).");
         }
     }

     /**
      * Sắp xếp các mục trong giỏ hàng theo giá (giảm dần) rồi đến tiêu đề (tăng dần).
      */
     public void sortByCost() {
         if (itemsOrdered != null) {
              // Sử dụng Comparator tĩnh từ lớp Media
             itemsOrdered.sort(Media.COMPARE_BY_COST_TITLE);
              System.out.println("Cart sorted by cost (descending, then title).");
         }
     }

      // --- Optional helper methods ---
      /**
       * Lấy danh sách các mục đã đặt hàng (trả về bản sao để tránh sửa đổi trực tiếp).
       * @return Một bản sao của danh sách các Media trong giỏ hàng.
       */
      public List<Media> getItemsOrdered() {
          return new ArrayList<>(itemsOrdered); // Trả về bản sao
      }

      /**
       * Làm rỗng giỏ hàng.
       */
      public void emptyCart() {
          itemsOrdered.clear();
          System.out.println("Cart has been emptied.");
      }

      /**
       * Lấy số lượng mặt hàng trong giỏ.
       * @return số lượng mặt hàng.
       */
      public int getItemCount() {
          return itemsOrdered.size();
      }
}
