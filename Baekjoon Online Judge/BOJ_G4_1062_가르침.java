import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : K���� ���ڸ� ����ĥ ��, �л����� ���� �� �ִ� �ܾ��� ������ �ִ� ���ϱ�
 * ���� ���� : ����
 * 
 * <Ǯ�̹� ���>
 * 
 * ������ a n t i c 5���� ���ĺ��� ���� �� �־���Ѵ�!
 * 
 * -- ūƲ --
 * 1. a n t i c 5���� ������ (K-1)���� ���ĺ��� ������ ���Ѵ�
 * 2. �ش� ������ ����Ͽ� �ܾ �д´�
 * 
 * -- Ǯ�̹� --
 * (1) K�� 26���� ��� �ܾ ���� �� �ֱ� ������ N�� ����Ѵ�.
 * (2) K�� 5 �̸��̸� ���� �� ���� ������  0�� ����Ѵ�.
 * (3) �׿ܴ� a n t i c�� ��� �˰��������� üũ + K���� 5�� ���� �� ����!
 * 1. 26���� ���ĺ� �� (K-5)���� ��� �� �ִ� ���ĺ� ������ ���Ѵ�
 * 2. �ܾ� ����Ʈ�� ���鼭 ���� �� �ִ� �ܾ��� ���� ���Ѵ�
 * 3. �ܾ���� �ִ��� �������ش�
 * 
 * 
 */

public class BOJ_G4_1062_����ħ {

	static int N, K, answer;
	static String[] list;
	static boolean[] alpha = new boolean[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new String[N];

		if (K == 26) { // 26���� ���ĺ��� ��� �˰��ִٸ� ��� �ܾ ���� �� �ֱ� ������ N�� ����Ѵ�.
			System.out.println(N);
		} else if (K < 5) { // a n t i c�� �˾ƾ��ϱ� ������ 5 �̸��� 0�� ����Ѵ�.
			System.out.println("0");
		} else {
			// �Է�
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				// �� : "anta", �� : "tica"�� �ڸ��� ����
				list[i] = line.substring(4, line.length() - 4);
			}

			// a n t i c �˰����� üũ
			alpha['a' - 'a'] = true;
			alpha['n' - 'a'] = true;
			alpha['t' - 'a'] = true;
			alpha['i' - 'a'] = true;
			alpha['c' - 'a'] = true;

			// a n t i c ��� count ���ֱ�
			K -= 5;

			// �Լ� ����
			combi(0, 0);
			System.out.println(answer);
		}
	}

	static void combi(int start, int cnt) {
		// K���� ���ĺ��� ��� �� �ܾ� �б�~~
		if (cnt == K) {
			// ���� �� �ִ� �ܾ���� �ִ��� ����
			answer = Math.max(answer, countWord());
			return;
		}

		// ��� �� �ִ� K���� ���ĺ� ���� ���ϱ�
		for (int i = start; i < 26; i++) {
			if (!alpha[i]) { // ���ĺ��� ������� ���� ���
				alpha[i] = true;
				combi(i + 1, cnt + 1);
				alpha[i] = false;
			}
		}
	}

	// �ܾ� ī���� �Լ�
	static int countWord() {
		int count = 0;

		for (String word : list) {
			// �ش� �ܾ ���� �� �ִ��� �Ǵ�
			boolean readCheck = true;
			for (int i = 0; i < word.length(); i++) {
				// �ش� ���ĺ��� ����� �ʾҴٸ�
				if (!alpha[word.charAt(i) - 'a']) {
					readCheck = false; // �ܾ ���� �� ����
					break;
				}
			}
			if (readCheck) { // ���� �� �ִ� �ܾ���
				count++; // ����
			}
		}

		return count;
	}

}
