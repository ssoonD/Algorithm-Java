import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ȥ���� Ư������ 0�� ���� ����� �� ��� ���ϱ�
 * ���� ���� : �� ������
 * ���� ���� : Max : 2000000000, �񱳸� ���� ���밪�� ����ؾ��Ѵ�
 * 
 * <�ǵ��>
 * Ǯ���� �� ��װ� �����? ����
 * 
 */

public class BOJ_G5_2467_��� {

	final static int INF = 2000000005;
	static int N;
	static int[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		solve();
	}

	private static void solve() {
		int left = 0;
		int right = N - 1;
		int[] answer = new int[2];
		int sumMin = INF;
		
		while (left < right) {
			int sum = list[left] + list[right];

			// ���� ���Ѵ� + �񱳸� ���� ���밪�� ���Ѵ�
			if (sumMin > Math.abs(sum)) {
				sumMin = Math.abs(sum);
				// answer�� �� ����� ũ�⸦ �����Ѵ�.
				answer[0] = list[left];
				answer[1] = list[right];
				
				// ���� sum�� 0�� �ȴٸ� ��
				if (sum == 0) {
					break;
				}
			}

			if (sum < 0) { 
				left++;
			} else { 
				right--;
			}
		}
		
		// ���
		for (int i = 0; i < 2; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}
