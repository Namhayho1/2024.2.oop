package hust.soict.hedspi.aims.store;
import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    private static final int MAX_ITEMS_IN_STORE = 20;

    public Store() {
        // Constructor now initializes an empty ArrayList
    }

    // Add media to store
    public void addMedia(Media media) {
        if (itemsInStore.size() < MAX_ITEMS_IN_STORE) {
            itemsInStore.add(media);
            System.out.println("The media " + media.getTitle() + " has been added to the store.");
        } else {
            System.out.println("Store is full. Cannot add " + media.getTitle());
        }
    }

    // Add multiple media items at once
    public void addMedia(Media... mediaList) {
        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    // Remove media from store
    public void removeMedia(Media media) {
        if (itemsInStore.isEmpty()) {
            System.out.println("Store is empty. No media to remove.");
            return;
        }
        
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media " + media.getTitle() + " has been removed from the store.");
        } else {
            System.out.println("The media " + media.getTitle() + " is not in the store.");
        }
    }

    // Print store inventory
    public void printStore() {
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        System.out.println("*** Store Inventory ***");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("************************");
    }
}