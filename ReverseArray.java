package LAB9;
import java.util.Scanner;
public class ReverseArray {
 
    public static <T> void printArrayInReverse(T[] array) {
        System.out.print("Reversed Array: ");
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the integer array: ");
        int n = scanner.nextInt();
        Integer[] intArray = new Integer[n];
        System.out.println("Enter elements of the integer array:");
        for (int i = 0; i < n; i++) {
            intArray[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the double array: ");
        int m = scanner.nextInt();
        Double[] doubleArray = new Double[m];
        System.out.println("Enter elements of the double array:");
        for (int i = 0; i < m; i++) {
            doubleArray[i] = scanner.nextDouble();
        }

        System.out.print("Enter the size of the character array: ");
        int o = scanner.nextInt();
        Character[] charArray = new Character[o];
        System.out.println("Enter elements of the character array:");
        for (int i = 0; i < o; i++) {
            charArray[i] = scanner.next().charAt(0);
        }

        System.out.println("\nReversing the arrays:");
        System.out.println("Integer Array:");
        printArrayInReverse(intArray);

        System.out.println("Double Array:");
        printArrayInReverse(doubleArray);

        System.out.println("Character Array:");
        printArrayInReverse(charArray);

        scanner.close();
    }
}

