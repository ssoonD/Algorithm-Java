import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 사이클을 이루는 도로 길이의 최소값 찾기
 * 문제 유형 : 플로이드 와샬
 * 유의 사항 : INF = 4000005
 * 
 */

public class BOJ_G4_1956_운동 {

	static final int INF = 4000005;
	static int V, E;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new int[V + 1][V + 1];

		// 최단 거리 초기화
		for (int i = 0; i <= V; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
		}

		// 플로이드 와샬 실행
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// 사이클이 존재하는지 판단
		int answer = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) { // 자기 자신이면 continue
					continue;
				}
				// 서로가 서로에게 가는 경로가 있다면 -> 사이클 존재
				if (graph[i][j] != INF && graph[j][i] != INF) { // 사이클이 존재한다면
					answer = Math.min(answer, graph[i][j] + graph[j][i]); // 최소값 저장
				}
			}
		}

		System.out.println(answer == INF ? "-1" : answer);
	}

}
