package BOJ;

import java.io.*;

public class BOJ_B2_2577_숫자의_개수 {
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = new int[10];
        int number = 1;
        for (int i = 0; i < 3; i++) {
            number *= Integer.parseInt(br.readLine());
        }

        checkNum(number);

        print();
    }

    private static void checkNum(int number) {
        while (number > 0) {
            num[number % 10]++;
            number /= 10;
        }
    }

    private static void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(num[i]);
        }
    }

}
