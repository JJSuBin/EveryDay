import java.util.*;

public class BaekJoon_9372 {
	static int n;
	static boolean[] visited;
	static int[][] graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		while(t-->0) {
			n=sc.nextInt();
			int m=sc.nextInt();
			
			visited=new boolean[n+1];
			graph=new int[n+1][n+1];
			
			for(int i=0;i<m;i++) {
				int start=sc.nextInt();
				int end=sc.nextInt();
				
				// 양방향
				graph[start][end]=graph[end][start]=1;
			}
			
			System.out.println(bfs());
		}
	}
	
	// 구해야 하는 것은 노드를 잇는 간선의 개수와 같다. 시작 지점 상관 X
	static int bfs() {
		Queue<Integer> q=new LinkedList<>();
		q.offer(1); // 1에서 시작
		visited[1]=true; // 방문처리
		int count=0; // 거쳐간 간선의 개수 count
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			for(int i=2;i<=n;i++) {
				if(graph[now][i]==1&&!visited[i]) {
					count++;
					visited[i]=true;
					q.offer(i);
				}
			}
		}
		return count;
	}

}
