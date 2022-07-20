package BOJ;

import java.io.*;
import java.util.Arrays;

public class BOJ_S5_1475_방_번호 {
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        num = new int[10];

        checkNum(number);

        System.out.println(solve());
    }

    private static void checkNum(int number) {
        while (number > 0) {
            num[number % 10]++;
            number /= 10;
        }
    }

    private static int solve() {
        num[6] += num[9];
        num[9] = 0;
        num[6] = (num[6] + 1) / 2;
        Arrays.sort(num);
        return num[9];
    }
}
