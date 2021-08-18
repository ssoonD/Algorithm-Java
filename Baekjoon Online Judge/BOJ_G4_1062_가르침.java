import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어의 개수의 최댓값 구하기
 * 문제 유형 : 조합
 * 
 * <풀이법 요약>
 * 
 * 무조건 a n t i c 5개의 알파벳은 읽을 수 있어야한다!
 * 
 * -- 큰틀 --
 * 1. a n t i c 5개를 제외한 (K-1)개의 알파벳의 조합을 구한다
 * 2. 해당 조합을 사용하여 단어를 읽는다
 * 
 * -- 풀이법 --
 * (1) K가 26개면 모든 단어를 읽을 수 있기 때문에 N을 출력한다.
 * (2) K가 5 미만이면 읽을 수 없기 때문에  0을 출력한다.
 * (3) 그외는 a n t i c를 모두 알고있음으로 체크 + K에서 5를 빼준 뒤 시작!
 * 1. 26개의 알파벳 중 (K-5)개의 배울 수 있는 알파벳 조합을 구한다
 * 2. 단어 리스트를 돌면서 읽을 수 있는 단어의 수를 구한다
 * 3. 단어수의 최댓값을 갱신해준다
 * 
 * 
 */

public class BOJ_G4_1062_가르침 {

	static int N, K, answer;
	static String[] list;
	static boolean[] alpha = new boolean[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new String[N];

		if (K == 26) { // 26개의 알파벳을 모두 알고있다면 모든 단어를 읽을 수 있기 때문에 N을 출력한다.
			System.out.println(N);
		} else if (K < 5) { // a n t i c를 알아야하기 때문에 5 미만은 0을 출력한다.
			System.out.println("0");
		} else {
			// 입력
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				// 앞 : "anta", 뒤 : "tica"를 자르고 저장
				list[i] = line.substring(4, line.length() - 4);
			}

			// a n t i c 알고있음 체크
			alpha['a' - 'a'] = true;
			alpha['n' - 'a'] = true;
			alpha['t' - 'a'] = true;
			alpha['i' - 'a'] = true;
			alpha['c' - 'a'] = true;

			// a n t i c 사용 count 빼주기
			K -= 5;

			// 함수 실행
			combi(0, 0);
			System.out.println(answer);
		}
	}

	static void combi(int start, int cnt) {
		// K개의 알파벳을 배운 뒤 단어 읽기~~
		if (cnt == K) {
			// 읽을 수 있는 단어수의 최댓값을 저장
			answer = Math.max(answer, countWord());
			return;
		}

		// 배울 수 있는 K개의 알파벳 조합 구하기
		for (int i = start; i < 26; i++) {
			if (!alpha[i]) { // 알파벳을 사용하지 않은 경우
				alpha[i] = true;
				combi(i + 1, cnt + 1);
				alpha[i] = false;
			}
		}
	}

	// 단어 카운팅 함수
	static int countWord() {
		int count = 0;

		for (String word : list) {
			// 해당 단어를 읽을 수 있는지 판단
			boolean readCheck = true;
			for (int i = 0; i < word.length(); i++) {
				// 해당 알파벳을 배우지 않았다면
				if (!alpha[word.charAt(i) - 'a']) {
					readCheck = false; // 단어를 읽을 수 없음
					break;
				}
			}
			if (readCheck) { // 읽을 수 있는 단어라면
				count++; // 증가
			}
		}

		return count;
	}

}
