import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 용사가 공주님은 안전하게 구출할 수 있는지, 있다면 얼마나 빨리 구할 수 있는지
 * 문제 유형 : bfs + 반례찾기
 * 
 * <피드백>
 * 17%에서 자꾸 틀려서 반례를 찾아보고 !!!!!!!을 느꼈다 -> 알고리즘 다시짬
 * 
 * 반례
 * 4 10 100
 * 0 1 1 1 1 2 1 1 1 1
 * 0 0 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1 1 1
 * 0 0 0 0 0 0 0 0 0 0
 * 
 * 그람을 발견했다면 이미 이동했던 곳을 지나서 이동해야한다 
 * -> 이 반례를 발견하는 것이 핵심인듯 
 * -> visited을 3차원으로 만들어줘야한다!
 */

public class BOJ_G5_17836_공주님을_구해라 {

	static class Point {
		int x;
		int y;
		int cnt;
		boolean check;

		public Point(int x, int y, int cnt, boolean check) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.check = check;
		}

	}

	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// 그람이 있을 때 : 1
		// 그람이 없을 때 : 0
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs(0, 0);
		if (answer != -1) {
			System.out.println(answer);
		} else {
			System.out.println("Fail");
		}

	}

	private static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, 0, false));
		visited[x][y][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (now.cnt > T) { // T보다 크다면 return -1
				return -1;
			}
			if (now.x == N - 1 && now.y == M - 1) { // 도착지에 도달했다면 return cnt
				return now.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (now.check) { // 그람 있을 때
					if (!visited[nx][ny][1]) { // 방문하지 않았다면
						q.add(new Point(nx, ny, now.cnt + 1, now.check));
						visited[nx][ny][1] = true;
					}
				} else { // 그람이 없을 때
					if (visited[nx][ny][0] || map[nx][ny] == 1) { // 방문했거나 벽이면 continue
						continue;
					}
					boolean tmpCheck = now.check; // 현재 위치의 그람 유무 저장
					if (map[nx][ny] == 2) { // 만약 다음 위치가 그람이라면
						tmpCheck = true; // true로 변경
					}
					q.add(new Point(nx, ny, now.cnt + 1, tmpCheck));
					visited[nx][ny][0] = true;
				}

			}
		}

		return -1;
	}

}
