
public class PM_2021KAKAOBLIND_����_���� {

	static int[] timeTable;

	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		// ������ ��� �ð�
		int playTime = stringToTime(play_time);
		// ���� ��� �ð�
		int advTime = stringToTime(adv_time);
		timeTable = new int[playTime + 1];

		for (String log : logs) {
			String[] tmp = log.split("-");
			int start = stringToTime(tmp[0]); // ���� �ð�
			int end = stringToTime(tmp[1]); // ���� �ð�

			// ��û �ð��� ��û�� ����
			for (int i = start; i < end; i++) {
				timeTable[i] += 1;
			}
		}

		// sumViewer : ���� ��û�� ���� ������
		long sumViewer = 0;
		for (int i = 0; i < advTime; i++) {
			sumViewer += timeTable[i];
		}

		// timeIndex : ���� ��û�� ���� �ִ��� ���� ���� ���۽ð�
		int timeIndex = 0;
		// maxViewer : ���� ��û�� ���� �ִ�
		long maxViewer = sumViewer;
		// ���� ��û�� �� �ʱⰪ ����
		for (int i = advTime; i < playTime; i++) {
			// ���� �� - ���� ���� �ð� + ���� ���� �ð�
			sumViewer += (timeTable[i] - timeTable[i - advTime]);
			// ���� ���� �ִ񰪺��� ũ�ٸ�
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
