import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 각자 받은 칭찬의 수치
 * 문제 유형 : 그래프 탐색
 * 
 * <풀이법 요약>
 * 1. 인접리스트에 각 직원들의 리스트를 저장한다.
 * 2. 각 직원 별 칭찬 점수를 저장한다.
 * 3. 그래프를 탐색하면서 칭찬의 수치를 누적한다.
 * 
 */

public class BOJ_G4_14267_회사_문화_1 {

	static int n, m;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] point;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();

		// 그래프 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		point = new int[n + 1];

		// 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int boss = Integer.parseInt(st.nextToken());
			// 사장일 때
			if (boss == -1) {
				continue;
			}
			// 직속 상사에 자신의 번호 추가
			graph.get(boss).add(i + 1);
		}

		// 칭찬 수치 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// 직원의 번호
			int index = Integer.parseInt(st.nextToken());
			// 칭찬의 수치
			int weight = Integer.parseInt(st.nextToken());
			// 누적 수치 저장
			point[index] += weight;
		}

		// 보스부터 시작
		search(1);

		// 점수 출력
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			answer.append(point[i]).append(" ");
		}
		System.out.println(answer.toString());
	}

	private static void search(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			// now : 상사
			int now = q.poll(); 
			// next : 부하직원
			for (int next : graph.get(now)) {
				// 상사의 칭찬 수치를 부하 직원의 칭찬 수치에 누적해준다.
				point[next] += point[now];
				q.add(next);
			}
		}
	}

}
