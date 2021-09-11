
public class PM_2021KAKAOBLIND_신규_아이디_추천 {

	public String solution(String new_id) {
		StringBuilder answer = new StringBuilder();

		// 1단계 - 대문자 -> 소문자
		new_id = new_id.toLowerCase();

		// 2단계 - 문자 제거 + 3단계 - 2개 이상이면 치환
		int len = new_id.length();
		boolean chk = false;
		for (int i = 0; i < len; i++) {
			char ch = new_id.charAt(i);
			if (Character.isLowerCase(ch) || Character.isDigit(ch) || ch == '-' || ch == '_') {
				answer.append(ch);
				chk = false;
			} else if (ch == '.') {
				if (!chk) {
					answer.append(ch);
				}
				chk = true;
			}
		}

		// 4단계 - 앞 뒤 .을 제거
		if (answer.length() != 0 && answer.charAt(0) == '.') {
			answer.deleteCharAt(0);
		}
		if (answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') {
			answer.deleteCharAt(answer.length() - 1);
		}

		// 5단계 - 빈문자열 a 추가
		if (answer.length() == 0) {
			answer.append('a');
		}

		// 6단계 - 16이상 자르기
		if (answer.length() > 15) {
			answer = new StringBuilder(answer.substring(0, 15));
			if (answer.charAt(14) == '.') {
				answer.deleteCharAt(14);
			}
		}

		// 7단계 - 최소 길이 3 만들어주기
		if (answer.length() < 3) {
			char last = answer.charAt(answer.length() - 1);
			len = 3 - answer.length();
			for (int i = 0; i < len; i++) {
				answer.append(last);
			}
		}

		return answer.toString();
	}

	public static void main(String[] args) {
		PM_2021KAKAOBLIND_신규_아이디_추천 pm = new PM_2021KAKAOBLIND_신규_아이디_추천();
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(pm.solution(new_id));
	}

}
