package hust.soict.hespi.aims.media;

/**
 * Lớp DigitalVideoDisc đại diện cho một đĩa DVD, kế thừa từ Disc.
 * Implement Playable.
 */
public class DigitalVideoDisc extends Disc implements Playable {

    // --- Constructors ---
    // Gọi các constructor tương ứng của lớp cha (Disc)
    public DigitalVideoDisc(String title) {
        super(title);
    }
    public DigitalVideoDisc(String title, String category) {
        super(title,category);
    }
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, cost, 0, director); // length mặc định 0
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, length, director);
    }

     public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, length, director);
    }

    // --- play() method ---
    @Override // Triển khai từ Playable
    public void play() {
         if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength() + " mins");
         } else {
             System.out.println("Cannot play DVD: " + this.getTitle() + " - Invalid length (" + this.getLength() + ").");
         }
    }

    // --- toString() method ---
    @Override
    public String toString() {
        return String.format("DVD - ID: %d - Title: %s - Category: %s - Director: %s - Length: %d mins - Cost: %.2f $",
                getId(),
                getTitle() != null ? getTitle() : "[No Title]",
                getCategory() != null ? getCategory() : "[No Category]",
                getDirector() != null ? getDirector() : "[No Director]",
                getLength(),
                getCost());
    }

    // --- isMatch() method (giữ lại nếu cần) ---
    public boolean isMatch(String titleToSearch) {
        if (this.getTitle() == null || titleToSearch == null) {
            return false;
        }
        return this.getTitle().equalsIgnoreCase(titleToSearch);
    }
}

