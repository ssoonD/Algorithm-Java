import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * [문제 요약]
 * 구해야 하는 것 : 달빛 여우가 달빛 늑대보다 먼저 도착할 수 있는 그루터의 개수
 * 문제 유형 : 다익스트라
 * 
 * [풀이법 요약]
 * 아이디어
 * 1. 거리를 넣을 때 2배해서 넣어주기
 * 2. wolf를 2차원 배열로 만들어주기
 * 
 * [여우]
 * 다익스트라 진행
 * [늑대]
 * wolf 배열의 열로 짝수를 조절하면서 다익스트라 진행
 * 
 * 풀다가 gg 구선생의 힘을 빌렸음...!! => wolf를 2차원 배열로 만들어야 했음..ㅎ
 * 다익스트라가 진짜 어려운 문제구나
 * 
 */
public class Main_G2_16118_달빛_여우 {

	static class Node implements Comparable<Node> {
		int index;
		int distance;
		int check;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		public Node(int index, int distance, int check) {
			this.index = index;
			this.distance = distance;
			this.check = check;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}

	}

	public static final int INF = 2000000000;
	static int N, M;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] fox;
	static int[][] wolf;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		fox = new int[N];
		wolf = new int[N][2];

		// 그래프 초기화
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
			fox[i] = INF;
			Arrays.fill(wolf[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) * 2;
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		dijkstraFox(0, fox);
		dijkstraWolf(0, wolf);
		
		System.out.println(solve());
	}

	private static int solve() {
		int answer = 0;

		for (int i = 0; i < N; i++) {
			// 달빛 여우가 늑대보다 먼저 도착할 때
			if (fox[i] < Math.min(wolf[i][0], wolf[i][1])) {
				answer++;
			}
		}

		return answer;
	}

	// 다익스트라 - 여우
	private static void dijkstraFox(int start, int[] d) {
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

	// 다익스트라 - 늑대
	// check : 0 - 홀수번째 도착
	// check : 1 - 짝수번째 도착
	private static void dijkstraWolf(int start, int[][] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0, 0));
		d[start][0] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if (d[current.index][current.check] < current.distance) {
				continue;
			}
			for (Node next : graph.get(current.index)) {
				int tmpDistance = current.distance;
				int tmpCheck = -1;

				if (current.check == 1) { // 짝수일 때
					tmpDistance += (next.distance * 2); // 두배의 속도
					tmpCheck = 0; // 다음 홀수로 변경
				} else { // 홀수일 때
					tmpDistance += (next.distance / 2); // 절반의 속도
					tmpCheck = 1; // 다음짝수로 변경
				}

				if (tmpDistance < d[next.index][tmpCheck]) {
					d[next.index][tmpCheck] = tmpDistance;
					pq.offer(new Node(next.index, tmpDistance, tmpCheck));
				}
			}
		}
	}
}
