import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : N�� ���� ȣ���̿��� ��Ƹ����� �ʵ��� ȣ���̿��� �� ���� ����
 * ���� ���� : dfs
 * 
 * <�ǵ��>
 * �� dfs�� ���̷��� �����...�̤�
 * boolean�� 2�������� ���� Ǯ��� ����� �����س��� ���ߴ�...
 * ������ �ٽ� Ǯ�������!!!
 */

public class BOJ_G5_16432_�������_ȣ���� {

	static int N;
	static ArrayList<Integer>[] riceCake;
	static boolean[][] visited;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		riceCake = new ArrayList[N];
		answer = new int[N];
		visited = new boolean[N + 1][10]; // [��¥][�� ����]
		for (int i = 0; i < N; i++) {
			riceCake[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				riceCake[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		solve(0, 0);
		// ���� �� -1�� ���
		System.out.println("-1");
	}

	private static void solve(int cnt, int before) {
		if (cnt == N) { // N���� �����ϸ�
			// ���� ���
			for (int i = 0; i < N; i++) {
				System.out.println(answer[i]);
			}
			// ������
			System.exit(0);
		}

		for (int i = 1; i < 10; i++) {
			// 1. ������ ���� ���� �ƴϰ� -> i != before
			// 2. �湮�� ���� ���� -> !visited[cnt + 1][i]
			// 3. �ش� ��¥�� ���� �� �ְ� -> riceCake[cnt].contains(i)
			if (i != before && !visited[cnt + 1][i] && riceCake[cnt].contains(i)) {
				// �湮 üũ
				visited[cnt + 1][i] = true;
				// ���� ����
				answer[cnt] = i;
				// Ž��
				solve(cnt + 1, i);
			}
		}
	}

}
