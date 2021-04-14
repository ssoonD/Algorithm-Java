import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 두 개의 정점을 지나는 최단 경로의 길이
 * 문제 유형 : 다익스트라
 * 
 * <풀이법 요약>
 * 나는 3개의 노드별로 최단거리 테이블을 다 만들어 주었다!
 * 그후 Start -> v1 -> v2 -> End vs Start -> v2 -> v1 -> End 했음
 * 1. 다익스트라를 사용하여 각 노드의 최단 거리 테이블을 만든다
 * 2. Math.min(start => v1 => v2 => N, start => v2 => v1 => N)
 */

public class Main_G4_1504_특정한_최단_경로 {

	static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}

	}

	// 무한을 의미하는 값으로 10억을 설정
	public static final int INF = (int) 1e9;
	// N : 정점의 개수
	// E : 간선의 개수
	static int N, E;
	static int v1, v2;
	// 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	// 최단 거리 테이블 만들기
	static int[] d1, d2, d3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		d1 = new int[N];
		d2 = new int[N];
		d3 = new int[N];
		// 최단 거리 테이블 모두 무한으로 초기화
		Arrays.fill(d1, INF);
		Arrays.fill(d2, INF);
		Arrays.fill(d3, INF);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken()) - 1;
		v2 = Integer.parseInt(st.nextToken()) - 1;

		dijkstra(0, d1);
		dijkstra(v1, d2);
		dijkstra(v2, d3);

		int answer = INF;
		// 1. start => v1 => v2 => N
		// 2. start => v2 => v1 => N
		// 만약 한개라도 INF의 값을 가진다면 구할 필요가 없음
		if (d1[v1] != INF && d2[v2] != INF && d3[N - 1] != INF) { // 1
			answer = d1[v1] + d2[v2] + d3[N - 1];
		}
		if (d1[v2] != INF && d3[v1] != INF && d2[N - 1] != INF) { // 2
			answer = Math.min(answer, d1[v2] + d3[v1] + d2[N - 1]);
		}

		// 경로가 존재하지 않을 때 -1을 출력
		System.out.println(answer >= INF ? -1 : answer);
	}

	// 다익스트라
	private static void dijkstra(int start, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if (d[current.index] < current.distance) {
				continue;
			}
			for (Node next : graph.get(current.index)) {
				// 현재 노드르 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
				if (d[current.index] + next.distance < d[next.index]) {
					d[next.index] = d[current.index] + next.distance;
					pq.offer(new Node(next.index, d[next.index]));
				}
			}
		}
	}

}
