import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ���� ���� Ī���� ��ġ
 * ���� ���� : �׷��� Ž��
 * 
 * <Ǯ�̹� ���>
 * 1. ��������Ʈ�� �� �������� ����Ʈ�� �����Ѵ�.
 * 2. �� ���� �� Ī�� ������ �����Ѵ�.
 * 3. �׷����� Ž���ϸ鼭 Ī���� ��ġ�� �����Ѵ�.
 * 
 */

public class BOJ_G4_14267_ȸ��_��ȭ_1 {

	static int n, m;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] point;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();

		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		point = new int[n + 1];

		// �Է�
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int boss = Integer.parseInt(st.nextToken());
			// ������ ��
			if (boss == -1) {
				continue;
			}
			// ���� ��翡 �ڽ��� ��ȣ �߰�
			graph.get(boss).add(i + 1);
		}

		// Ī�� ��ġ �Է�
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// ������ ��ȣ
			int index = Integer.parseInt(st.nextToken());
			// Ī���� ��ġ
			int weight = Integer.parseInt(st.nextToken());
			// ���� ��ġ ����
			point[index] += weight;
		}

		// �������� ����
		search(1);

		// ���� ���
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
			// now : ���
			int now = q.poll(); 
			// next : ��������
			for (int next : graph.get(now)) {
				// ����� Ī�� ��ġ�� ���� ������ Ī�� ��ġ�� �������ش�.
				point[next] += point[now];
				q.add(next);
			}
		}
	}

}
