package lw02.prelab;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> rawTransactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        InputStream inputStream = Main.class.getResourceAsStream("transactions.txt");
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");
            rawTransactions.add(parts);

            String name = parts[0];
            boolean exists = false;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                customers.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        transactionQueue.addAll(rawTransactions);

        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);
            if (type.equals("DEPOSIT")) {
                customer[1] = String.valueOf(balance + amount);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(tx);
                } else {
                    customer[1] = String.valueOf(balance - amount);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + ": " + c[1]);
        }
        
        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}
