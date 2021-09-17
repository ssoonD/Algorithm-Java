import java.io.*;
import java.util.*;

/*
 * <문제 요약>
 * 문제 정의 : 정상 T에 오르는 데까지 걸리는 이동 횟수의 최솟값
 * 문제 유형 : bfs
 * 유의 사항 : 처음에 (0,0)을 list에 넣고 시작한다
 * 
 * <풀이법 요약>
 * list에 저장된 index를 가지고  비교해준다
 * check[index] : index에 해당하는 지점에 도달하는 이동 횟수의 최솟값
 * 
 * 1. x 기준으로 정렬
 * 2. index 기준 왼쪽 탐색 - x 좌표 기준으로 탐색
 * 2-1. 탐색하다가 2초과가 되는 순간 break
 * 2-2. 방문하지 않은 지점(check[i]!=-1) 또는 y좌표가 2초과 -> continue 
 * 2-3. 모든 조건 통과시 index에 해당하는 이동 횟수 +1한 값을 저장
 * 3. index 기준 오른쪽 탐색 - x 좌표 기준으로 탐색
 * (2-1 ~ 2-3) 반복
 * 4. y 좌표가 T가 되는 순간 check[index] 출력
 * 
 * <피드백>
 * 처음에 (0,0)넣고 시작하는거 생각 안 해서 조금 틀렸다..
 * 
 */

public class BOJ_G3_2412_암벽_등반 {

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}

	}

	static int n, T;
	// 각 node별 방문 기록 (몇번째로 방문했는지)
	static int[] check;
	// node 저장
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		check = new int[n + 1];

		// 시작 지점 넣어주기
		list.add(new Point(0, 0));
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Point(x, y));
		}

		// 정렬
		Collections.sort(list);
		// 초기화
		Arrays.fill(check, -1);

		System.out.println(solve());
	}

	private static int solve() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		check[0] = 0;

		while (!q.isEmpty()) {
			int now = q.poll();

			// T까지 도달했을 때 출력
			if (list.get(now).y == T) {
				return check[now];
			}

			// 왼쪽 탐색
			for (int i = now - 1; i > 0; i--) {
				// x 좌표 비교값이 2 초과일 때 -> break;
				if (!chkAbs(list.get(now).x, list.get(i).x)) {
					break;
				}
				// 방문한 좌표일 때, y 좌표 비교값이 2 초과일 때 -> continue;
				if (check[i] != -1 || !chkAbs(list.get(now).y, list.get(i).y)) {
					continue;
				}
				check[i] = check[now] + 1;
				q.offer(i);
			}

			// 오른쪽 탐색
			for (int i = now + 1; i <= n; i++) {
				// x 좌표 비교값이 2 초과일 때 -> break;
				if (!chkAbs(list.get(now).x, list.get(i).x)) {
					break;
				}
				// 방문한 좌표일 때, y 좌표 비교값이 2 초과일 때 -> continue;
				if (check[i] != -1 || !chkAbs(list.get(now).y, list.get(i).y)) {
					continue;
				}
				check[i] = check[now] + 1;
				q.offer(i);
			}
		}

		return -1;
	}

	private static boolean chkAbs(int a, int b) {
		return Math.abs(a - b) <= 2;
	}

}
