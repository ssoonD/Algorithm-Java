import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * <���� ���>
 * ���� ���� : ���ǿ� �ش��ϴ� ���ڿ��� ���� ���ϱ�
 * ���� ���� : �����̵� ������
 * 
 * <Ǯ�̹� ���>
 * K�� ũ�⸸ŭ �����̵� ������� index ���̸� ����Ͽ� �ִ�, �ּҰ��� ���Ѵ�.
 * 
 */

public class BOJ_G5_20437_���ڿ�_����2 {

	static int T, K;
	static String line;
	static ArrayList<ArrayList<Integer>> alpha;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			line = br.readLine();
			K = Integer.parseInt(br.readLine());
			int len = line.length();

			// �ʱ�ȭ
			alpha = new ArrayList<>();
			for (int i = 0; i < 26; i++) {
				alpha.add(new ArrayList<>());
			}

			// ���ĺ� �־��ֱ�
			for (int i = 0; i < len; i++) {
				int index = line.charAt(i) - 'a';
				alpha.get(index).add(i);
			}

			solve();
		}

		System.out.println(answer.toString());
	}

	private static void solve() {
		// �ּҰ� �ִ밪 üũ
		int min = 100005, max = -1;

		// ���ĺ� ��� üũ
		for (int i = 0; i < 26; i++) {
			int size = alpha.get(i).size();
			// ����� ������ �Ѱ��ֱ�
			if (size < K) {
				continue;
			}

			for (int j = 0; j <= size - K; j++) {
				// index���� ���
				int diff = alpha.get(i).get(j + K - 1) - alpha.get(i).get(j) + 1;
				// �ִ밪 �ּҰ� ���
				min = Math.min(min, diff);
				max = Math.max(max, diff);
			}
		}

		// ����� ����
		if (min == 100005 || max == -1) {
			answer.append(-1);
		} else {
			answer.append(min).append(" ").append(max);
		}
		answer.append("\n");
	}

}
