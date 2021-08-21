import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ��簡 ���ִ��� �����ϰ� ������ �� �ִ���, �ִٸ� �󸶳� ���� ���� �� �ִ���
 * ���� ���� : bfs + �ݷ�ã��
 * 
 * <�ǵ��>
 * 17%���� �ڲ� Ʋ���� �ݷʸ� ã�ƺ��� !!!!!!!�� ������ -> �˰��� �ٽ�«
 * 
 * �ݷ�
 * 4 10 100
 * 0 1 1 1 1 2 1 1 1 1
 * 0 0 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1 1 1
 * 0 0 0 0 0 0 0 0 0 0
 * 
 * �׶��� �߰��ߴٸ� �̹� �̵��ߴ� ���� ������ �̵��ؾ��Ѵ� 
 * -> �� �ݷʸ� �߰��ϴ� ���� �ٽ��ε� 
 * -> visited�� 3�������� ���������Ѵ�!
 */

public class BOJ_G5_17836_���ִ���_���ض� {

	static class Point {
		int x;
		int y;
		int cnt;
		boolean check;

		public Point(int x, int y, int cnt, boolean check) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.check = check;
		}

	}

	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// �׶��� ���� �� : 1
		// �׶��� ���� �� : 0
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs(0, 0);
		if (answer != -1) {
			System.out.println(answer);
		} else {
			System.out.println("Fail");
		}

	}

	private static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, 0, false));
		visited[x][y][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();

			if (now.cnt > T) { // T���� ũ�ٸ� return -1
				return -1;
			}
			if (now.x == N - 1 && now.y == M - 1) { // �������� �����ߴٸ� return cnt
				return now.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (now.check) { // �׶� ���� ��
					if (!visited[nx][ny][1]) { // �湮���� �ʾҴٸ�
						q.add(new Point(nx, ny, now.cnt + 1, now.check));
						visited[nx][ny][1] = true;
					}
				} else { // �׶��� ���� ��
					if (visited[nx][ny][0] || map[nx][ny] == 1) { // �湮�߰ų� ���̸� continue
						continue;
					}
					boolean tmpCheck = now.check; // ���� ��ġ�� �׶� ���� ����
					if (map[nx][ny] == 2) { // ���� ���� ��ġ�� �׶��̶��
						tmpCheck = true; // true�� ����
					}
					q.add(new Point(nx, ny, now.cnt + 1, tmpCheck));
					visited[nx][ny][0] = true;
				}

			}
		}

		return -1;
	}

}
