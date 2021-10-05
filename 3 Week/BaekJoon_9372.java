import java.util.*;

public class BaekJoon_9372 {
	static int n,m;
	static boolean[] visited;
	static int[][] graph;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int testcase=sc.nextInt();
		
		while(testcase-->0) {
			n=sc.nextInt();
			m=sc.nextInt();
			
			visited=new boolean[n+1];
			graph=new int[n+1][n+1];
			
			for(int i=0;i<m;i++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				
				// 양방향
				graph[a][b]=graph[b][a]=1;
			}
			
			int result=airplane();
			System.out.println(result);
		}
	}
	
	// 구해야 하는 것은 노드를 잇는 간선의 개수와 같다. 시작 지점 상관 X
	static int airplane() {
		Queue<Integer> q=new LinkedList<>();
		q.offer(1);
		visited[1]=true;
		int count=0;
		
		while(!q.isEmpty()) {
			int prev=q.poll();
			
			// 이어져있는 노드 탐색
			for(int i=2;i<=n;i++) {
				if(graph[prev][i]==1&&!visited[i]) {
					count++; // 간선 개수 count
					visited[i]=true; // 방문처리
					q.offer(i);
				}
			}
		}
		
		return count;
	}
}
