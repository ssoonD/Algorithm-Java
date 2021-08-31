import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_B2_5622_´ÙÀÌ¾ó {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int len = line.length();
		int answer = 0;
		for (int i = 0; i < len; i++) {
			answer += findIndex(line.charAt(i));
		}
		System.out.println(answer);
	}

	private static int findIndex(char word) {
		int[] dial = { 0, 3, 6, 9, 12, 15, 19, 22 };
		int index = word - 'A';
		int answer = 7;
		for (int i = 0; i < 8; i++) {
			if (index < dial[i]) {
				answer = i - 1;
				break;
			}
		}
		return answer + 3;
	}

}
