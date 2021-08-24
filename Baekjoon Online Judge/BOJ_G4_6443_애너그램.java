import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;

/*
 * <문제 요약>
 * 문제 정의 : 애너그램 프로그램 만들기
 * 문제 유형 : next_permutation
 * 
 * <피드백>
 * 잊고있었다... next_permutation
 * 다시 정리해야겠다
 * 
 */

public class BOJ_G4_6443_애너그램 {

	static int N;
	static char[] word;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int t = 0; t < N; t++) {
			word = br.readLine().toCharArray();
			// 정렬
			Arrays.sort(word);
			
			do {
				for(char alpha : word) {
					answer.append(alpha);
				}
				answer.append('\n');
			}while(next_permutation());
		}
		
		System.out.println(answer);
	}

	private static boolean next_permutation() {
		int i = word.length - 1;
		while (i > 0 && word[i] <= word[i - 1]) {
			i--;
		}
		if (i <= 0) {
			return false;
		}

		int j = word.length - 1;
		while (word[i - 1] >= word[j]) {
			j--;
		}

		char tmp = word[i - 1];
		word[i - 1] = word[j];
		word[j] = tmp;

		int k = word.length - 1;
		while (i < k) {
			tmp = word[i];
			word[i] = word[k];
			word[k] = tmp;
			i++;
			k--;
		}

		return true;
	}

}
