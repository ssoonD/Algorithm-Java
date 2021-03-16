import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가능성 있는 모든 암호들 구하기
 * 문제 핵심 요약 : 백트래킹
 * 
 * <풀이법 요약>
 * 기저 조건
 * 1. answer의 길이가 L이고 최소 한개의 모음, 두 개의 자음을 만족한다면 출력
 * 2. 남은 알파벳의 개수가 앞으로 만들어야 할 암호의 길이보다 작다면 return
 * 
 * 재귀
 * 1. index에 해당하는 알파벳 포함
 * 2. index에 해당하는 알파벳 불포함
 */

public class Main_1759_암호_만들기 {

	static int L, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String[] alpha = br.readLine().split(" ");
		Arrays.sort(alpha);
		dfs(alpha, 0, "");
	}

	public static void dfs(String[] alpha, int index, String answer) {
		if (answer.length() == L) { // 만약 answer의 길이가 L이고
			if (check(answer)) { // 최소 한개의 모음, 두 개의 자음을 만족한다면
				System.out.println(answer); // 출력
			}
			return;
		}
		if ((C - index) < (L - answer.length())) { // 남은 알파벳의 개수가 앞으로 만들어야 할 암호의 길이보다 작다면
			return;
		}
		dfs(alpha, index + 1, answer + alpha[index]);
		dfs(alpha, index + 1, answer);
	}

	public static boolean check(String answer) {
		int cnt = 0; // 모음의 개수를 세주기 위한 변수
		for (int i = 0; i < L; i++) {
			if (answer.charAt(i) == 'a' || answer.charAt(i) == 'e' || answer.charAt(i) == 'i' || answer.charAt(i) == 'o'
					|| answer.charAt(i) == 'u') {
				cnt++;
			}
		}
		return cnt >= 1 && (L - cnt) >= 2; // 최소 한개의 모음, 두 개의 자음을 만족하는지 체크
	}

}
