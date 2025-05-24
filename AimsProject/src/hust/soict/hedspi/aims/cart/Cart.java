package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.hedspi.aims.media.Media;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Cart {
    private static final int MAX_NUMBERS_ORDERED = 20;

    // Sửa: Thêm khai báo itemsOrdered
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    // Sửa: Sử dụng FloatProperty đúng cách
    private FloatProperty totalCost = new SimpleFloatProperty(0f);

    public Cart() {
        itemsOrdered.addListener((ListChangeListener<Media>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Media media : change.getAddedSubList()) {
                        totalCost.set(totalCost.get() + media.getCost());
                    }
                }
                if (change.wasRemoved()) {
                    for (Media media : change.getRemoved()) {
                        totalCost.set(totalCost.get() - media.getCost());
                    }
                }
            }
        });
    }

    // Getter cho totalCostProperty để sử dụng trong binding
    public FloatProperty totalCostProperty() {
        return totalCost;
    }

    // Getter cho giá trị totalCost
    public float getTotalCost() {
        return totalCost.get();
    }

    // Sửa: Chỉ giữ một method addMedia
    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full.");
            return;
        }

        if(itemsOrdered.contains(media)) {
            System.out.println("Media already exists");
        } else {
            itemsOrdered.add(media);
            System.out.println("Media is added");
        }
    }

    // Add multiple media items
    public void addMedia(Media... mediaList) {
        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    // remove media
    public void removeMedia(Media media) {
        if(itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Media is removed");
        }else {
            System.out.println("No media was found");
        }
    }

    // Sửa: Method totalCost() trả về giá trị từ property
    public float totalCost() {
        return totalCost.get();
    }

    // Thêm phương thức để sắp xếp theo tiêu đề rồi giá
    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    // Thêm phương thức để sắp xếp theo giá rồi tiêu đề
    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    // Simple print method
    public void prinCart() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty");
            return;
        }
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ": " + itemsOrdered.get(i).getTitle() + " - " + itemsOrdered.get(i).getCost() + "$");
        }
    }

    // Detailed print method
    public void printCart() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("***********************CART***********************");
            System.out.println("The cart is empty");
            System.out.println("***************************************************");
            return;
        }
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + "$");
        System.out.println("***************************************************");
    }

    // Search media by ID
    public void searchMediaByID(int id) {
        if (itemsOrdered.isEmpty()) {
            System.out.println("No match is found.");
            return;
        }
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.toString());
                return;
            }
        }
        System.out.println("No match is found.");
    }

    // Search media by title
    public void searchMediaByTitle(String title) {
        if (itemsOrdered.isEmpty()) {
            System.out.println("No match is found.");
            return;
        }
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match is found.");
        }
    }

    public Media findByTitle(String title) {
        for (Media m : itemsOrdered) {
            if (m.isMatch(title)) return m;
        }
        return null;
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}