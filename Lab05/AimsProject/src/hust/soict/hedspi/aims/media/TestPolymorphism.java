package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class TestPolymorphism {

	public static void main(String[] args) {
		// 1. Tạo ArrayList kiểu Media
        ArrayList<Media> mediaList = new ArrayList<Media>();
        
        // 2. Thêm các đối tượng media khác nhau vào danh sách
        // Lưu ý: Điều chỉnh các tham số constructor theo thiết kế lớp của bạn
        DigitalVideoDisc dvd = new DigitalVideoDisc(1, "The Lion King", "Animation",  87);
        mediaList.add(dvd);
        
        Book book = new Book(2, "Star Wars", "Science Fiction", 24.95f);
        book.addAuthor("George Lucas");
        mediaList.add(book);
        
        CompactDisc cd = new CompactDisc(3, "Greatest Hits", "Music", 12.95f, "Michael Jackson", "Epic Records");
        mediaList.add(cd);
        
        // 3 & 4. Duyệt qua danh sách và gọi toString() trên mỗi phần tử
        System.out.println("***********************MEDIA LIST***********************");
        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
        System.out.println("********************************************************");
        
        // 5 & 6. Giải thích chi tiết (có thể viết trong comment hoặc in ra)
        System.out.println("\nGiải thích về tính đa hình:");
        System.out.println("Mặc dù tất cả các đối tượng đều được lưu trong ArrayList<Media>,");
        System.out.println("nhưng khi gọi phương thức toString(), phiên bản toString() tương ứng");
        System.out.println("của lớp thực tế của đối tượng được thực thi.");
        System.out.println("Đây chính là biểu hiện của tính đa hình trong Java.");

	}

}
