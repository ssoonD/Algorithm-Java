import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 구해야 하는 것 : 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값
 * 문제 유형 : dfs
 * 
 * <풀이법 요약>
 * 핵심
 * 1. 괄호의 위치는 연달아 올 수 없다!
 * 2. 연산자 우선순위는 모두 동일하기 때문에 왼쪽부터 순서대로 계산한다!
 * 
 * 풀이 과정
 * 1. 숫자와 연산자 배열에 저장
 * 2. 괄호 O, X dfs로 계산
 * 3. max값 return
 * 
 */
public class Main_G3_16637_괄호_추가하기_dfs {

	static int N; // 수식의 길이
	static int[] nums; // 입력받은 숫자
	static char[] opers; // 입력받은 연산자
	static int answer = Integer.MIN_VALUE; // 최댓값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) / 2;
		nums = new int[N + 1];
		opers = new char[N];

		// 입력
		String line = br.readLine();
		nums[0] = line.charAt(0) - '0';
		for (int i = 0; i < N; i++) {
			opers[i] = line.charAt(2 * i + 1);
			nums[i + 1] = line.charAt(2 * i + 2) - '0';
		}

		dfs(0, nums[0]);
		System.out.println(answer);
	}

	// 괄호가 올 수 있는 경우의 수 만들기 -> 괄호는 연달아 올 수 없다!
	// 해당 연산자 위치에는 괄호가 있거나 없거나 두가지 경우가 있다
	// 1. 괄호 X -> sum + num
	// 2. 괄호 O -> sum + 괄호
	private static void dfs(int index, int sum) {
		if (index >= N) {
			answer = Math.max(answer, sum);
			return;
		}

		// 괄호 X
		dfs(index + 1, calculation(sum, nums[index + 1], opers[index]));

		// 괄호 O
		if (index < N - 1) {
			// 다음 괄호 계산
			int bracket = calculation(nums[index + 1], nums[index + 2], opers[index + 1]);
			// index + 2
			dfs(index + 2, calculation(sum, bracket, opers[index]));
		}
	}

	// 계산
	private static int calculation(int first, int second, char oper) {
		if (oper == '+') {
			return first + second;
		} else if (oper == '-') {
			return first - second;
		} else {
			return first * second;
		}
	}

}
