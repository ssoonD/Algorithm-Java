import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;

/*
 * <���� ���>
 * ���� ���� : �ֳʱ׷� ���α׷� �����
 * ���� ���� : next_permutation
 * 
 * <�ǵ��>
 * �ذ��־���... next_permutation
 * �ٽ� �����ؾ߰ڴ�
 * 
 */

public class BOJ_G4_6443_�ֳʱ׷� {

	static int N;
	static char[] word;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int t = 0; t < N; t++) {
			word = br.readLine().toCharArray();
			// ����
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
