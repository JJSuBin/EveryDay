import java.util.*;

public class BaekJoon_14500 {
	static int n,m,max=Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int[][] ex= {{1,1,1,0},{0,0,0,1},{0,1,2,1},{0,1,2,1}}; // ㅗ,ㅜ,ㅏ,ㅓ 순서
	static int[][] ey= {{0,1,2,1},{0,1,2,1},{0,0,0,1},{1,1,1,0}};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		visited=new boolean[n][m];
		
		for(int i=0;i<n;i++) 
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(!visited[i][j]) {
					visited[i][j]=true;
					tetromimo(i,j,0,0);
					visited[i][j]=false;
				}
				
				ex_tetromimo(i,j);
			}
		}
		System.out.println(max);
	}
	
	// ㅜ 모양을 제외한 조각들은 dfs 탐색을 4 조각을 선택했을 때 모두 도출되는 모양
	static void tetromimo(int x, int y, int depth, int total) {
		if(depth==4) {
			max=Math.max(total,max);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx>=0&&ny>=0&&nx<n&&ny<m) {
				if(!visited[nx][ny]) {
					visited[nx][ny]=true;
					tetromimo(nx,ny,depth+1,total+map[nx][ny]);
					visited[nx][ny]=false;
				}
			}
		}
	}
	
	// ㅜ 모양은 dfs 탐색으로 나올 수 없는 모양이기 때문에 가능한 4가지 경우를 완전탐색으로 구한다.
	static void ex_tetromimo(int x, int y) {
		for(int i=0;i<4;i++) {
			int total=0;
			for(int j=0;j<4;j++) {
				int nx=x+ex[i][j];
				int ny=y+ey[i][j];
				
				if(nx>=0&&ny>=0&&nx<n&&ny<m) 
					total+=map[nx][ny];
			}
			
			max=Math.max(total, max);
		}
	}
}
