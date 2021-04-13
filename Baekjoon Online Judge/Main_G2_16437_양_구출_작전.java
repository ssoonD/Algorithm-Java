import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 구출할 수 있는 양의 수
 * 문제 유형 : 트리 후위순회
 * 
 * <풀이법 요약>
 * 단말 노드까지 내려가서 부모 노드로 올라오면서 계산해준다
 * 
 * 처음에 dfs로 풀었더니 싹 틀렸음! 예제는 정말 기가맥히게 만드는듯@@
 * 구선생의 도움을 빌렸음!
 * 아이디어 줍줍
 */
public class Main_G2_16437_양_구출_작전 {

	// type : 양 / 늑대
	// index : 노드 번호
	// weight : 동물 마릿수
	static class Node {
		char type;
		int index;
		int weight;

		public Node(char type, int index, int weight) {
			this.type = type;
			this.index = index;
			this.weight = weight;
		}

	}

	// N : 섬의 개수
	static int N;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char t = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken()) - 1;
			graph.get(p).add(new Node(t, i, a));
		}

		System.out.println(postOrder(new Node('S', 0, 0)));
	}

	// 트리 후위순회 
	private static long postOrder(Node now) {
		long answer = 0L;
		// 단말 노드까지 내려가기
		for (Node next : graph.get(now.index)) {
			answer += postOrder(next);
		}
		if (now.type == 'S') { // 양일 때
			return answer + now.weight; // 현재 마릿수 더해주기
		} else { // 늑대일 때
			// 지금까지 구한 마릿수에서 늑대 마릿수를 뺀 값이 0보다 작으면 0으로 만들어준다!
			return (answer - now.weight) >= 0 ? (answer - now.weight) : 0;
		}
	}

}
