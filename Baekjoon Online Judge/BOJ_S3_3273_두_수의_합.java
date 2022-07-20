package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S3_3273_두_수의_합 {
    static int n, x;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());

        System.out.println(solve());
    }

    private static int solve() {
        int answer = 0;

        Arrays.sort(num);

        int left = 0, right = n - 1;
        while (left < right) {
            int sum = num[left] + num[right];
            if (sum < x) {
                left++;
            } else if (sum > x) {
                right--;
            } else {
                answer++;
                left++;
                right--;
            }
        }

        return answer;
    }
}
