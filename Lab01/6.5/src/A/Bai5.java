package A;
import java.util.Scanner;
import java.util.Arrays;
public class Bai5 {
	  public static void main(String[] args){
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Nhập số phần tử của mảng: ");
	        int n = sc.nextInt();
	        if (n <= 0){
	            System.out.print("Số phần tử của mảng không hợp lệ");
	            return;
	        }
	        double[] arr = new double[n]; //Khai báo mảng arr có n phần tử
	        for (int i=0;i<n;i++){
	            System.out.print("Input arr["+i+"]: ");
	            arr[i] = sc.nextDouble();
	        }
	        Arrays.sort(arr);
	        double sum = 0;
	        for (int i=0;i<n;i++){
	            sum += arr[i];
	        }
	        double avg = sum/n;

	        System.out.println("\n\nMảng sau khi đã sắp xếp từ bé đến lớn: ");
	        for (double i:arr){
	            System.out.print(i+" ");
	        }
	        System.out.println("\nTổng các phần tử trong mảng: "+sum);
	        System.out.print("Trung bình cộng các giá trị rtong mảng: "+avg);
	    }
}
