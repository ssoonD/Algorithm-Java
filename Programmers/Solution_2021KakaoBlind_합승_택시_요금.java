import java.util.Arrays;

/*
 * <문제 요약>
 * 구해야 하는 것 : 최저 택시요금
 * 문제 유형 : 플로이드 와샬
 * 
 * <풀이법 요약>
 * 1. 플로이드 와샬로 각 지점에서의 최단 거리 구하기
 * 2. Math.min(s->i + i->a + i->b) 구하기!
 * 
 *  플로이드 와샬 알면 쉽게 풀리는 문제~
 */

public class Solution_2021KakaoBlind_합승_택시_요금 {

	// 최댓값
	static final int MAX = 20000001;
	static int[][] graph;

	public int solution(int n, int s, int a, int b, int[][] fares) {

		graph = new int[n][n];

		// 최단 거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i < n; i++) {
			Arrays.fill(graph[i], MAX);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}

		// 입력값 저장
		for (int i = 0; i < fares.length; i++) {
			int c = fares[i][0] - 1;
			int d = fares[i][1] - 1;
			int f = fares[i][2];
			graph[c][d] = f;
			graph[d][c] = f;
		}

		fw(n);

		return solve(n, s - 1, a - 1, b - 1);
	}

	// s->a,b 가는 방법
	// 1. s->i 까지 같이 타고감 -> 한번만 더해주기 (겹치기 때문에)
	// 2. i->a, i->b 각각 이동한다 -> 각각 더해주기
	private static int solve(int n, int s, int a, int b) {
		// 시작은 각자 이동하는 가격으로 시작한다
		int answer = graph[s][a] + graph[s][b];
		// (s->i + i->a + i->b)의 최솟값 구하기
		for (int i = 0; i < n; i++) {
			answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
		}
		return answer;
	}

	// 플로이드 와샬
	private static void fw(int n) {
		// k = 거쳐가는 노드
		for (int k = 0; k < n; k++) {
			// i = 출발 노드
			for (int i = 0; i < n; i++) {
				// j = 도착 노드
				for (int j = 0; j < n; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution_2021KakaoBlind_합승_택시_요금 pm = new Solution_2021KakaoBlind_합승_택시_요금();
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		System.out.println(pm.solution(n, s, a, b, fares));
	}

}
