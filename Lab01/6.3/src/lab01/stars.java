package lab01;
import java.util.Scanner; 
public class stars {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the triangle: ");
        int n = scanner.nextInt();
        scanner.close();
        for (int i = 1; i <= n; i++) {
            // In khoảng trắng để căn giữa
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            // In dấu *
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
