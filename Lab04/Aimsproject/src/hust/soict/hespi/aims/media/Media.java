package hust.soict.hespi.aims.media;

import java.util.Comparator; // Import Comparator
import java.util.Objects;

/**
 * Lớp trừu tượng Media là lớp cơ sở cho tất cả các loại sản phẩm media
 * trong cửa hàng AIMS. Chứa các thuộc tính chung.
 * Equals được định nghĩa dựa trên Title theo yêu cầu 15.
 */
public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    private static int nbMedia = 0;

    // Comparators theo yêu cầu 17
    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
                                           new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
                                           new MediaComparatorByCostTitle();

    // --- Constructors ---
    public Media() {
        super();
        this.id = ++nbMedia;
    }

    public Media(String title) {
        this();
        this.title = title;
    }
     public Media(String title, String category) {
        this(title);
        this.category = category;
    }

    public Media(String title, String category, float cost) {
        this(title, category);
        this.cost = cost;
    }

    public Media(int id, String title, String category, float cost) {
        super();
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
        if (id > nbMedia) {
            nbMedia = id;
        }
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }

    // --- equals() and hashCode() - THEO YÊU CẦU 15 (Dựa trên Title) ---
    /**
     * So sánh hai đối tượng Media dựa trên Title (không phân biệt hoa thường).
     * @param o Đối tượng cần so sánh.
     * @return true nếu title bằng nhau (bỏ qua hoa/thường), false nếu khác.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        Media media = (Media) o;
        // So sánh chỉ dựa trên title, không phân biệt hoa thường, null safe
        if (this.getTitle() == null) {
            return media.getTitle() == null;
        }
        return this.getTitle().equalsIgnoreCase(media.getTitle());
    }

    /**
     * Trả về mã hash cho đối tượng Media dựa trên Title (chữ thường).
     * Phải nhất quán với phương thức equals().
     * @return Mã hash.
     */
    @Override
    public int hashCode() {
        // Dựa trên trường được sử dụng trong equals() - title (chuyển về chữ thường để nhất quán với equalsIgnoreCase)
         return Objects.hash( (getTitle() == null) ? null : getTitle().toLowerCase() );
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "Media [ID=" + getId() + ", Title=" + getTitle() +
               ", Category=" + getCategory() + ", Cost=" + getCost() + "$]";
    }
}
