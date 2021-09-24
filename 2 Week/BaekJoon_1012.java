import java.util.*;

public class BaekJoon_1012 {
	static int[][] map;
	static boolean[][] visited;
	static int m,n,k,result;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int testcase=sc.nextInt();
		
		while(testcase-->0) {
			n=sc.nextInt();
			m=sc.nextInt();
			k=sc.nextInt();
			
			visited=new boolean[n][m];
			map=new int[n][m];
			result=0;
			
			for(int i=0;i<k;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				
				map[x][y]=1; // 배추가 있는 곳 표시
			}
			
			// 배추의 집단이 총 몇개 있는지 찾는 문제
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					// 아직 방문하지 않은 배추가 있는 곳이라면 dfs 수행
					if(!visited[i][j]&&map[i][j]==1) {
						dfs(i,j);
						result+=1; // dfs가 한번 수행될 때 마다 배추의 집단 count
					}
				}
			}
			System.out.println(result);
		}
	}
	
	// dfs를 사용하여 배추가 있는 집단의 visited 값을 true로 바꿔준다
	static void dfs(int x, int y) {
		visited[x][y]=true;
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx<n&&ny<m&&nx>=0&&ny>=0) 
				if(!visited[nx][ny]&&map[nx][ny]==1)
					dfs(nx,ny);
		}
	}
}
