import java.io.BufferedReader;
import java.io.InputStreamReader;

/* (완)
 * <문제 요약>
 * 문제 정의 : 가능한 성원이의 몸무게 구하기
 * 문제 유형 : 투 포인터
 * 
 * <피드백>
 * max = 1, min = 1에서 시작하는 것이 핵심!
 * 아이디어가 중요하다~~
 * 
 */

public class BOJ_G4_1484_다이어트 {

	static int G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());

		int max = 1, min = 1;
		boolean check = false;

		while (true) {
			int weight = (int) (Math.pow(max, 2) - Math.pow(min, 2)); // 몸무게 계산
			if ((max - min == 1) && weight > G) { // max-min=1인데 weight>G이면 더이상 계산할 필요가 없다!
				break;
			}
			if (weight == G) { // 구한 몸무게가 G와 같으면
				System.out.println(max); // 출력
				check = true; // 있음 체크
			}
			if (weight >= G) {
				min++;
			} else {
				max++;
			}
		}

		if (!check) { // 구했던 몸무게가 없을 때
			System.out.println("-1");
		}

	}

}
