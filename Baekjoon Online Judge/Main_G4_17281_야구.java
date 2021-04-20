import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 아인타팀이 얻을 수 있는 최대 점수
 * 문제 유형 : next permutation, 구현
 * 
 * <풀이법 요약>
 * 1. 1번 선수가 4번 타자일 때 얻을 수 있는 타순을 next permutation으로 구한다
 * 2. 각 타순별 score을 계산한다
 * 3. max값을 찾아준다
 * 
 */

public class Main_G4_17281_야구 {

	static int N;
	static int[][] innings;
	static int[] p = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	static boolean[] base;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		innings = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	private static int solve() {
		int answer = 0;

		do {
			// 1번 선수가 4번 타자가 아니라면 넘겨!
			if (p[3] != 0) {
				continue;
			}
			answer = Math.max(answer, game());
		} while (np(p.length - 1));

		return answer;
	}

	// 게임
	private static int game() {
		int score = 0; // 점수
		int count = 0; // 아웃 카운트
		int index = 0; // 현재 타순

		for (int i = 0; i < N; i++) {
			// 현재 타석 1루 2루 3루
			base = new boolean[4];
			while (count != 3) { // 3아웃까지
				if (innings[i][p[index]] == 0) { // 아웃이라면
					count++;
				} else {
					base[0] = true; // 현재 타석으로 이동
					// 위치 이동
					for (int j = 3; j >= 0; j--) {
						if (!base[j]) { // 해당 타석에 사람이 없으면 넘겨~!
							continue;
						}
						int tmp = innings[i][p[index]] + j; // 이동 타석 구하기
						if (tmp > 3) { // 타석이 3루를 넘으면
							score++; // 점수 카운팅
						} else {
							base[tmp] = true; // 타석이 3루를 넘지 않으면 해당 타석 체크
						}
						base[j] = false; // 타석 이동했으니까 원래 타석 체크 되돌리기
					}
				}
				index = (index + 1) % 9; // 다음 타자 index
			}
			count = 0; // 아웃 카운트 reset
		}

		return score;
	}

	// next permutation
	// i : 첫 꼭대기 / j : 오른쪽에서 첫번째로 큰 수 / k : 오른쪽부터 swap할 index
	private static boolean np(int size) { // size : 맨 오른쪽 수
		int i = size; // 가장 오른쪽부터 시작
		while (i > 0 && p[i - 1] >= p[i]) {
			i--; // 첫 꼭대기를 찾는다.
		}

		if (i == 0)
			return false; // 이미 정렬되어 있음 5 4 3 2 1

		int j = size; // 가장 오른쪽
		while (p[i - 1] >= p[j]) {
			j--; // 오른쪽에서 첫번째로 큰 수 찾기
		}

		// swap
		int tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = size;
		while (i < k) {
			tmp = p[i];
			p[i] = p[k];
			p[k] = tmp;
			i++;
			k--;
		}

		return true;
	}

}
