import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * <문제 요약>
 * 구해야 하는 것 : 지뢰를 제외한 모든 칸의 숫자를 나타내기 위한 최소 클릭 횟수
 * 문제 유형 : bfs, 구현
 * 
 * <풀이법 요약>
 * !!아이디어!!
 * 1. 0을 눌렀을 때 -> 플러드 필 가능 -> 모두 v로 바꿔준다
 * 2. 0이 아닌 곳을 눌렀을 때 -> 끝! -> . 유지
 * 
 * 0일 때 count(1) + . count(2) => 정답
 * 
 * 고민을 많이 했던 문제
 * 0을 눌렀을 때 v로 바꿔주고
 * 0이 아닌 곳을 눌렀을 때는 .을 유지해주면 된다
 * 그 후 0을 눌렀을 때 개수와 .의 개수를 더해주면 정답이다
 * 아이디어를 잘 짜는게 중요하다!
 * 
 */
public class Solution_1868_파핑파핑_지뢰찾기 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int T, N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1, -1, -1, 1, 1 };
	static int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			System.out.printf("#%d %d\n", t, solve());
		}
	}

	public static int solve() {
		int answer = 0;

		// 0일때만 dfs 진행 + 방문한 위치 v로 바꿔주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.' && checkCount(new Point(i, j)) == 0) {
					bfs(new Point(i, j));
					answer++;
				}
			}
		}

		// .인 개수 카운팅
		// .은 무조건 클릭해야하기 때문에
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					answer++;
				}
			}
		}

		return answer;
	}

	// 0을 클릭했을 때 플러드필
	public static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		visited[start.x][start.y] = true;

		while (!q.isEmpty()) {
			Point current = q.poll();
			// 주위 지뢰의 개수가 0일때만 bfs 진행
			if (checkCount(current) == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = current.x + dx[i];
					int ny = current.y + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] != '.') {
						continue;
					}
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
			// 방문한 위치 체크
			map[current.x][current.y] = 'v';
		}
	}

	// 주변 지뢰의 개수 체크
	private static int checkCount(Point current) {
		int count = 0; // 주변의 지뢰 개수 카운팅

		for (int i = 0; i < 8; i++) {
			int nx = current.x + dx[i];
			int ny = current.y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			if (map[nx][ny] == '*') { // 주변이 지뢰라면 count++
				count++;
			}
		}

		return count;
	}

}
