
public class PM_2021KAKAOBLIND_광고_삽입 {

	static int[] timeTable;

	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		// 동영상 재생 시간
		int playTime = stringToTime(play_time);
		// 광고 재생 시간
		int advTime = stringToTime(adv_time);
		timeTable = new int[playTime + 1];

		for (String log : logs) {
			String[] tmp = log.split("-");
			int start = stringToTime(tmp[0]); // 시작 시간
			int end = stringToTime(tmp[1]); // 종료 시간

			// 시청 시간내 시청자 누적
			for (int i = start; i < end; i++) {
				timeTable[i] += 1;
			}
		}

		// sumViewer : 광고 시청자 수의 누적값
		long sumViewer = 0;
		for (int i = 0; i < advTime; i++) {
			sumViewer += timeTable[i];
		}

		// timeIndex : 광고 시청자 수가 최대일 때의 광고 시작시간
		int timeIndex = 0;
		// maxViewer : 광고 시청자 수의 최댓값
		long maxViewer = sumViewer;
		// 광고 시청자 수 초기값 설정
		for (int i = advTime; i < playTime; i++) {
			// 누적 합 - 이전 시작 시간 + 현재 종료 시간
			sumViewer += (timeTable[i] - timeTable[i - advTime]);
			// 누적 합이 최댓값보다 크다면
			if (sumViewer > maxViewer) {
				maxViewer = sumViewer;
				timeIndex = i - advTime + 1;
			}
		}

		return timeToString(timeIndex);
	}

	private static int stringToTime(String log) {
		String[] time = log.split(":");
		return Integer.parseInt(time[0]) * 60 * 60 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
	}

	private static String timeToString(int time) {
		return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
	}

	public static void main(String[] args) {

	}

}
