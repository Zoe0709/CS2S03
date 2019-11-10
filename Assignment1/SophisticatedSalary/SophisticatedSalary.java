import java.util.Scanner;

public class SophisticatedSalary {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int t = 0;
        int d = 0;
        int D = 0;
        int T = 0;
        int totalSalary;

        String vars;
        vars = keyboard.nextLine();

        String[] splitStr = vars.trim().split("\\s+");

        for (int i = 0; i < 4; i++) {
            String s = splitStr [i];
            char element = s.charAt(0);
            String[] expressions = s.split(";");

            switch (element) {
                case 't':
                    for (String exp : expressions) {
                        t = Integer.parseInt(exp.split("=")[1]);
                    }
                    break;
                case 'd':
                    for (String exp : expressions) {
                        d = Integer.parseInt(exp.split("=")[1]);
                    }
                    break;
                case 'D':
                    for (String exp : expressions) {
                        D = Integer.parseInt(exp.split("=")[1]);
                    }
                    break;
                case 'T':
                    for (String exp : expressions) {
                        T = Integer.parseInt(exp.split("=")[1]);
                    }
                    break;
            }

        }

        if (T <= t) {
            totalSalary = T * d;
        } else {
            totalSalary = t * d + (T - t) * D;
        }

        String total = Integer.toString(totalSalary);
        int count = total.length() + 8;

        for (int j = 0; j < count; j++) {
            System.out.print("*");
        }

        System.out.printf("%n");

        System.out.println(totalSalary + " Dollars");

        for (int j = 0; j < count; j++) {
            System.out.print("*");
        }


    }
}
