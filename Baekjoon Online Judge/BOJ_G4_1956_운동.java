import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ����Ŭ�� �̷�� ���� ������ �ּҰ� ã��
 * ���� ���� : �÷��̵� �ͼ�
 * ���� ���� : INF = 4000005
 * 
 */

public class BOJ_G4_1956_� {

	static final int INF = 4000005;
	static int V, E;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new int[V + 1][V + 1];

		// �ִ� �Ÿ� �ʱ�ȭ
		for (int i = 0; i <= V; i++) {
			Arrays.fill(graph[i], INF);
		}

		// �Է�
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
		}

		// �÷��̵� �ͼ� ����
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// ����Ŭ�� �����ϴ��� �Ǵ�
		int answer = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) { // �ڱ� �ڽ��̸� continue
					continue;
				}
				// ���ΰ� ���ο��� ���� ��ΰ� �ִٸ� -> ����Ŭ ����
				if (graph[i][j] != INF && graph[j][i] != INF) { // ����Ŭ�� �����Ѵٸ�
					answer = Math.min(answer, graph[i][j] + graph[j][i]); // �ּҰ� ����
				}
			}
		}

		System.out.println(answer == INF ? "-1" : answer);
	}

}
