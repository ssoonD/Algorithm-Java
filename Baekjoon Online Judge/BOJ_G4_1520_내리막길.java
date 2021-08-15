import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* (�̿�)
 * <���� ���>
 * ���� ���� : (0,0)���� (N-1,M-1)�� �̵��ϴ� ����� �� + ���� ��ġ���� �� ���� ������ �̵� �����ϴ�
 * ���� ���� : dfs + dp
 * 
 * <�ǵ��>
 * dfs + dp ������ �ʹ� ��ƴ�... ���߿� �ٽ� Ǯ������� ����
 * 
 */

public class BOJ_G4_1520_�������� {

	static int N, M;
	static int[][] map, d;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		d = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(d[i], -1);
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(go(0, 0));
	}

	private static long go(int x, int y) {
		// ������ ���� -> Ž�� �ʿ� ����
		if (x == N - 1 && y == M - 1) {
			return 1;
		}
		// -1�� �ƴ϶�� -> �̹� �湮
		if (d[x][y] != -1) {
			return d[x][y];
		}
		// �湮 ǥ��
		d[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
				continue;
			}
			// �̵��� ������ ��� -> ���� ��ġ�� �� ū ���
			if (map[nx][ny] < map[x][y]) {
				d[x][y] += go(nx, ny);
			}
		}

		return d[x][y];
	}

}
