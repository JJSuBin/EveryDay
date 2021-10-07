import java.util.*;

public class BaekJoon_14620 {
	static int[][] map;
	static boolean[][] visited;
	static int n,min=Integer.MAX_VALUE;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		map=new int[n][n];
		visited=new boolean[n][n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				map[i][j]=sc.nextInt();
		
		dfs(0,0);
		System.out.println(min);
	}
	
	// 씨앗을 3개 심는 함수
	static void dfs(int depth, int cost) {
		// 씨앗 3개를 모두 가능하게 심었다면 최소비용 갱신
		if(depth==3) {
			min=Math.min(min, cost);
			return;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				// (i,j)칸에 씨앗을 심을수 있다면 
				if(isPossible(i,j)) {
					int ex=expense(i,j); // 비용계산
					
					// 씨앗과 꽆잎 방문처리
					visited[i][j]=true;
					for(int d=0;d<4;d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						
						visited[nx][ny]=true;
					}
					
					// 씨앗 개수 +1, 누적비용 계산 후 재귀호출
					dfs(depth+1,cost+ex);
					
					// 다음 경우를 위해 방문처리한 값 모두 초기화
					visited[i][j]=false;
					for(int d=0;d<4;d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						
						visited[nx][ny]=false;
					}
				}
			}
		}
	}
	
	// 씨앗을 심을수 있는지 확인 -> 꽃이 폈을때 꽃잎까지 모두 배열 범위 초과 X, 겹치면 X
	static boolean isPossible(int x, int y) {
		// 씨앗 자리 체크
		if(visited[x][y])
			return false;
		
		// 꽃잎 자리 체크
		for(int d=0;d<4;d++) {
			int nx=x+dx[d];
			int ny=y+dy[d];
			
			if(nx<0||ny<0||nx>=n||ny>=n)
				return false;
			
			if(visited[nx][ny])
				return false;
		}
		
		return true;
	}
	
	// 비용 구하는 함수
	static int expense(int x, int y) {
		int total=map[x][y]; // 씨앗
		
		// 꽃잎
		for(int d=0;d<4;d++) {
			int nx=x+dx[d];
			int ny=y+dy[d];
			
			total+=map[nx][ny];
		}
		
		return total;
	}
}
