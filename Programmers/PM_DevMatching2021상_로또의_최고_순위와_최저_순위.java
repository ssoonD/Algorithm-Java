import java.util.Arrays;

public class PM_DevMatching2021��_�ζ���_�ְ�_������_����_���� {

	public int[] solution(int[] lottos, int[] win_nums) {
		boolean[] check = new boolean[46];
		for (int num : win_nums) {
			check[num] = true;
		}

		int sameCnt = 0, zeroCnt = 0;
		for (int num : lottos) {
			if (check[num]) {
				sameCnt++;
			} else if (num == 0) {
				zeroCnt++;
			}
		}

		int maxWin = (sameCnt + zeroCnt >= 6) ? 6 : sameCnt + zeroCnt;
		maxWin = (maxWin == 0) ? 6 : 7 - maxWin;
		int minWin = (sameCnt == 0) ? 6 : 7 - sameCnt;

		return new int[] { maxWin, minWin };
	}

	public static void main(String[] args) {
		PM_DevMatching2021��_�ζ���_�ְ�_������_����_���� p = new PM_DevMatching2021��_�ζ���_�ְ�_������_����_����();
		int[] lottos = { 44, 1, 0, 0, 31, 25 };
		int[] win_nums = { 31, 10, 45, 1, 6, 19 };
		System.out.println(Arrays.toString(p.solution(lottos, win_nums)));
	}

}
