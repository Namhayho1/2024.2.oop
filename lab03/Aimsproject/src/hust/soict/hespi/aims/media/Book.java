package hust.soict.hespi.aims.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lớp Book đại diện cho một cuốn sách, kế thừa từ Media.
 */
public class Book extends Media {

    private List<String> authors = new ArrayList<>();

    // --- Constructors ---
     public Book() {
        super();
    }

    public Book(String title) {
        super(title);
    }
     public Book(String title, String category) {
        super(title, category);
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

     public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    // --- Author Management ---
    public boolean addAuthor(String authorName) {
        if (authorName != null && !authorName.isEmpty() && !authors.contains(authorName)) {
            authors.add(authorName);
            return true;
        }
        return false;
    }

    public boolean removeAuthor(String authorName) {
        if (authorName != null) {
            return authors.remove(authorName);
        }
        return false;
    }

    // --- Getter for authors ---
    public List<String> getAuthors() {
        // Trả về bản sao không thể sửa đổi để bảo vệ danh sách gốc
        return Collections.unmodifiableList(authors);
    }

    // --- toString() ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book - ");
        sb.append("ID: ").append(getId()).append(" - ");
        sb.append("Title: ").append(getTitle()).append(" - ");
        sb.append("Category: ").append(getCategory()).append(" - ");
        sb.append("Authors: ").append(authors.isEmpty() ? "[No Authors]" : String.join(", ", authors)).append(" - ");
        sb.append("Cost: ").append(getCost()).append(" $");
        return sb.toString();
    }

    // Book không implement Playable nên không có play()
}