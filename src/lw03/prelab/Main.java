package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Problem1();
        Problem2();
        Problem3();
    }

    public static void Problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();

            String[] parts = baris.split(" ", 2);
            String perintah = parts[0];

            if (perintah.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);
            } else if (perintah.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                String song = insertParts[1];
                if (index >= 0 && index <= playlist.size()) {
                    playlist.add(index, song);
                }
            } else if (perintah.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();
    }

    public static void Problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int dupli = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) continue;

            if (!participants.add(name)) {
                dupli++;
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String participant : participants) {
            System.out.println(rank + ". " + participant);
            rank++;
        }
        System.out.println("Duplicate registrations: " + dupli);
        System.out.println();
    }

    public static void Problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failed = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();

            String[] parts = baris.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failed++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failed);
    }
}

































