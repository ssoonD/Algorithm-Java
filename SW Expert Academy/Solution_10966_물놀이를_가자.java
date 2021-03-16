import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 물인 칸으로 이동하기 위한 최고 이동 횟수의 합
 * 문제 핵심 요약 : 물을 기준으로 bfs
 * 
 * <풀이법 요약>
 * 1. map에 물인 위치를 -1로 표시 + Queue에 넣어주기
 * 2. 물인 위치를 기준으로 bfs
 * 3. bfs를 돌리면서 map에 이동 횟수 넣어주기
 */

public class Solution_10966_물놀이를_가자 {

	static class Point {
		int x;
		int y;
		int dis;

		public Point(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}

	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			Queue<Point> q = new LinkedList<Point>(); // 큐 선언
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					if (line.charAt(j) == 'W') {
						map[i][j] = -1; // 물인곳을 표시
						q.offer(new Point(i, j, 0)); // 큐에 물의 위치 넣어주기
					}
				}
			}

			System.out.printf("#%d %d\n", t, bfs(N, M, q));
		}
	}

	public static int bfs(int N, int M, Queue<Point> q) {
		int answer = 0;
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != 0 || map[nx][ny] == -1) {
					continue;
				}
				map[nx][ny] = p.dis + 1; // map에 이동 횟수를 넣어줌
				answer += (p.dis + 1); // 이동 횟수의 합 계산
				q.offer(new Point(nx, ny, p.dis + 1)); // q에 추가~
			}
		}
		return answer;
	}

}
