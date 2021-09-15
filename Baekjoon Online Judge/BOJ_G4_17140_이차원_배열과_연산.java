import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_17140_이차원_배열과_연산 {

	static class Num implements Comparable<Num> {
		int num;
		int cnt;

		public Num(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Num o) {
			if (this.cnt == o.cnt) {
				return this.num - o.num;
			}
			return this.cnt - o.cnt;
		}

	}

	static int r, c, k;
	static int[] nums;
	static int[][] map = new int[101][101];
	static int answer = 0;
	static int lenX = 3, lenY = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int t = 0;
		boolean flag = false;
		while ((t++) <= 100) {
			if (map[r - 1][c - 1] == k) {
				flag = true;
				break;
			}
			if (lenX >= lenY) {
				colcR();
			} else {
				colcC();
			}
			answer++;
		}

		if (!flag) {
			answer = -1;
		}

		System.out.println(answer);
	}

	// 행연산
	private static void colcR() {
		int maxLen = 0;

		// 각 수의 개수 체크
		int[] nums;
		PriorityQueue<Num> pq;
		for (int i = 0; i < lenX; i++) {
			pq = new PriorityQueue<>();
			nums = new int[101];
			for (int j = 0; j < lenY; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				nums[map[i][j]]++;
			}

			for (int k = 1; k < 101; k++) {
				if (nums[k] != 0) {
					pq.add(new Num(k, nums[k]));
				}
			}

			for (int k = pq.size() * 2; k < lenY; k++) {
				map[i][k] = 0;
			}
			maxLen = Math.max(maxLen, pq.size());
			for (int k = 0; k < 50; k += 2) {
				if (pq.isEmpty()) {
					break;
				}
				Num now = pq.poll();
				map[i][k] = now.num;
				map[i][k + 1] = now.cnt;
			}

		}

		lenY = maxLen * 2;
	}

	// 열연산
	private static void colcC() {
		int maxLen = 0;

		// 각 수의 개수 체크
		int[] nums;
		PriorityQueue<Num> pq;
		for (int j = 0; j < lenY; j++) {
			pq = new PriorityQueue<>();
			nums = new int[101];
			for (int i = 0; i < lenX; i++) {
				if (map[i][j] == 0) {
					continue;
				}
				nums[map[i][j]]++;
			}

			for (int k = 1; k < 101; k++) {
				if (nums[k] != 0) {
					pq.add(new Num(k, nums[k]));
				}
			}

			for (int k = pq.size() * 2; k < lenY; k++) {
				map[k][j] = 0;
			}
			maxLen = Math.max(maxLen, pq.size());
			for (int k = 0; k < 50; k += 2) {
				if (pq.isEmpty()) {
					break;
				}
				Num now = pq.poll();
				map[k][j] = now.num;
				map[k + 1][j] = now.cnt;
			}

		}

		lenX = maxLen * 2;
	}

}
