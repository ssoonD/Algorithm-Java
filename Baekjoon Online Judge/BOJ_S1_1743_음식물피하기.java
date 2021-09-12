import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 가장 큰 음식물 찾기
 * 문제 유형 : bfs
 */

public class BOJ_S1_1743_음식물피하기 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, M, K;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1;
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!check[i][j] && (map[i][j] == 1)) {
					answer = Math.max(answer, solve(new Point(i, j)));
				}
			}
		}
		System.out.println(answer);
	}

	private static int solve(Point point) {
		int cnt = 1;
		Queue<Point> q = new LinkedList<>();
		q.offer(point);
		check[point.x][point.y] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || check[nx][ny] || (map[nx][ny] != 1)) {
					continue;
				}
				check[nx][ny] = true;
				q.offer(new Point(nx, ny));
				cnt++;
			}
		}
		return cnt;
	}

}
