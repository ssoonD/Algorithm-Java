import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 경과 시간 동안 탈주범이 위치할 수 있는 장소의 개수
 * 문제 핵심 요약 : bfs + 조건 체크
 * 
 * <풀이법 요약>
 * 두가지의 조건만 체크해주면 된다! 그 외는 전형적인 bfs
 * 1. "해당 타입별"로 이동할 수 있는지 
 * 2. 이동하는 위치에서 가능한 "다음 타입"
 */

public class Solution_1953_탈주범_검거 {

	static class Point {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(R, C, 0));

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.printf("#%d %d\n", t, bfs(N, M, R, C, L, map, q, new boolean[N][M]));
		}
	}

	public static int bfs(int N, int M, int R, int C, int L, int[][] map, Queue<Point> q, boolean[][] visited) {
		int answer = 0;
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		visited[R][C] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int type = map[p.x][p.y];
			if (p.cnt >= L) // 만약 소요시간보다 크거나 같다면 그만~!
				break;
			answer++; // 장소 개수 증가
			for (int i = 0; i < 4; i++) {
				// 해당 타입별로 이동할 수 없는 부분 넘겨주는 부분
				if (type == 2 && (i == 1 || i == 3)) {
					continue;
				} else if (type == 3 && (i == 0 || i == 2)) {
					continue;
				} else if (type == 4 && (i == 2 || i == 3)) {
					continue;
				} else if (type == 5 && (i == 0 || i == 3)) {
					continue;
				} else if (type == 6 && (i == 0 || i == 1)) {
					continue;
				} else if (type == 7 && (i == 1 || i == 2)) {
					continue;
				}
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visited[nx][ny]) {
					continue;
				}
				// 이동하는 위치에서 가능한 다음 타입별로 체크
				int nextType = map[nx][ny];
				if (i == 0) {
					if (nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, p.cnt + 1));
					}
				} else if (i == 1) {
					if (nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, p.cnt + 1));
					}
				} else if (i == 2) {
					if (nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, p.cnt + 1));
					}
				} else {
					if (nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, p.cnt + 1));
					}
				}

			}
		}
		return answer;
	}

}
