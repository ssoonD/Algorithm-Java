import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 자신의 키 번호를 알 수 있는 학생의 수
 * 문제 유형 : 플로이드 와샬
 * 
 * <풀이법 요약>
 * 나와 연결된 node의 개수가 N-1개면 된다
 * 1. 플로이드 와샬로 graph를 구한다
 * 2. 해당 index의 row와 column이 0,INF가 아닌 개수의 합이 N-1개라면 카운팅 해준다
 * 
 */
public class Main_G4_2458_키_순서 {

	// 무한을 의미하는 값으로 10억을 설정
	public static final int INF = (int) 1e9;
	// N : 노드의 개수
	// M : 간선의 개수
	static int N, M;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];

		// 최단 거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a][b] = 1;
		}

		fw();

		System.out.println(solve());
	}

	private static void fw() {
		// k = 거쳐가는 노드
		for (int k = 0; k < N; k++) {
			// i = 출발 노드
			for (int i = 0; i < N; i++) {
				// j = 도착 노드
				for (int j = 0; j < N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}

	// 연결된 node의 개수 체크
	private static int solve() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			int count = 0; // 연결된 node의 개수
			for (int j = 0; j < N; j++) {
				if (graph[i][j] != 0 && graph[i][j] != INF) {
					count++;
				}
				if (graph[j][i] != 0 && graph[j][i] != INF) {
					count++;
				}
			}
			if (count == N - 1) { // N-1개가 연결되어 있다면
				answer++; // 학생의 수를 증가
			}
		}
		return answer;
	}

}
