import java.util.*;

/*
 * 이분그래프인지 확인하는 방법은 DFS/BFS로 모든 노드를 탐색하면서
 * 정점을 방문할 때마다 두 가지 색 중 하나를 칠한다. 
 * 이때 다음 정점의 색과 자신의 색이 같다면 이분그래프가 될 수 없는 경우이다. 
 */
public class BaekJoon_1707 {
	static ArrayList<ArrayList<Integer>> graph;
	static int v,e;
	static int[] colors; // 각 노드의 색을 저장하는 배역(0은 아직 아무색도 칠해지지 않은 경우, 1/-1로 색을 칠한다.)
	static boolean check; // 이분그래프인지 아닌지 확인하는 변수
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int testcase=sc.nextInt();
		
		while(testcase-->0) {
			graph=new ArrayList<>();
			v=sc.nextInt();
			e=sc.nextInt();
			colors=new int[v+1];
			check=true; // 초기에는 이분그래프가 맞다고 생각
			
			// 노드 추가
			for(int i=0;i<=v;i++) {
				graph.add(new ArrayList<>());
				colors[i]=0; // 아직 방문하지 않은 정점의 색은 0으로 초기화
			}
			
			// 간선 정보 입력
			for(int i=0;i<e;i++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				
				// 양방향
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			// 모든 노드 탐색
			for(int i=1;i<=v;i++) {
				// check 변수가 false면 이분그래프가 아니기 때문에 탐색 중지
				if(!check)
					break;
				// 아직 어떤 그룹에도 속해있지 않다면 dfs 수행
				if(colors[i]==0)
					dfs(i,1); // 첫 번째 정점을 1로 색칠하고 시작
			}
			
			System.out.println(check?"YES":"NO");
		}
	}
	static void dfs(int start, int color) {
		colors[start]=color; // 시작 노드는 1로 색칠한다.
		
		// 시작노드와 연결된 모든 노드 탐색
		for(int i=0;i<graph.get(start).size();i++) {
			int temp=graph.get(start).get(i); // 다음 노드
			
			// 자기 자신의 색과 다음 노드의 색이 같다면 이분그래프 X
			if(colors[start]==colors[temp]) {
				check=false;
				return;
			}
			
			// 아직 색이 칠해지지 않은 경우는 자신과 다른 색을 칠한다. 
			if(colors[temp]==0)
				dfs(temp,-color);
		}
	}
}
