import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : �����ִ� ������ �հ� ���� ū ����� ĭ ���� ���
 * ���� ���� : �ùķ��̼�
 * 
 * <�ǵ��>
 * 90�� ������ �κп��� �����ɷȴ�...
 * ����� �δ°� ������!
 */

public class BOJ_G4_20058_������_����_���̾�� {

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

	// ����ã��
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

	// ȸ���� ó�� index ã��
	private static void findStartIndex(int L) {
		int len = (int) Math.pow(2, L);
		for (int i = 0; i < size; i += len) {
			for (int j = 0; j < size; j += len) {
				turn(i, j, len);
			}
		}
	}

	// 90�� ȸ��
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

	// ���̱�
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
