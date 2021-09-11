
public class PM_2021KAKAOBLIND_�ű�_���̵�_��õ {

	public String solution(String new_id) {
		StringBuilder answer = new StringBuilder();

		// 1�ܰ� - �빮�� -> �ҹ���
		new_id = new_id.toLowerCase();

		// 2�ܰ� - ���� ���� + 3�ܰ� - 2�� �̻��̸� ġȯ
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

		// 4�ܰ� - �� �� .�� ����
		if (answer.length() != 0 && answer.charAt(0) == '.') {
			answer.deleteCharAt(0);
		}
		if (answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') {
			answer.deleteCharAt(answer.length() - 1);
		}

		// 5�ܰ� - ���ڿ� a �߰�
		if (answer.length() == 0) {
			answer.append('a');
		}

		// 6�ܰ� - 16�̻� �ڸ���
		if (answer.length() > 15) {
			answer = new StringBuilder(answer.substring(0, 15));
			if (answer.charAt(14) == '.') {
				answer.deleteCharAt(14);
			}
		}

		// 7�ܰ� - �ּ� ���� 3 ������ֱ�
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
		PM_2021KAKAOBLIND_�ű�_���̵�_��õ pm = new PM_2021KAKAOBLIND_�ű�_���̵�_��õ();
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(pm.solution(new_id));
	}

}
