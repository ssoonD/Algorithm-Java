import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1546_ЦђБе {

	static int N;
	static int[] score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		score = new int[N];
		st = new StringTokenizer(br.readLine());
		double max = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, score[i]);
			sum += score[i];
		}
		System.out.println((((double)sum / max) * 100.0) / N);
	}
}
