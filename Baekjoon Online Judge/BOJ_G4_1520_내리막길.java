import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* (미완)
 * <문제 요약>
 * 문제 정의 : (0,0)에서 (N-1,M-1)로 이동하는 경로의 수 + 현재 위치보다 더 낮은 지점만 이동 가능하다
 * 문제 유형 : dfs + dp
 * 
 * <피드백>
 * dfs + dp 문제는 너무 어렵다... 나중에 다시 풀어봐야할 문제
 * 
 */

public class BOJ_G4_1520_내리막길 {

	static int N, M;
	static int[][] map, d;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		d = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(d[i], -1);
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(go(0, 0));
	}

	private static long go(int x, int y) {
		// 목적지 도착 -> 탐색 필요 없음
		if (x == N - 1 && y == M - 1) {
			return 1;
		}
		// -1이 아니라면 -> 이미 방문
		if (d[x][y] != -1) {
			return d[x][y];
		}
		// 방문 표시
		d[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}
			// 이동이 가능한 경우 -> 현재 위치가 더 큰 경우
			if (map[nx][ny] < map[x][y]) {
				d[x][y] += go(nx, ny);
			}
		}

		return d[x][y];
	}

}
