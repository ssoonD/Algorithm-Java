import java.util.Arrays;

/*
 * [문제 요약]
 * 구해야 하는 것 : 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
 * 문제 유형 : 이분 탐색
 * 
 * [풀이법 요약]
 * - 시간을 기준으로 이분탐색을 진행한다
 * - 해당 시간동안 통과 가능한 사람의 수로 조건을 걸어준다
 * 
 * long으로 다 바꿔주는거 왜일까??
 * 이거때문에 오래걸림..
 */

public class PM_L3_이분탐색_입국심사 {

	public long solution(int n, int[] times) {
		// 이분 탐색을 위한 정렬
		Arrays.sort(times); 
		
		// 값 초기화
		long left = 1;
		long right = (long)times[times.length - 1] * n;
		long answer = right;

		// 이분 탐색
		while (left <= right) {
			long mid = (left + right) / 2;
			// 입국 심사를 통과한 사람의 수
			long count = 0; 
			// 해당 시간동안 통과 가능한 사람의 수 계산
			for (Integer time : times) {
				count += (mid / time);
			}
			// 통과한 사람의 수가 승객의 수보다 많다면
			if (count >= n) {
				right = mid - 1; // 왼쪽 선택 (줄이기)
				answer = Math.min(answer, mid);
			} else { // 통과한 사람의 수가 승객의 수보다 적다면
				left = mid + 1; // 오른쪽 선택 (늘리기)
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		PM_L3_이분탐색_입국심사 pm = new PM_L3_이분탐색_입국심사();
		int n = 6;
		int[] times = { 7, 10 };
		System.out.println(pm.solution(n, times));
	}

}
