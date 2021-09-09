import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PM_2021KAKAOBLIND_메뉴_리뉴얼 {
	// Map<길이, Map<메뉴, 등장횟수>>
	static Map<Integer, Map<String, Integer>> courseMap = new HashMap<>();

	public String[] solution(String[] orders, int[] course) {
		// 코스 종류
		for (int cnt : course) {
			courseMap.put(cnt, new HashMap<>());
		}

		for (String order : orders) {
			char[] orderList = order.toCharArray();
			Arrays.sort(orderList);
			perm(orderList, "", 0);
		}

		List<String> answer = new ArrayList<>();
		for (Map.Entry<Integer, Map<String, Integer>> map : courseMap.entrySet()) {
			List<Map.Entry<String, Integer>> courseList = new ArrayList<>(map.getValue().entrySet());
			// 코스의 종류가 비어있을 때
			if (courseList.size() == 0) {
				continue;
			}
			// 개수로 정렬
			courseList.sort(Comparator.comparingInt(o -> o.getValue()));
			// 최댓값을 가지는 index
			int index = courseList.size() - 1;
			// 최댓 값
			int max = Math.max(courseList.get(index).getValue(), 2);

			while (index >= 0 && courseList.get(index).getValue() == max) {
				answer.add(courseList.get(index--).getKey());
			}
		}

		answer.sort(null);
		return answer.toArray(new String[0]);
	}

	private static void perm(char[] order, String course, int index) {
		if (courseMap.containsKey(course.length())) {
			int len = course.length();
			courseMap.get(len).put(course, courseMap.get(len).getOrDefault(course, 0) + 1);
		}
		for (int i = index; i < order.length; i++) {
			perm(order, course + order[i], i + 1);
		}
	}

	public static void main(String[] args) {
		PM_2021KAKAOBLIND_메뉴_리뉴얼 pm = new PM_2021KAKAOBLIND_메뉴_리뉴얼();
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };
		System.out.println(Arrays.toString(pm.solution(orders, course)));
	}

}
