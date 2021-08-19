import java.io.BufferedReader;
import java.io.InputStreamReader;

/* (��)
 * <���� ���>
 * ���� ���� : ������ �������� ������ ���ϱ�
 * ���� ���� : �� ������
 * 
 * <�ǵ��>
 * max = 1, min = 1���� �����ϴ� ���� �ٽ�!
 * ���̵� �߿��ϴ�~~
 * 
 */

public class BOJ_G4_1484_���̾�Ʈ {

	static int G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());

		int max = 1, min = 1;
		boolean check = false;

		while (true) {
			int weight = (int) (Math.pow(max, 2) - Math.pow(min, 2)); // ������ ���
			if ((max - min == 1) && weight > G) { // max-min=1�ε� weight>G�̸� ���̻� ����� �ʿ䰡 ����!
				break;
			}
			if (weight == G) { // ���� �����԰� G�� ������
				System.out.println(max); // ���
				check = true; // ���� üũ
			}
			if (weight >= G) {
				min++;
			} else {
				max++;
			}
		}

		if (!check) { // ���ߴ� �����԰� ���� ��
			System.out.println("-1");
		}

	}

}
