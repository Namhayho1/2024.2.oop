package hust.soict.hespi.aims.media; // Cùng package với Media

import java.util.Comparator;

/**
 * Comparator để sắp xếp Media theo giá (giảm dần),
 * sau đó theo tiêu đề (tăng dần) nếu giá trùng nhau.
 */
public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh giá giảm dần trước
        int costCompare = Float.compare(m2.getCost(), m1.getCost()); // Đảo m1, m2 để giảm dần

        if (costCompare != 0) {
            return costCompare; // Giá khác nhau, trả về kết quả so sánh giá
        } else {
            // Giá giống nhau, so sánh tiêu đề tăng dần (nulls last)
             return Comparator.nullsLast(String::compareToIgnoreCase)
                             .compare(m1.getTitle(), m2.getTitle());
        }

        // Cách dùng comparing/thenComparing (ngắn gọn hơn):
        // return Comparator.<Media, Float>comparing(Media::getCost, Comparator.reverseOrder())
        //                .thenComparing(Media::getTitle, Comparator.nullsLast(String::compareToIgnoreCase))
        //                .compare(m1, m2);
    }
}