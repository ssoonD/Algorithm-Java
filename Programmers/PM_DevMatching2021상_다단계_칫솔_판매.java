import java.util.*;

public class PM_DevMatching2021상_다단계_칫솔_판매 {

	static Map<String, String> node = new HashMap<>();
	static Map<String, Integer> money = new HashMap<>();

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int len = enroll.length;
		int[] answer = new int[len];
		for (int i = 0; i < len; i++) {
			node.put(enroll[i], referral[i]);
		}

		len = seller.length;
		for (int i = 0; i < len; i++) {
			int totalMoney = amount[i] * 100;
			int divMoney = totalMoney / 10;
			String nowSeller = seller[i];
			while (totalMoney > 0 && !nowSeller.equals("-")) {
				money.put(nowSeller, money.getOrDefault(nowSeller, 0) + totalMoney - divMoney);
				totalMoney = divMoney;
				divMoney = totalMoney / 10;
				nowSeller = node.get(nowSeller);
			}
		}

		len = enroll.length;
		for (int i = 0; i < len; i++) {
			answer[i] = money.getOrDefault(enroll[i], 0);
		}

		return answer;
	}

	static void print() {
		for (String key : money.keySet()) {
			System.out.println(key + " " + money.get(key));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		PM_DevMatching2021상_다단계_칫솔_판매 p = new PM_DevMatching2021상_다단계_칫솔_판매();
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };
		System.out.println(Arrays.toString(p.solution(enroll, referral, seller, amount)));
	}

}
