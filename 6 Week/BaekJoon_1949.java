import java.util.*;

public class BaekJoon_1949 {
	static int n;
	static int[] people;
	static int[][] dp;
	static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		people=new int[n+1];
		
		/*
		 * dp[n][0] : n번 마을이 우수 마을이 아닐때, n번 마을을 루트노드로 하는 하위트리의 마을 주민 수
		 * dp[n][1] : n번 마을이 우수 마을일 때, n번 마을을 루트노드로 하는 하위트리의 마을 주민 수
		 */
		dp=new int[n+1][2];
		
		for(int i=0;i<=n;i++)
			graph.add(new ArrayList<>());
		
		for(int i=1;i<=n;i++)
			people[i]=sc.nextInt();
		
		for(int i=0;i<n-1;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			// 양방향
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(1,0);
		System.out.println(Math.max(dp[1][0],dp[1][1]));
	}
	
	static void dfs(int now, int parent) {
		for(int child:graph.get(now)) {
			if(child!=parent) {
				dfs(child,now); // dfs를 수행하면서 맨 아래에 도달해 트리의 아래에서 위로 올라오면서 dp 값을 구한다
				dp[now][1]+=dp[child][0]; // now 노드가 우수마을로 선정되면 자식 노드는 우수마을로 선정될 수 없다.
				/*
				 * now 노드가 우수마을로 선정되지 않았을 경우 자식 노드중 몇 개의 노드가 우수마을이 되도 상관 없다.
				 * 자신의 자식 노드를 모두 탐색하면서 두 가지의 경우 중 큰 값의 누적 합을 구한다. 
				 */
				dp[now][0]+=Math.max(dp[child][0],dp[child][1]);
				
			}
		}
		/*
		 * dfs를 수행하다 트리의 맨 아래에 도달하면 해당 노드는 자식노드가 없기 때문에 
		 * 우수마을로 선정될 경우만 자신의 인구 수를 저장하고, 우수마을로 선정되지 못한다면 기존 값 0을 가져간다.
		 */
		dp[now][1]+=people[now];
	}
}
