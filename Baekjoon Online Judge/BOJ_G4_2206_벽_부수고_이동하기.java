import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * <���� ���>
 * ���� ���� : �ִ� ���� �� �� ���� �μ� �� ���� ��, �ִ� ��� ���ϱ�
 * ���� ���� : bfs
 * 
 * �ݷ� 
 * 1 1
 * 0 
 * -> 1
 * 
 * <�ǵ��>
 * ����� ������ ���� �����ϱ� �ش� �˾Ƶ���~!
 */

public class BOJ_G4_2206_��_�μ���_�̵��ϱ� {

	static class Point {
		int x;
		int y;
		int dis; // �̵��Ÿ�
		int wall; // �� �μ���

		public Point(int x, int y, int dis, int wall) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.wall = wall;
		}
	}

	static int N, M;
	// check : ���� Ƚ�� ����
	static int[][] map, check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				check[i][j] = Integer.MAX_VALUE; // ���Ѵ�� �ʱ�ȭ
			}
		}

		// �̵��Ÿ� : 1
		// �� �μ��� : 0
		int answer = solve(new Point(0, 0, 1, 0));

		System.out.println(answer);
	}

	private static int solve(Point point) {
		Queue<Point> q = new LinkedList<>();
		q.offer(point);
		check[point.x][point.y] = 0;

		while (!q.isEmpty()) {
			Point now = q.poll();

			// �������� ��
			if (now.x == (N - 1) && now.y == (M - 1)) {
				return now.dis;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				// ������ ���� Ƚ���� ������ ������ ������ �۰ų� ������ pass~
				if (check[nx][ny] <= now.wall) {
					continue;
				}

				if (map[nx][ny] == 0) { // ���� �ƴ� ��
					check[nx][ny] = now.wall; // ���� Ƚ�� ����
					q.offer(new Point(nx, ny, now.dis + 1, now.wall));
				} else { // ���� ��
					if (now.wall == 0) {
						check[nx][ny] = now.wall + 1; // ���� Ƚ�� ����
						q.offer(new Point(nx, ny, now.dis + 1, now.wall + 1));
					}
				}
			}
		}

		return -1;
	}

}
