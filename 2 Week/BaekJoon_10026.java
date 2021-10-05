import java.util.*;

public class BaekJoon_10026 {
	static int n,nor=0,abnor=0;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		map=new char[n][n];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<n;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		visited=new boolean[n][n];
		// 일반적인 경우 
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {
					check(i,j);
					nor++;
				}
			}
		}
		
		// 방문여부 체크 배열 초기화
		visited=new boolean[n][n];
		/*
		 * 적록색약이 있는 사람들은 초록색과 빨간색을 구분할 수 없기 때문에 
		 * 초록색을 모두 빨간색으로 바꿔 구역의 개수를 구한다.
		 */
		for(int i=0;i<n;i++) 
			for(int j=0;j<n;j++) 
				if(map[i][j]=='G')
					map[i][j]='R';
		
		// 적록색약이 있는 경우
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {
					check(i,j);
					abnor++;
				}
			}
		}

		System.out.println(nor+" "+abnor);
	}
	
	// (x,y) 칸을 시작으로 주위에 같은 색인 칸들을 방문처리하여 하나의 구역으로 만드는 함수
	static void check(int x, int y) {
		visited[x][y]=true; // 방문처리
		char color=map[x][y];
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 배열 범위를 초과하거나 이미 방문했던 칸은 패스
			if(nx<0||ny<0||nx>=n||ny>=n||visited[nx][ny])
				continue;
			
			// 배열 (x,y) 칸의 색과 같은 경우 재귀호출 
			if(map[nx][ny]==color)
				check(nx,ny);
		}
	}
}
