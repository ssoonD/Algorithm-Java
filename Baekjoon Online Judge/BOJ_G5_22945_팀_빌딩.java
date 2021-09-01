import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : �� �������� ���� �� �ִ� �� �ɷ�ġ�� �ִ밪 ���ϱ�
 * ���� ���� : ��������
 * ���� ���� : answerMin = 0
 * 
 */

public class BOJ_G5_22945_��_���� {

	static int N;
	static int[] ability;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		ability = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ability[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}

	private static int solve() {
		int answer = 0;

		int diff = N - 2;
		int left = 0, right = N - 1;
		while (diff != 0) {
			// �� �ɷ�ġ ���
			int teamAbility = diff * Math.min(ability[left], ability[right]);
			// max�� ����
			answer = Math.max(answer, teamAbility);
			// �� ���� ���� ���� ������ �̵�
			if (ability[left] < ability[right]) {
				left++;
			} else {
				right--;
			}
			// ���� ����
			diff--;
		}

		return answer;
	}

}
