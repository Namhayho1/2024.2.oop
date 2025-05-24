package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        // So sánh theo giá trước (giảm dần)
        int costComparison = Float.compare(o2.getCost(), o1.getCost());
        
        // Nếu giá giống nhau, so sánh theo tiêu đề (theo bảng chữ cái)
        if (costComparison == 0) {
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
        }
        
        return costComparison;
    }
}