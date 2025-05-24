package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    // Constructors
    public CompactDisc() {
        super();
    }

    public CompactDisc(int id, String title, String category, float cost) {
        super();
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    public CompactDisc(int id, String title, String category, float cost, String artist) {
        super(id, title, category, cost, 0, "");
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
    }

    // Getter for artist
    public String getArtist() {
        return artist;
    }

    // Methods to add and remove tracks
    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track đã tồn tại trong danh sách!");
        } else {
            tracks.add(track);
            System.out.println("Track đã được thêm thành công.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track đã được xóa thành công.");
        } else {
            System.out.println("Track không tồn tại trong danh sách!");
        }
    }

    // Get the total length of the CD
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    // Implement the abstract display() method from Media
    @Override
    public void display() {
        System.out.println("CD - ID: " + getId());
        System.out.println("Tiêu đề: " + getTitle());
        System.out.println("Thể loại: " + getCategory());
        System.out.println("Nghệ sĩ: " + getArtist());
        System.out.println("Đạo diễn: " + getDirector());
        System.out.println("Độ dài: " + getLength() + " phút");
        System.out.println("Giá: " + getCost() + " $");

        System.out.println("Danh sách các track:");
        if (tracks.isEmpty()) {
            System.out.println("Không có track nào");
        } else {
            for (int i = 0; i < tracks.size(); i++) {
                Track track = tracks.get(i);
                System.out.println((i+1) + ". " + track.getTitle() + " - " + track.getLength() + " phút");
            }
        }
    }
//    public void play() {
//        System.out.println("Playing CD: " + this.getTitle());
//        System.out.println("CD length: " + this.getLength());
//        java.util.Iterator<Track> trackIterator = tracks.iterator();
//        while (trackIterator.hasNext()) {
//            Track track = trackIterator.next();
//            track.play();
//        }
//    }
    @Override
    public String toString() {
        // nếu bạn muốn show track list có thể bổ sung thêm
        int totalLength = this.getLength(); // hoặc tự tính tổng từ các track
        return String.format("CD    - %s - %s - %s - %d mins - %.2f $",
                getTitle(), getCategory(), getArtist(),
                totalLength, getCost());
    }
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle());
            System.out.println("CD length: " + this.getLength());
            for (Track t : tracks) {
                try {
                    t.play();
                } catch (PlayerException e) {
                    System.err.println("ERROR playing track on CD: " + t.getTitle());
                    throw e;  // delegate further
                }
            }
        } else {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("CD length must be positive: " + this.getLength());
        }
    }

}