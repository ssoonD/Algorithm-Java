import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 문제 정의 : 정수 N을 1로 만드는 연산의 최솟값
 * 문제 유형 : DP
 * 
 * <풀이 요약>
 * 
 * <피드백>
 * TopDown - 288ms
 * BottomUp - 140ms
 */

public class BOJ_1463_S3_1로_만들기 {

	static int N;
	static int[] td, bu;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		td = new int[N + 1];
		bu = new int[N + 1];

		System.out.println(topDown(N));
		System.out.println(bottomUp(N));
	}

	// top-down
	private static int topDown(int n) {
		// 초기 값
		if (n == 1) {
			return 0;
		}
		// 메모이제이션
		// 이미 값이 저장되어 있으면 해당 값 return
		if (td[n] > 0) {
			return td[n];
		}
		// 조건3 - 1을 뺀다
		td[n] = topDown(n - 1) + 1;
		// 조건2 - 2로 나누어 떨어지고
		if (n % 2 == 0) {
			int tmp = topDown(n / 2) + 1;
			// 2로 나눈 연산 횟수가 더 작다면 바꿔준다
			if (td[n] > tmp) {
				td[n] = tmp;
			}
		}
		// 조건1 - 3으로 나누어 떨어지고
		if (n % 3 == 0) {
			int tmp = topDown(n / 3) + 1;
			// 3으로 나눈 연산 횟수가 더 작다면 바꿔준다
			if (td[n] > tmp) {
				td[n] = tmp;
			}
		}
		// 저장한 n에 해당하는 값 return
		return td[n];
	}

	// bottom-up
	private static int bottomUp(int n) {
		// 초기값
		bu[1] = 0;
		// N까지 반복
		for (int i = 2; i <= n; i++) {
			// 조건3 - 1을 뺀다
			bu[i] = bu[i - 1] + 1;
			// 조건2 - 2로 나누어 떨어지고 연산 횟수가 더 작다면 2로 나눈다
			if (i % 2 == 0 && bu[i] > bu[i / 2] + 1) {
				bu[i] = bu[i / 2] + 1;
			}
			// 조건1 - 3으로 나누어 떨어지고 연산 횟수가 더 작다면 3으로 나눈다
			if (i % 3 == 0 && bu[i] > bu[i / 3] + 1) {
				bu[i] = bu[i / 3] + 1;
			}
		}
		return bu[n];
	}

}
