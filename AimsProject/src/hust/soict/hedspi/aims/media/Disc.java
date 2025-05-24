package hust.soict.hedspi.aims.media;

public abstract class Disc extends Media {
    private int length;
    protected String director;
    
    // Constructors
    public Disc() {
        // Default constructor
    }
    
    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }
    
    // Getters
    public int getLength() {
        return length;
    }
    
    public String getDirector() {
        return director;
    }

	public void display() {
		// TODO Auto-generated method stub
		
	}
}