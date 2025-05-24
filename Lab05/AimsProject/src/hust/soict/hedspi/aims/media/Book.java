package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    private ArrayList<String> authors = new ArrayList<String>();

    // Các hàm khởi tạo
    public Book() {
        // Hàm khởi tạo mặc định
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);  // Gọi hàm khởi tạo của lớp cha thay vì thiết lập trường trực tiếp
    }

    // Các phương thức để thêm và xóa tác giả
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }
    
    // Triển khai phương thức display() trừu tượng từ lớp Media
    @Override
    public void display() {
        System.out.println("Book ID: " + getId());
        System.out.println("Tiêu đề: " + getTitle());
        System.out.println("Thể loại: " + getCategory());
        System.out.println("Giá: $" + getCost());
        System.out.print("Tác giả: ");
        if (authors.size() == 0) {
            System.out.println("Không có tác giả");
        } else {
            System.out.println(String.join(", ", authors));
        }
    }
    @Override
    public String toString() {
        return String.format("Book  - %s - %s - %.2f $",
                             getTitle(), getCategory(), getCost());
    }

}