import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ��Ʈ��ũ �����ϴ� ����� ��� -> �ִܰŸ� ��Ʈ ã��
 * ���� ���� : ���ͽ�Ʈ��
 * 
 * <Ǯ�̹� ���>
 * �ִ� �Ÿ��� ������ �� ������ path�� �������ش� -> �װ��� �ٷ� �ִ� �Ÿ��̴ϱ�!
 * 
 * <�ǵ��>
 * ���ͽ�Ʈ�� ������ �����ؾ� Ǯ �� �ִ� ��������! ���� ����!
 */

public class BOJ_G2_2211_��Ʈ��ũ_���� {

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
		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// �ִ� �Ÿ� ���̺� �ʱ�ȭ
		Arrays.fill(d, INF);
		// �Է�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Computer(b, c));
			graph.get(b).add(new Computer(a, c));
		}
		// ���ͽ�Ʈ�� ����
		dijkstra(1);

		// ���
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
					// ������ ��뺸�� ���� ��� path�� �������ش� -> �ִܰŸ��̱� ������
					path[next.index] = now.index;
				}
			}
		}
	}
}
