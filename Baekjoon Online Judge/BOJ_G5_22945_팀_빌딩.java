import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 팀 빌딩에서 나올 수 있는 팀 능력치의 최대값 구하기
 * 문제 유형 : 투포인터
 * 유의 사항 : answerMin = 0
 * 
 */

public class BOJ_G5_22945_팀_빌딩 {

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
			// 팀 능력치 계산
			int teamAbility = diff * Math.min(ability[left], ability[right]);
			// max값 저장
			answer = Math.max(answer, teamAbility);
			// 더 작은 값을 가진 포인터 이동
			if (ability[left] < ability[right]) {
				left++;
			} else {
				right--;
			}
			// 차이 감소
			diff--;
		}

		return answer;
	}

}
