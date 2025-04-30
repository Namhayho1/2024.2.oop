package hust.soict.hespi.aims.media;

/**
 * Lớp Disc trừu tượng, kế thừa từ Media, thêm length và director.
 */
public abstract class Disc extends Media {

    private int length;
    private String director;

    // --- Constructors ---
    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(String title, String category, float cost, int length, String director) {
        super(title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Disc(String title) {
        super(title);
    }

    public Disc() {
        super();
    }

    // --- Getters ---
    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    // --- Setters ---
    protected void setLength(int length) { // Protected
        this.length = length;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}