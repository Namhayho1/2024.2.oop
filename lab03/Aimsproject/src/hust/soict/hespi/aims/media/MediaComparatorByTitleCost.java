package hust.soict.hespi.aims.media; // Cùng package với Media

import java.util.Comparator;

/**
 * Comparator để sắp xếp Media theo tiêu đề (tăng dần),
 * sau đó theo giá (giảm dần) nếu tiêu đề trùng nhau.
 */
public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh tiêu đề trước (nulls last)
        int titleCompare = Comparator.nullsLast(String::compareToIgnoreCase)
                                    .compare(m1.getTitle(), m2.getTitle());

        if (titleCompare != 0) {
            return titleCompare; // Tiêu đề khác nhau, trả về kết quả so sánh tiêu đề
        } else {
            // Tiêu đề giống nhau, so sánh giá giảm dần
            // Float.compare(f1, f2) trả về <0 nếu f1<f2, 0 nếu f1==f2, >0 nếu f1>f2
            // Muốn giảm dần nên đảo ngược: so sánh m2.getCost() với m1.getCost()
            return Float.compare(m2.getCost(), m1.getCost());
        }

        // Cách dùng comparing/thenComparing (ngắn gọn hơn):
        // return Comparator.comparing(Media::getTitle, Comparator.nullsLast(String::compareToIgnoreCase))
        //                .thenComparing(Media::getCost, Comparator.reverseOrder())
        //                .compare(m1, m2);
    }
}