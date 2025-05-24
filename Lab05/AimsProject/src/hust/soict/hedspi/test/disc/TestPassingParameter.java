package hust.soict.hedspi.test.disc;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle", "Adventure", "Jon Favreau", 120, 20.0f);
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella", "Fantasy", "Kenneth Branagh", 105, 18.5f);

        swap(jungleDVD, cinderellaDVD);  // Thử đổi chỗ hai đối tượng
        System.out.println("jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("cinderella DVD title: " + cinderellaDVD.getTitle());

       // changeTitle(jungleDVD, cinderellaDVD.getTitle());  // Đổi tiêu đề của jungleDVD

        System.out.println("jungle DVD title: " + jungleDVD.getTitle());
    }

    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        DigitalVideoDisc temp = dvd1;
        dvd1 = dvd2;
        dvd2 = temp;
    }

   // public static void changeTitle(DigitalVideoDisc dvd, String title) {
    //    dvd.setTitle(title);
    //}
}
