import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B1_4344_평균은_넘겠지 {

	static int N, M;
	static int[] score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			score = new int[M];
			int sum = 0;
			for (int i = 0; i < M; i++) {
				score[i] = Integer.parseInt(st.nextToken());
				sum += score[i];
			}
			double average = sum / M;
			int count = 0;
			for (int i = 0; i < M; i++) {
				if (score[i] > average) {
					count++;
				}
			}
			System.out.printf("%.3f%%\n", (double)count / M * 100);
		}
	}

}
