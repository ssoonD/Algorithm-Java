import java.io.*;
import java.util.*;

/*
 * <���� ���>
 * ���� ���� : ���� T�� ������ ������ �ɸ��� �̵� Ƚ���� �ּڰ�
 * ���� ���� : bfs
 * ���� ���� : ó���� (0,0)�� list�� �ְ� �����Ѵ�
 * 
 * <Ǯ�̹� ���>
 * list�� ����� index�� ������  �����ش�
 * check[index] : index�� �ش��ϴ� ������ �����ϴ� �̵� Ƚ���� �ּڰ�
 * 
 * 1. x �������� ����
 * 2. index ���� ���� Ž�� - x ��ǥ �������� Ž��
 * 2-1. Ž���ϴٰ� 2�ʰ��� �Ǵ� ���� break
 * 2-2. �湮���� ���� ����(check[i]!=-1) �Ǵ� y��ǥ�� 2�ʰ� -> continue 
 * 2-3. ��� ���� ����� index�� �ش��ϴ� �̵� Ƚ�� +1�� ���� ����
 * 3. index ���� ������ Ž�� - x ��ǥ �������� Ž��
 * (2-1 ~ 2-3) �ݺ�
 * 4. y ��ǥ�� T�� �Ǵ� ���� check[index] ���
 * 
 * <�ǵ��>
 * ó���� (0,0)�ְ� �����ϴ°� ���� �� �ؼ� ���� Ʋ�ȴ�..
 * 
 */

public class BOJ_G3_2412_�Ϻ�_��� {

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}

	}

	static int n, T;
	// �� node�� �湮 ��� (���°�� �湮�ߴ���)
	static int[] check;
	// node ����
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		check = new int[n + 1];

		// ���� ���� �־��ֱ�
		list.add(new Point(0, 0));
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Point(x, y));
		}

		// ����
		Collections.sort(list);
		// �ʱ�ȭ
		Arrays.fill(check, -1);

		System.out.println(solve());
	}

	private static int solve() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		check[0] = 0;

		while (!q.isEmpty()) {
			int now = q.poll();

			// T���� �������� �� ���
			if (list.get(now).y == T) {
				return check[now];
			}

			// ���� Ž��
			for (int i = now - 1; i > 0; i--) {
				// x ��ǥ �񱳰��� 2 �ʰ��� �� -> break;
				if (!chkAbs(list.get(now).x, list.get(i).x)) {
					break;
				}
				// �湮�� ��ǥ�� ��, y ��ǥ �񱳰��� 2 �ʰ��� �� -> continue;
				if (check[i] != -1 || !chkAbs(list.get(now).y, list.get(i).y)) {
					continue;
				}
				check[i] = check[now] + 1;
				q.offer(i);
			}

			// ������ Ž��
			for (int i = now + 1; i <= n; i++) {
				// x ��ǥ �񱳰��� 2 �ʰ��� �� -> break;
				if (!chkAbs(list.get(now).x, list.get(i).x)) {
					break;
				}
				// �湮�� ��ǥ�� ��, y ��ǥ �񱳰��� 2 �ʰ��� �� -> continue;
				if (check[i] != -1 || !chkAbs(list.get(now).y, list.get(i).y)) {
					continue;
				}
				check[i] = check[now] + 1;
				q.offer(i);
			}
		}

		return -1;
	}

	private static boolean chkAbs(int a, int b) {
		return Math.abs(a - b) <= 2;
	}

}
