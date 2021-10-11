import java.util.*;

public class BaekJoon_14500 {
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	// ㅗ,ㅜ,ㅏ,ㅓ 순서
	static int[][] ex= {{1,1,1,0},{0,0,0,1},{0,1,2,1},{0,1,2,1}};
	static int[][] ey= {{0,1,2,1},{0,1,2,1},{0,0,0,1},{1,1,1,0}};
	static int[][] map;
	static int n,m,max=Integer.MIN_VALUE;
	static boolean[][] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		visited=new boolean[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j]=true;
				dfs(i,j,1,map[i][j]); // (i,j) 칸에서 탐색 시작
				visited[i][j]=false;
				
				ex_dfs(i,j); // 예외경우 -> ㅗ 모양
			}
		}
		System.out.println(max);
	}
	
	/*
	 *  ㅜ 모양의 폴리오미노를 뺀 나머지 모양에서 
	 *  놓인 칸의 쓰여있는 수들의 합이 최대인 경우 찾기
	 *  
	 *  ㅜ 모양을 뺀 나머지는 dfs로 현재 칸에서 동,서,남북을
	 *  탐색하는 과정에서 모두 도출될 수 있는 모양
	 */
	static void dfs(int x, int y, int depth, int cost) {
		// 4조각 모두 탐색했다면 최댓값 갱신
		if(depth==4) {
			max=Math.max(max, cost);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 범위를 초과했거나 이미 방문한 곳이라면 넘어간다.
			if(nx<0||ny<0||nx>=n||ny>=m||visited[nx][ny]) continue;
			
			visited[nx][ny]=true;
			dfs(nx,ny,depth+1,cost+map[nx][ny]);
			visited[nx][ny]=false; // 다음 경우의수를 위해 초기화
		}
	}
	
	// ㅜ 모양은 완전탐색으로 가능한 모든 경우의 수 찾기
	static void ex_dfs(int x, int y) {
		for(int i=0;i<4;i++) {
			int result=0; // 조각의 합

			for(int j=0;j<4;j++) {
				int nx=x+ex[i][j];
				int ny=y+ey[i][j];
				
				// 범위를 벗어났다면 넘어간다.
				if(nx<0||ny<0||nx>=n||ny>=m) 
					continue;
				
				// 칸에 적힌 숫자 누적
				result+=map[nx][ny];
			}

			max=Math.max(result, max); // 최댓값 갱신
		}
	}
}
