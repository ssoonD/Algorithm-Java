import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 탈출 가능한 칸의 수
 * 문제 유형 : dfs + 메모이제이션?
 * 
 * <풀이법 요약>
 * dfs의 return type을 boolean으로 설정
 * 1. 탈출 가능할 때  => return true
 * 2. 탈출 불가능할  때 => return false
 * 3. 방문 체크
 * 4. 해당 위치로 이동
 * 5. 이동이 끝나면 해당 위치 T / F (탈출 가능 / 불가능)로 변경
 * 6. 최종 가능여부 체크
 * 
 * 처음에 그냥 void로 하고 2중 for문 돌렸다가 가차없이 시간초과가 났다! ㅎㅎ
 * 해결법으로 boolean으로 바꿔 방문할 때 체크해주고 map을 바꿔주면서 이동했더니 통과했다
 */

public class Main_G2_17090_미로_탈출하기 {

	// N : 세로
	// M : 가로
	static int N, M;
	// map : 미로
	static char[][] map;
	// visited : 방문 여부
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(solve());
	}

	// 문제 풀이
	private static int solve() {
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 탈출 가능할 때 answer 카운팅
				if (dfs(i, j)) {
					answer++;
				}
			}
		}

		return answer;
	}

	private static boolean dfs(int x, int y) {
		boolean answer = false;

		// 탈출 가능할 때
		// 1. 미로 경계 밖으로 이동할 때
		// 2. 해당 위치에서 밖으로 이동 가능할 때
		if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 'T') {
			return true;
		}
		// 탈출 불가능할 때
		// 1. 해당 위치에서 밖으로 이동 불가능할 때
		// 2. 이미 방문한 위치일 때
		if (map[x][y] == 'F' || visited[x][y]) {
			return false;
		}

		// 방문 체크
		visited[x][y] = true;
		if (map[x][y] == 'U') { // 위쪽
			answer = dfs(x - 1, y);
		} else if (map[x][y] == 'R') { // 오른쪽
			answer = dfs(x, y + 1);
		} else if (map[x][y] == 'D') { // 아래쪽
			answer = dfs(x + 1, y);
		} else { // 왼쪽
			answer = dfs(x, y - 1);
		}
		// 해당 위치에서 탈출 가능 저장
		// T : 탈출 가능 / F : 탈출 불가능
		map[x][y] = answer ? 'T' : 'F';

		// 최종 가능여부 체크
		return answer;
	}

}
