package lw04.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int size = scanner.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        SortCard(numbers, 2);

        for (int i : numbers) {
            System.out.print(i + " ");
        }

        System.out.println();

        int ask = scanner.nextInt();
        for (int i = 0; i < ask; i++) {
            int requestedCard = scanner.nextInt();
            int foundAt = findCard(numbers, requestedCard);
            if (foundAt != -1) {
                System.out.println(requestedCard + " : FOUND at index " + foundAt);
            } else {
                System.out.println(requestedCard + " : NOT FOUND ");
            }
        }

    }

    public static void SortCard(int[] arr, int passes) {
        for (int i = 0; i < arr.length && i < passes; i++) {
            int temporary = arr[i];
            int smallest = arr[i];
            int n = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < smallest) {
                    smallest = arr[j];
                    n = j;
                }
            }
            arr[i] = smallest;
            arr[n] = temporary;
        }
    }

    public static int findCard(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;

            }
        }
        return -1;
    }
} 
