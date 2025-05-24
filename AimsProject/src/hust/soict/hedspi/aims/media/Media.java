package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media implements Comparable<Media> {

    protected int id;
    protected String title;
    protected String category;
    protected Float cost;

    // Thêm comparators làm thuộc tính static final
    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            Comparator.comparing(Media::getTitle)
                    .thenComparing(Media::getCost);
    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            Comparator.comparing(Media::getCost)
                    .thenComparing(Media::getTitle);

    // Constructor
    public Media() {}

    public Media(int id, String title, String category, float cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative: " + cost);
        }
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters
    public int getId()     { return id; }
    public String getTitle()  { return title; }
    public String getCategory() { return category; }
    public Float getCost()    { return cost; }

    // Abstract methods
    public abstract void display();

    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Media))
            return false;  // tránh ClassCastException
        Media other = (Media) obj;
        // so sánh cả title và cost, xử lý null an toàn
        return Objects.equals(this.title, other.title)
                && Objects.equals(this.cost,  other.cost);
    }

    @Override
    public int hashCode() {
        // Phải override hashCode khi override equals
        return Objects.hash(title, cost);
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        // So sánh theo title
        int cmp = this.title.compareTo(other.title);
        if (cmp != 0) return cmp;
        // nếu title bằng, so sánh cost
        return this.cost.compareTo(other.cost);
    }
}