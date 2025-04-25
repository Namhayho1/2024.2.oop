package hust.soict.hespi.aims.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Lớp CompactDisc đại diện cho một đĩa CD, kế thừa từ Disc.
 * Chứa thông tin về nghệ sĩ và danh sách các bài hát (Track).
 * Implement Playable.
 */
public class CompactDisc extends Disc implements Playable {

    private String artist;
    private List<Track> tracks = new ArrayList<>();

    // --- Constructors ---
     public CompactDisc(String title, String category, float cost, String artist) {
         super(title, category, cost, 0, null); // director mặc định null, length ban đầu 0
         this.artist = artist;
     }

     public CompactDisc(String title, String category, float cost, String director, String artist) {
         super(title, category, cost, 0, director); // length ban đầu là 0
         this.artist = artist;
     }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost, 0, director); // length ban đầu là 0
        this.artist = artist;
    }

    // --- Getter for artist ---
    public String getArtist() {
        return artist;
    }

    // --- Track Management ---
    public boolean addTrack(Track track) {
        if (track != null && !tracks.contains(track)) { // contains() dùng equals() mới của Track
            tracks.add(track);
            // System.out.println("Added track '" + track.getTitle() + "' to CD '" + this.getTitle() + "'.");
            return true;
        }
        // Bỏ bớt println để tránh rối console, có thể trả về lý do tại sao false nếu cần
        return false;
    }

    public boolean removeTrack(Track track) {
         if (track != null) {
            return tracks.remove(track); // remove() dùng equals() mới của Track
         }
         return false;
    }

     // --- Getter for tracks (optional, returns unmodifiable list) ---
     public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
     }


    // --- getLength() Override ---
    @Override
    public int getLength() {
        int totalLength = 0;
        if (tracks != null) {
            for (Track track : tracks) {
                totalLength += track.getLength();
            }
        }
        return totalLength;
    }

    // --- play() Override ---
    @Override
    public void play() {
        if (tracks != null && !tracks.isEmpty() && this.getLength() > 0) {
            System.out.println("=========================================");
            System.out.println("Playing CD: " + this.getTitle());
            System.out.println("Artist: " + this.getArtist());
            System.out.println("Total Length: " + this.getLength() + " mins");
            System.out.println("--- Tracks ---");
            Iterator<Track> iterator = tracks.iterator();
            int trackNumber = 1;
            while (iterator.hasNext()) {
                 Track nextTrack = iterator.next();
                 System.out.print(trackNumber + ". ");
                 nextTrack.play();
                 trackNumber++;
            }
             System.out.println("=========================================");
        } else {
             System.out.println("Cannot play CD: '" + this.getTitle() + "'. No playable tracks or length is zero.");
        }
    }

    // --- toString() Override ---
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CD   - "); // Thêm khoảng trắng cho dễ nhìn
        sb.append("ID: ").append(getId()).append(" - ");
        sb.append("Title: ").append(getTitle() != null ? getTitle() : "[No Title]").append(" - ");
        sb.append("Category: ").append(getCategory() != null ? getCategory() : "[No Category]").append(" - ");
        sb.append("Director: ").append(getDirector() != null ? getDirector() : "N/A").append(" - "); // Director có thể N/A
        sb.append("Artist: ").append(getArtist() != null ? getArtist() : "[No Artist]").append(" - ");
        sb.append("Length: ").append(getLength()).append(" mins - ");
        sb.append("Cost: ").append(getCost()).append(" $");
        if (!tracks.isEmpty()) {
            sb.append("\n     Tracks:"); // Thụt vào để dễ đọc
            for (int i = 0; i < tracks.size(); i++) {
                sb.append("\n       ").append(i + 1).append(". ").append(tracks.get(i).toString());
            }
        }
        return sb.toString();
    }
}