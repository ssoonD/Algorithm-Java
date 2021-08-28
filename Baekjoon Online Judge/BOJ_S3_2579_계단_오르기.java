import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <���� ���>
 * ���� ���� : ���� �� �ִ� �� ������ �ִ�
 * ���� ���� : DP
 * ���� ���� : N�� �ڿ���, 1<= N <= 300
 * 
 * <Ǯ�̹� ����>
 * �ʱⰪ
 * d[1] = stairs[1]
 * d[2] = stairs[1] + stairs[2]
 * 
 * Math.max(1, 2)
 * 1. ���� ù��° ����� �� -> d[i-2] + stairs[i]
 * 2. ���� �ι�° ����� �� -> d[i-3] + starts[i] + starts[i-1]
 */

public class BOJ_S3_2579_���_������ {

	static int N;
	static int[] stairs, d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stairs = new int[N + 1];
		d = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		// ������
		d[1] = stairs[1];
		// ������ ù��° ����� ��� ���� �̵�
		// if�� �����ٰ� ��� ��Ÿ�� �����߻�...
		if (N >= 2) {
			d[2] = stairs[1] + stairs[2];
		}
		for (int i = 3; i <= N; i++) {
			d[i] = Math.max(d[i - 2] + stairs[i], d[i - 3] + stairs[i] + stairs[i - 1]);
		}

		System.out.println(d[N]);
	}

}
