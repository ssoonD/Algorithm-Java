import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 두 일꾼이 꿀을 채취하여 얻을 수 있는 수익의 합의 최댓값
 * 문제 유형 : subSet, Combination, Memoization, 완탐
 * 
 * <풀이법 요약>
 * 1. subSet을 이용하여 해당 위치에서의 수익의 최댓값을 계산한 뒤 저장한다. (Memoization)
 * 2. 완탐으로 두 개의 위치를 뽑아 최댓값을 저장한다
 * 
 * 2번을 좀더 아름답게? 구하고 싶었는데 실패했다
 * 저번에도 이런 비슷한 문제를 구했던 적이 있었는데 그때도 못 구했던것같다ㅜㅜ
 * 풀이법을 찾아봐야겠다 ㅜㅜ
 */

public class Solution_2115_벌꿀채취 {

	static int T, N, M, C;
	// map : 입력받은 map
	// maxMap : 해당 위치에서의 최대 수익
	static int[][] map, maxMap;
	static int maxSum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxMap = new int[N][N - M + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.printf("#%d %d\n", t, getMaxBenefit());
		}
	}

	private static int getMaxBenefit() {
		int answer = 0, aBenefit = 0, bBenefit = 0;

		makeMemoization();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				// 일꾼 A의 수익
				aBenefit = maxMap[i][j];

				// 일꾼 B의 수익
				bBenefit = 0;
				// 일꾼 A와 같은행에서 선택
				for (int j2 = j + M; j2 <= N - M; j2++) {
					bBenefit = Math.max(bBenefit, maxMap[i][j2]);
				}

				// 일꾼 A의 다음행부터 선택
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						bBenefit = Math.max(bBenefit, maxMap[i2][j2]);
					}
				}

				// 수익합의 최댓값 저장
				answer = Math.max(answer, aBenefit + bBenefit);
			}
		}

		return answer;
	}

	// Memoization
	private static void makeMemoization() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				maxSum = 0;
				makeMaxSubset(i, j, 0, 0, 0);
				maxMap[i][j] = maxSum; // 각 위치에 최댓값 저장
			}
		}
	}

	// i, j : 현재 index
	// cnt : 고려한 원소의 개수
	// sum : 채취한 꿀의 양
	// powerSum : 수익의 합
	// maxSum : 해당 벌통에서 얻을 수 있는 최대 수익
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powerSum) {
		// 채취할 수 있는 최대양을 넘었다면 끝
		if (sum > C) {
			return;
		}
		// 마지막 원소까지 다 부분집합에 고려해봤다면
		if (cnt == M) {
			maxSum = Math.max(maxSum, powerSum);
			return;
		}
		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powerSum + (map[i][j] * map[i][j]));
		// 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powerSum);
	}
}