import java.util.*;

public class BaekJoon_11403 {
	static int n;
	static int[][] graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		graph=new int[n][n];
		
		for(int i=0;i<n;i++) 
			for(int j=0;j<n;j++) 
				graph[i][j]=sc.nextInt();
		
		// 플로이드 와샬 알고리즘
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					/*
					 * i에서 j로 가는 길을 다른 정점 k를 거쳐 갈 수 있다면
					 * 두 노드는 연결되어 있는 것이기 때문에 1로 변경
					 */
					if(graph[i][k]==1&&graph[k][j]==1)
						graph[i][j]=1;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
			
	}

}
