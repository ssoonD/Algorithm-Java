import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * <문제 요약>
 * 문제 정의 : 조건에 해당하는 문자열의 길이 구하기
 * 문제 유형 : 슬라이딩 윈도우
 * 
 * <풀이법 요약>
 * K의 크기만큼 슬라이딩 윈도우로 index 차이를 계산하여 최대, 최소값을 구한다.
 * 
 */

public class BOJ_G5_20437_문자열_게임2 {

	static int T, K;
	static String line;
	static ArrayList<ArrayList<Integer>> alpha;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			line = br.readLine();
			K = Integer.parseInt(br.readLine());
			int len = line.length();

			// 초기화
			alpha = new ArrayList<>();
			for (int i = 0; i < 26; i++) {
				alpha.add(new ArrayList<>());
			}

			// 알파벳 넣어주기
			for (int i = 0; i < len; i++) {
				int index = line.charAt(i) - 'a';
				alpha.get(index).add(i);
			}

			solve();
		}

		System.out.println(answer.toString());
	}

	private static void solve() {
		// 최소값 최대값 체크
		int min = 100005, max = -1;

		// 알파벳 모두 체크
		for (int i = 0; i < 26; i++) {
			int size = alpha.get(i).size();
			// 사이즈가 작으면 넘겨주기
			if (size < K) {
				continue;
			}

			for (int j = 0; j <= size - K; j++) {
				// index차이 계산
				int diff = alpha.get(i).get(j + K - 1) - alpha.get(i).get(j) + 1;
				// 최대값 최소값 계산
				min = Math.min(min, diff);
				max = Math.max(max, diff);
			}
		}

		// 결과값 저장
		if (min == 100005 || max == -1) {
			answer.append(-1);
		} else {
			answer.append(min).append(" ").append(max);
		}
		answer.append("\n");
	}

}
