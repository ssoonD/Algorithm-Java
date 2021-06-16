import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_15651_N과_M_3 {

	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		func(0);
		System.out.println(sb);
	}

	private static void func(int index) {
		if (index == M) {
			print();
			return;
		}
		for (int i = 1; i <= N; i++) {
			arr[index] = i;
			func(index + 1);
		}
	}

	private static void print() {
		for (int i = 0; i < M; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.append("\n");
	}
}
