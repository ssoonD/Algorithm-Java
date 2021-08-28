import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 문제 정의 : 얻을 수 있는 총 점수의 최댓값
 * 문제 유형 : DP
 * 유의 사항 : N은 자연수, 1<= N <= 300
 * 
 * <풀이법 정의>
 * 초기값
 * d[1] = stairs[1]
 * d[2] = stairs[1] + stairs[2]
 * 
 * Math.max(1, 2)
 * 1. 내가 첫번째 계단일 때 -> d[i-2] + stairs[i]
 * 2. 내가 두번째 계단일 때 -> d[i-3] + starts[i] + starts[i-1]
 */

public class BOJ_S3_2579_계단_오르기 {

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

		// 시작점
		d[1] = stairs[1];
		// 무조건 첫번째 계단을 밟는 것이 이득
		// if문 없었다가 계속 런타임 에러발생...
		if (N >= 2) {
			d[2] = stairs[1] + stairs[2];
		}
		for (int i = 3; i <= N; i++) {
			d[i] = Math.max(d[i - 2] + stairs[i], d[i - 3] + stairs[i] + stairs[i - 1]);
		}

		System.out.println(d[N]);
	}

}
