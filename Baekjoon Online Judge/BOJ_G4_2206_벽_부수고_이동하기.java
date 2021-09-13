import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * <문제 요약>
 * 문제 정의 : 최대 벽을 한 개 까지 부술 수 있을 때, 최단 경로 구하기
 * 문제 유형 : bfs
 * 
 * 반례 
 * 1 1
 * 0 
 * -> 1
 * 
 * <피드백>
 * 비슷한 유형이 많이 나오니까 해답 알아두자~!
 */

public class BOJ_G4_2206_벽_부수고_이동하기 {

	static class Point {
		int x;
		int y;
		int dis; // 이동거리
		int wall; // 벽 부수기

		public Point(int x, int y, int dis, int wall) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.wall = wall;
		}
	}

	static int N, M;
	// check : 공사 횟수 저장
	static int[][] map, check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				check[i][j] = Integer.MAX_VALUE; // 무한대로 초기화
			}
		}

		// 이동거리 : 1
		// 벽 부수기 : 0
		int answer = solve(new Point(0, 0, 1, 0));

		System.out.println(answer);
	}

	private static int solve(Point point) {
		Queue<Point> q = new LinkedList<>();
		q.offer(point);
		check[point.x][point.y] = 0;

		while (!q.isEmpty()) {
			Point now = q.poll();

			// 도착했을 때
			if (now.x == (N - 1) && now.y == (M - 1)) {
				return now.dis;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				// 다음의 공사 횟수가 기존의 기존의 값보다 작거나 같으면 pass~
				if (check[nx][ny] <= now.wall) {
					continue;
				}

				if (map[nx][ny] == 0) { // 벽이 아닐 때
					check[nx][ny] = now.wall; // 공사 횟수 유지
					q.offer(new Point(nx, ny, now.dis + 1, now.wall));
				} else { // 벽일 때
					if (now.wall == 0) {
						check[nx][ny] = now.wall + 1; // 공사 횟수 증가
						q.offer(new Point(nx, ny, now.dis + 1, now.wall + 1));
					}
				}
			}
		}

		return -1;
	}

}
