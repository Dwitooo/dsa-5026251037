package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNext()) {
            String[] parts = new String[3];
            parts[0] = scanner.next();
            parts[1] = scanner.next();
            parts[2] = scanner.next();
            transactions.add(parts);
        }

        scanner.close();

        queue.addAll(transactions);
        while (!queue.isEmpty()) {
            String[] parts = queue.poll();

            String name = parts[0];
            String type = parts[1];
            int amount = Integer.parseInt(parts[2]);

            String[] customer = null;

            for (String[] data : customers) {
                if (data[0].equals(name)) {
                    customer = data;
                    break;
                }
            }

            if(customer == null){
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount <= balance) {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    failedTransactions.push(parts);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] parts = failedTransactions.pop();
            System.out.println(parts[0] + " " + parts[1] + " " + parts[2]);
        }

    }    

}