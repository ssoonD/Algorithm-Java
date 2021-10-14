import java.util.Arrays;

public class PM_DevMatching2021상_행렬_테투리_회전하기 {

	static int[][] map;
	static int minNum;

	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		map = new int[rows][columns];
		int num = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = num;
				num++;
			}
		}

		int index = 0;
		for (int[] query : queries) {
			minNum = rows * columns + 1;
			rotate(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
			answer[index++] = minNum;
		}

		return answer;
	}

	static void rotate(int x1, int y1, int x2, int y2) {
		int next = map[x1][y1], tmp = 0;

		// 동
		for (int j = y1; j < y2; j++) {
			minNum = Math.min(minNum, next);
			tmp = map[x1][j + 1];
			map[x1][j + 1] = next;
			next = tmp;
		}

		// 서
		for (int i = x1; i < x2; i++) {
			minNum = Math.min(minNum, next);
			tmp = map[i + 1][y2];
			map[i + 1][y2] = next;
			next = tmp;
		}

		// 남
		for (int j = y2; j > y1; j--) {
			minNum = Math.min(minNum, next);
			tmp = map[x2][j - 1];
			map[x2][j - 1] = next;
			next = tmp;
		}

		// 북
		for (int i = x2; i > x1; i--) {
			minNum = Math.min(minNum, next);
			tmp = map[i - 1][y1];
			map[i - 1][y1] = next;
			next = tmp;
		}
	}

	static void print(int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		PM_DevMatching2021상_행렬_테투리_회전하기 p = new PM_DevMatching2021상_행렬_테투리_회전하기();
		int rows = 6;
		int columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		System.out.println(Arrays.toString(p.solution(rows, columns, queries)));
	}

}
