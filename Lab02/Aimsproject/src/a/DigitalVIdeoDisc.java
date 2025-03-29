package a;

public class DigitalVIdeoDisc {
	private String title;
	private String category;
	private String director;
	private int length;
	private float cost;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public float getCost() {
		return cost;
	}
	private static int nbDigitalVIdeoDisc=0;
	private int id;
	public int getId() {
	        return id;
	    }
	public static int getNbDigitalVideoDiscs() {
	        return nbDigitalVIdeoDisc;
	    }
	public DigitalVIdeoDisc(String title) {
		super();
		this.title = title;
	}
	public DigitalVIdeoDisc(String title, String category, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	public DigitalVIdeoDisc(String title, String category, String director, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.cost = cost;
	}
	public DigitalVIdeoDisc(String title, String category, String director, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}
	
	
	
}
