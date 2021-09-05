import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 남아있는 얼음의 합과 가장 큰 덩어리의 칸 수를 출력
 * 문제 유형 : 시뮬레이션
 * 
 * <피드백>
 * 90도 돌리는 부분에서 오래걸렸다...
 * 기억해 두는게 좋을듯!
 */

public class BOJ_G4_20058_마법사_상어와_파이어스톰 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, Q, size;
	static int[][] map, tmp;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, N);
		map = new int[size][size];
		tmp = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			findStartIndex(L);
			melting();
//			print();
		}

		solve();
	}

	// 정답찾기
	private static void solve() {
		boolean[][] visited = new boolean[size][size];
		Queue<Point> q = new LinkedList<>();

		int sum = 0, max = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (!visited[x][y] && map[x][y] > 0) {
					int cnt = 1;
					sum += map[x][y];
					q.offer(new Point(x, y));
					visited[x][y] = true;

					while (!q.isEmpty()) {
						Point now = q.poll();
						for (int i = 0; i < 4; i++) {
							int nx = now.x + dx[i];
							int ny = now.y + dy[i];

							if (nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny] || (map[nx][ny] < 1)) {
								continue;
							}
							visited[nx][ny] = true;
							cnt++;
							sum += map[nx][ny];
							q.offer(new Point(nx, ny));
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}

		System.out.println(sum);
		System.out.println(max);
	}

	// 회전할 처음 index 찾기
	private static void findStartIndex(int L) {
		int len = (int) Math.pow(2, L);
		for (int i = 0; i < size; i += len) {
			for (int j = 0; j < size; j += len) {
				turn(i, j, len);
			}
		}
	}

	// 90도 회전
	private static void turn(int x, int y, int len) {
		int cnt = 0;
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				tmp[x + j - y][y + len - 1 - cnt] = map[i][j];
			}
			cnt++;
		}

		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	// 녹이기
	private static void melting() {
		ArrayList<Point> meltIce = new ArrayList<>();

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || nx >= size || ny < 0 || ny >= size) {
						continue;
					}
					if (map[nx][ny] >= 1) {
						cnt++;
					}
				}
				if (cnt < 3) {
					meltIce.add(new Point(x, y));
				}
			}
		}

		for (Point point : meltIce) {
			map[point.x][point.y]--;
		}
	}

//	private static void print() {
//		System.out.println();
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

}
