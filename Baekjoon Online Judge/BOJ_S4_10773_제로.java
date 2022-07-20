package BOJ;

import java.io.*;
import java.util.Stack;

public class BOJ_S4_10773_제로 {
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        int answer = 0;
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }

        System.out.println(answer);
    }
}
