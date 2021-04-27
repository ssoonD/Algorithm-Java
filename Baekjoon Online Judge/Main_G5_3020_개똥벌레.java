import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 요약]
 * 구해야 하는 것 : 개똥벌레가 파괴해야하는 장애물의 최솟값
 * 문제 유형 : 구간합
 * 
 * [풀이법 요약]
 * 동굴 행을 기준으로 코드를 작성
 * 1. 석순의 누적합 구하기
 * 2. 종유석의 누적합 구하기
 * 3. 1과 2의 누적합 구하기
 * 4. 3에서 구한 누적합으로 각 행에 해당하는 최솟값과 개수 구하기
 * 
 * 이 문제의 핵심은 문제를 보고 구간합을 생각하는것..!
 * 
 */

public class Main_G5_3020_개똥벌레 {

	static int N, H;
	// up : 석순 누적합
	// down : 종유석 누적합
	// pfs : 최종 누적합
	static int[] up, down, pfs;
	static int answer = Integer.MAX_VALUE, count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		up = new int[H];
		down = new int[H];
		pfs = new int[H];

		for (int i = 0; i < N / 2; i++) {
			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());
			down[H - a]++; // 석순
			up[b - 1]++; // 종유석
		}

		makePrefixSum();
		solve();

		System.out.println(answer + " " + count);
	}

	private static void solve() {
		for (int i = 0; i < H; i++) {
			if (pfs[i] < answer) { // 장애물의 개수가 더 작으면
				answer = pfs[i]; // 최솟값 저장
				count = 1; // count 초기화
			} else if (pfs[i] == answer) { // 장애물의 개수가 같으면
				count++; // 카운팅
			}
		}
	}

	// 누적합 만들기
	private static void makePrefixSum() {

		pfs[H - 1] += up[H - 1];
		// 종유석 누적
		// 최종 누적합 누적
		for (int i = H - 2; i >= 0; i--) {
			up[i] += up[i + 1];
			pfs[i] += up[i];
		}

		pfs[0] += down[0];
		// 석순 누적
		// 최종 누적합 누적
		for (int i = 1; i < H; i++) {
			down[i] += down[i - 1];
			pfs[i] += down[i];
		}

	}
}
