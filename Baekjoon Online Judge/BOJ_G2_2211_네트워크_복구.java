import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 네트워크 복구하는 방법을 출력 -> 최단거리 루트 찾기
 * 문제 유형 : 다익스트라
 * 
 * <풀이법 요약>
 * 최단 거리를 갱신할 때 마다의 path를 저장해준다 -> 그것이 바로 최단 거리이니까!
 * 
 * <피드백>
 * 다익스트라를 완전히 이해해야 풀 수 있는 문제같다! 좋은 문제!
 */

public class BOJ_G2_2211_네트워크_복구 {

	static class Computer implements Comparable<Computer> {
		int index;
		int distance;

		public Computer(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Computer o) {
			return this.distance - o.distance;
		}

	}

	static final int INF = 10005;
	static int N, M;
	static int[] d, path;
	static ArrayList<ArrayList<Computer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		d = new int[N + 1];
		path = new int[N + 1];
		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 최단 거리 테이블 초기화
		Arrays.fill(d, INF);
		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Computer(b, c));
			graph.get(b).add(new Computer(a, c));
		}
		// 다익스트라 실행
		dijkstra(1);

		// 출력
		StringBuilder answer = new StringBuilder();
		answer.append(N - 1).append("\n");
		for (int i = 2; i <= N; i++) {
			answer.append(i).append(" ").append(path[i]).append("\n");
		}
		System.out.println(answer.toString());
	}

	static void dijkstra(int start) {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		pq.offer(new Computer(start, 0));
		d[start] = 0;
		while (!pq.isEmpty()) {
			Computer now = pq.poll();
			if (d[now.index] < now.distance) {
				continue;
			}
			for (Computer next : graph.get(now.index)) {
				if (d[now.index] + next.distance < d[next.index]) {
					d[next.index] = d[now.index] + next.distance;
					pq.offer(new Computer(next.index, d[next.index]));
					// 기존의 비용보다 작은 경우 path를 저장해준다 -> 최단거리이기 때문에
					path[next.index] = now.index;
				}
			}
		}
	}
}
