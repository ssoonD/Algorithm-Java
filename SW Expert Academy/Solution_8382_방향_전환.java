import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : (x1, y1)에서 (x2, y2)로 이동하기 위한 최소 이동 횟수 
 * 문제 핵심 요약 : 차이값을 이용 + 홀수 / 짝수 구분 
 * 
 * <풀이법 요약> 
 * 1. x,y각각 좌표의 차이를 저장한다. 
 * 2. 차이가 같다면 차이X2를 return 한다. 
 * 3. 차이 중 작은 값을 저장한다. - (1) 
 * 4. x,y 각각 좌표 차이의 "차이"를 저장한다. -(2) 
 * 5. (1)은 x,y모두 가야하기 때문에 X2를 해준다. 
 * 6-1. (2)는 짝수일 때 X2번 
 * 6-2. (2)는 홀수일 때 X2-1번 
 * 6-3. 계산한 값을 return 한다.
 */

public class Solution_8382_방향_전환 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int difX = Math.abs(x1 - x2); // X 좌표의 차이
			int difY = Math.abs(y1 - y2); // Y 좌표의 차이
			if (difX == difY) { // 만약 차이가 같다면
				answer = difX * 2; // 차이X2
			} else { // 차이가 같지 않다면
				int min = Math.min(difX, difY); // 차이 중 작은 값 저장 - (1)
				int difLen = Math.abs(difX - difY); // "차이"의 차이 - (2)
				// (1)만큼은 무조건 X,Y둘 다 가야하기 때문에 X2를 해준다.
				// (2)는 짝수일 때 X2번 홀수일 때 X2-1번 가기 때문에 판별이 필요하다.
				answer = min * 2 + difLen * 2 - (difLen % 2);
			}

			System.out.printf("#%d %d\n", t, answer);
		}
	}

}
