import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험_감독 {

	static int N, B, C;
	static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long answer = 0;
		for (int i = 0; i < N; i++) {
			num[i] -= B;
			answer++;
			if (0 < num[i]) {
				answer += (num[i] / C);
				if (num[i] % C != 0) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

}
