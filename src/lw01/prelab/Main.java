package lw01.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        InputStream inputStream = Main.class.getResourceAsStream("jobs.txt");

        if (inputStream == null) {
            System.err.println("File jobs.txt tidak ditemukan!");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if (type.equalsIgnoreCase("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equalsIgnoreCase("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}