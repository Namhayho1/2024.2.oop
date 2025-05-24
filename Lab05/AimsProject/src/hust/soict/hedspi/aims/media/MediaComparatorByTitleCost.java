package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media o1, Media o2) {
        // So sánh theo tiêu đề trước
        int titleComparison = o1.getTitle().compareToIgnoreCase(o2.getTitle());
        
        // Nếu tiêu đề giống nhau, so sánh theo giá (giảm dần)
        if (titleComparison == 0) {
            // Đảo ngược thứ tự so sánh để sắp xếp giảm dần
            return Float.compare(o2.getCost(), o1.getCost());
        }
        
        return titleComparison;
    }
}