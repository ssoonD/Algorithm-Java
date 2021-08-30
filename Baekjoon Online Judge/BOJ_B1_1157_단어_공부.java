import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_B1_1157_단어_공부 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		int[] wordCount = new int[26];
		word = word.toUpperCase();
		int len = word.length();
		for (int i = 0; i < len; i++) {
			wordCount[word.charAt(i) - 'A']++;
		}
		int max = -1, cnt = 0, index = -1;
		for (int i = 0; i < 26; i++) {
			if (max < wordCount[i]) {
				max = wordCount[i];
				index = i;
				cnt = 0;
			} else if (max == wordCount[i]) {
				cnt++;
			}
		}
		if (cnt == 0) {
			System.out.println((char) ('A' + index));
		} else {
			System.out.println("?");
		}
	}

}
