package lw01.unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = Main.class.getResourceAsStream("rentals.txt");
        
        Scanner scanner = new Scanner(inputStream);

        int n = scanner.nextInt();
        Rental[] rentals = new Rental[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days) {
                    @Override
                    public int calculateCharge() {
                        return super.calculateCharge() * units;
                    }
                };
            } else if (type.equals("PROJECTOR")) {
                rentals[i] = new ProjectorRental(id, days) {
                    @Override
                    public int calculateCharge() {
                        return super.calculateCharge() * units;
                    }
                };
            }
        }

        scanner.close();

        for (Rental r : rentals) {
            System.out.println(r.summary());
        }
    }
}