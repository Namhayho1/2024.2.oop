package B;
import java.util.Scanner;

public class bai6 {
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhâp số hàng của ma trận: ");
        int row = sc.nextInt();
        System.out.print("Nhập số cột của ma trận: ");
        int col = sc.nextInt();
        double[][] matA = new double[row][col]; //Ma trận A
        double[][] matB = new double[row][col]; //Ma trận B
        double[][] matAns = new double[row][col]; //Ma trận kết quả
        System.out.println("\nHãy nhập các phần tử trong ma trận A: ");
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                matA[i][j] = sc.nextDouble();
            }
        }
        System.out.println("\nHãy nhập các phần tử trong ma trận B: ");
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                matB[i][j] = sc.nextDouble();
            }
        }
 
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                matAns[i][j] = matA[i][j] + matB[i][j];
            }
        }

        System.out.println("\n________________________________");
        System.out.println("Kết quả matA + amtB là: ");
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                System.out.print(matAns[i][j]+" ");
            }
            System.out.println();  
        }
    }
}
