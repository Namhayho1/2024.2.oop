package hust.soict.hespi.aims.media;

import java.util.Objects;

/**
 * Lớp Track đại diện cho một bài hát trong một CD.
 * Implement Playable. Equals dựa trên Title và Length theo yêu cầu 15.
 */
public class Track implements Playable {
    private String title;
    private int length;

    // --- Constructors ---
    public Track(String title, int length) {
        this.title = title;
        this.length = (length >= 0) ? length : 0;
    }

    public Track(String title) {
        this(title, 0);
    }

    // --- Getters ---
    public String getTitle() { return title; }
    public int getLength() { return length; }

    // --- equals() and hashCode() - THEO YÊU CẦU 15 (Dựa trên Title và Length) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        // So sánh cả title (không phân biệt hoa thường) và length
         boolean titleEquals;
         if (this.getTitle() == null) {
            titleEquals = (track.getTitle() == null);
         } else {
            titleEquals = this.getTitle().equalsIgnoreCase(track.getTitle());
         }
         return getLength() == track.getLength() && titleEquals;
    }

    @Override
    public int hashCode() {
        // Phải nhất quán với equals (dựa trên title chữ thường và length)
         String lowerCaseTitle = (getTitle() == null) ? null : getTitle().toLowerCase();
         return Objects.hash(lowerCaseTitle, getLength());
    }

    // --- play() method ---
    @Override
    public void play() {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle() + " - Length: " + this.getLength() + " mins");
        } else {
            System.out.println("Cannot play track: " + this.getTitle() + " - Invalid length ("+ this.getLength() + ").");
        }
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "Track: " + title + " (" + length + " mins)";
    }
}