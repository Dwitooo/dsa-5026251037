package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map <String, Integer> enroll = new LinkedHashMap<>();
        List <String> cek = new LinkedList<>();

        int reject = 0;
        int n = -1;

        while(scanner.hasNext()){
            String cmd = scanner.next();
            String kode = scanner.next();

            if(!cmd.equals("CHECK") ){
                n = scanner.nextInt();
            }
            
            scanner.nextLine();

            if(cmd.equals("REGISTER")){
                if(!enroll.containsKey(kode)){
                    enroll.put(kode, n);
                }
                else{
                    int sum = enroll.get(kode);
                    sum+= n;
                    enroll.put(kode, sum);
                }
            }else if(cmd.equals("WITHDRAW")){
                if(enroll.get(kode) == null){
                    reject++;
                }
                else if(enroll.get(kode) < n){
                    reject++;
                }
                else{
                    enroll.put(kode, enroll.get(kode) - n);
                }
            }else if(cmd.equals("CHECK")){
                if (enroll.containsKey(kode)) {
                    cek.add(kode + ": " + enroll.get(kode) + " students");
                } else {
                    cek.add(kode + ": Not found");
                    reject++;
                }
            }
        }
        System.out.println("===== Enrollment Checks =====");
        for(String i : cek){
            System.out.println(i);
        }

        System.out.println();

        System.out.println("===== Final Enrollment =====");
        for(Map.Entry<String, Integer> x : enroll.entrySet()) {
            String courseCode = x.getKey();
            int student = x.getValue();
            System.out.println(courseCode + ": " + student + " students");
        }
        System.out.println();

        System.out.println("Rejected operations: " + reject);
    }
}