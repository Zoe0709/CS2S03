import java.util.Scanner;

public class Salary {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int t;
        t = keyboard.nextInt();

        int d;
        d = keyboard.nextInt();

        int D;
        D = keyboard.nextInt();

        int T;
        T = keyboard.nextInt();

        int totalSalary = 0;

        if (T <= t) {
            totalSalary = T * d;
        } else {
            totalSalary = t * d + (T - t) * D;
        }

        System.out.println(totalSalary);

    }
}


