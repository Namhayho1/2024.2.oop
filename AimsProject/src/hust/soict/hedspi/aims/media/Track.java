package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    // Constructor
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Track)) return false;
        Track track = (Track) obj;
        return this.title.equals(track.title) && this.length == track.length;
    }
//    public void play() {
//        System.out.println("Playing Track: " + this.getTitle());
//        System.out.println("Track length: " + this.getLength());
//    }
    @Override
    public void play() throws PlayerException {
        if (length > 0) {
            System.out.println("Playing track: " + title);
            System.out.println("Track length: " + length);
        } else {
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("Track length must be positive: " + length);
        }
    }
}