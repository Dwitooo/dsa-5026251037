package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> requests = new LinkedList<>();

        Queue<String[]> success = new LinkedList<>();

        Stack<String[]> failed = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (scanner.hasNext()) {
            String name = scanner.next();
            String bookTitle = scanner.next();
            String [] request = {name, bookTitle};
            requests.add(request);

            boolean checkMember = false;

            for (String[] member : members) {
                if (member[0].equals(name)) {
                    checkMember = true;
                    break;
                }
            }

            if (!checkMember) {
                String[] newMember = {name, "0"};
                members.add(newMember);
            }

        }

        scanner.close();

        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);
            String name = request[0];
            String bookTitle = request[1];

            String[] member = null;
            for (String[] m : members) {
                if (m[0].equals(name)) {
                    member = m;
                    break;
                }
            }

            String[] book = null;
            for (String[] b : books) {
                if (b[0].equals(bookTitle)) {
                    book = b;
                    break;
                }
            }

            if (member != null && book != null) {
                int dipinjam = Integer.parseInt(member[1]);
                int stock = Integer.parseInt(book[1]);

                if (dipinjam < 2 && stock > 0) {
                    dipinjam++;
                    stock--;
                    member[1] = String.valueOf(dipinjam);
                    book[1] = String.valueOf(stock);
                    success.add(request);
                } else {
                    failed.push(request);
                }
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Successful Borrowings ===");
        while (!success.isEmpty()) {
            String[] request = success.poll();
            System.out.println(request[0] + " " + request[1]);
        }

        System.out.println();

        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println();

        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }

    }
}
