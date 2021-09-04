import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : N일 동안 호랑이에게 잡아먹히지 않도록 호랑이에게 줄 떡들 고르기
 * 문제 유형 : dfs
 * 
 * <피드백>
 * 난 dfs가 왜이렇게 어렵지...ㅜㅜ
 * boolean을 2차원으로 만들어서 풀어내는 방법을 생각해내지 못했다...
 * 다음에 다시 풀어봐야지!!!
 */

public class BOJ_G5_16432_떡장수와_호랑이 {

	static int N;
	static ArrayList<Integer>[] riceCake;
	static boolean[][] visited;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		riceCake = new ArrayList[N];
		answer = new int[N];
		visited = new boolean[N + 1][10]; // [날짜][떡 종류]
		for (int i = 0; i < N; i++) {
			riceCake[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				riceCake[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		solve(0, 0);
		// 없을 때 -1을 출력
		System.out.println("-1");
	}

	private static void solve(int cnt, int before) {
		if (cnt == N) { // N개가 가능하면
			// 정답 출력
			for (int i = 0; i < N; i++) {
				System.out.println(answer[i]);
			}
			// 끝내기
			System.exit(0);
		}

		for (int i = 1; i < 10; i++) {
			// 1. 이전에 뽑은 수가 아니고 -> i != before
			// 2. 방문한 적이 없고 -> !visited[cnt + 1][i]
			// 3. 해당 날짜에 뽑을 수 있고 -> riceCake[cnt].contains(i)
			if (i != before && !visited[cnt + 1][i] && riceCake[cnt].contains(i)) {
				// 방문 체크
				visited[cnt + 1][i] = true;
				// 정답 저장
				answer[cnt] = i;
				// 탐색
				solve(cnt + 1, i);
			}
		}
	}

}
