package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branch = new int[n];
        for (int i = 0; i < n; i++) {
            branch[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] target = new int[m];
        for (int i = 0; i < m; i++) {
            target[i] = sc.nextInt();
        }
        sc.close();

        int passes = 3;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = branch[i];
            int j = i - 1;

            while (j >= 0 && branch[j] < key) {
                branch[j + 1] = branch[j];
                j--;
            }
            branch[j + 1] = key;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(branch[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int target2 = target[i];
            int idx = -1;

            for (int j = 0; j < n; j++) {
                if (branch[j] == target2) {
                    idx = j;
                    break;
                }
            }

            if (idx != -1) {
                System.out.println(target2 + ": FOUND at index " + idx);
            } else {
                System.out.println(target2 + ": NOT FOUND");
            }
        }
    }
}
