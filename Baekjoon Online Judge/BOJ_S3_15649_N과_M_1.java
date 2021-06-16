import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_15649_N과_M_1 {

	static int N, M;
	static int[] arr;
	static boolean[] isused;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		isused = new boolean[N + 1];

		func(0);
	}

	private static void func(int index) {
		if (index == M) { 
			print();
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!isused[i]) {
				arr[index] = i;
				isused[i] = true;
				func(index + 1);
				isused[i] = false;
			}
		}
	}

	private static void print() {
		for (int i = 0; i < M; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
