import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S3_15655_N과_M_6 {

	static int N, M;
	static int[] arr, nums;
	static boolean[] isused;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];
		nums = new int[N];
		isused = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		func(0, 0);
		System.out.println(sb);
	}

	private static void func(int index, int cnt) {
		if (index == M) {
			print();
			return;
		}
		for (int i = cnt; i < N; i++) {
			if (!isused[i]) {
				isused[i] = true;
				arr[index] = nums[i];
				func(index + 1, i + 1);
				isused[i] = false;
			}
		}
	}

	private static void print() {
		for (int i = 0; i < M; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.append("\n");
	}
}
