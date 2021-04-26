import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 요약]
 * 구해야 하는 것 : 주어진 구간의 수의 합
 * 문제 유형 : DP, 구간합
 * 
 * [풀이법 요약]
 * 핵심은 구간합을 "사각형"으로 구하기!!
 * 사각형을 더하고 빼면서 정답을 계산
 * 1. (1,1)에서 (x,y)까지 구간합을 구한다 (배열의 크기를 N+1로 만들면 쉬움)
 * 2. 입력받은 구간에서의 구간합을 구한다
 * 
 * 그냥 행으로 생각해서 구간합으로 구했는데 바로 시간초과가 났다ㅜㅜ
 * => 시간초과 해결법 : stringBuilder를 사용하자!
 * 사각형으로 구간합을 구할 수 있구낭~~
 * 
 */

public class Main_S1_11660_구간_합_구하기_5 {

	static int N, M;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// (1, 1) 부터 해당 좌표까지의 사각형 안 모든 수의 합을 저장
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(solve(x1, y1, x2, y2));
		}

	}

	private static int solve(int x1, int y1, int x2, int y2) {
		// 저장한 구간합으로 정답 계산
		return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
	}

}
