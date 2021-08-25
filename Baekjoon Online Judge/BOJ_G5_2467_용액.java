import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 혼합한 특성값이 0에 가장 가까운 두 용액 구하기
 * 문제 유형 : 투 포인터
 * 유의 사항 : Max : 2000000000, 비교를 위해 절대값을 사용해야한다
 * 
 * <피드백>
 * 풀었던 두 용액과 비슷한? 문제
 * 
 */

public class BOJ_G5_2467_용액 {

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

			// 합을 비교한다 + 비교를 위해 절대값을 구한다
			if (sumMin > Math.abs(sum)) {
				sumMin = Math.abs(sum);
				// answer에 각 용액의 크기를 저장한다.
				answer[0] = list[left];
				answer[1] = list[right];
				
				// 만약 sum이 0이 된다면 끝
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
		
		// 출력
		for (int i = 0; i < 2; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}
