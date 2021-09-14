import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_S1_18222_투에모스_문자열 {

	static long k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Long.parseLong(br.readLine()) - 1;

		String str = Long.toBinaryString(k);

		int count = 0;
		char[] num = new char[str.length()];
		for (int i = 0; i < num.length; i++) {
			num[i] = str.charAt(i);
			if (num[i] == '1') {
				count++;
			}
		}

		if (count % 2 == 0) {
			System.out.println('0');
		} else {
			System.out.println('1');
		}
	}

}
