import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * <문제 요약>
 * 구해야 하는 것 : 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값
 * 문제 유형 : 완탐
 * <풀이법 요약>
 * 핵심
 * 1. 괄호의 위치는 연달아 올 수 없다!
 * 2. 연산자 우선순위는 모두 동일하기 때문에 왼쪽부터 순서대로 계산한다!
 * 
 * 풀이 과정
 * 1. 숫자와 연산자 배열에 저장
 * 2. 괄호가 올 수 있는 경우의 수를 powerSet을 이용해서 만들기
 * 3. 각 각의 경우마다 stack을 사용해서 계산하기
 * 4. max값 return
 * 
 * 예전에 계산기 문제를 풀었을 때 stack을 가지고 풀었던 적이 있어서 의심없이 stack을 이용해서 빡구현을 했다
 * 그런데 다들 dfs로 풀었더라... ㅎㅎ
 * 내가 아직 dfs가 익숙하지 않은걸까..?? 요즘 너무 갇혀있는 풀이를 하는것 같다ㅜㅜ
 * 내일 다시 dfs로 풀어봐야곘다!!!!!
 */
public class Main_G3_16637_괄호_추가하기 {

	static int N; // 수식의 길이
	static int[] nums; // 입력받은 숫자
	static char[] opers; // 입력받은 연산자
	static int answer; // 최댓값
	static Stack<Integer> numList = new Stack<>(); // 숫자 stack
	static Stack<Character> operList = new Stack<>(); // 연산자 stack

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) / 2 + 1;
		nums = new int[N];
		opers = new char[N - 1];

		// 입력
		String line = br.readLine();
		nums[0] = line.charAt(0) - '0';
		answer = nums[0];
		for (int i = 0; i < N - 1; i++) {
			opers[i] = line.charAt(2 * i + 1);
			nums[i + 1] = line.charAt(2 * i + 2) - '0';
			answer = calculation(answer, nums[i + 1], opers[i]);
		}

		powerSet(0, N - 1, new boolean[N - 1]);
		System.out.println(answer);
	}

	// 괄호가 올 수 있는 경우의 수 만들기 -> 괄호는 연달아 올 수 없다!
	private static void powerSet(int index, int n, boolean[] visited) {
		if (index == n) {
			answer = Math.max(answer, solve(n, visited));
			return;
		}

		visited[index] = false;
		powerSet(index + 1, n, visited);

		// 괄호가 연달아 올 수 없는 부분 체크
		if (index == 0 || (0 <= (index - 1) && !visited[index - 1])) {
			visited[index] = true;
			powerSet(index + 1, n, visited);
		}
	}

	private static int solve(int n, boolean[] visited) {
		// 괄호 먼저 계산
		numList.add(nums[n]);
		for (int i = n - 1; i >= 0; i--) {
			if (visited[i]) { // 괄호 추가 O
				numList.add(calculation(nums[i], numList.pop(), opers[i]));
			} else { // 괄호 추가 X
				numList.add(nums[i]);
				operList.add(opers[i]);
			}
		}

		// 나머지 계산
		while (!operList.isEmpty()) {
			numList.add(calculation(numList.pop(), numList.pop(), operList.pop()));
		}

		// 최종 값 return
		return numList.pop();
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
