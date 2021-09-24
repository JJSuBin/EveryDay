import java.util.*;

class Point_7576 {
	int x,y;
	
	public Point_7576(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_7576 {
	static int[][] map;
	static int m,n;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static Queue<Point_7576> q=new LinkedList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		m=sc.nextInt();
		n=sc.nextInt();
		map=new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();
				
				// 초기 토마토의 위치 큐에 삽입
				if(map[i][j]==1)
					q.offer(new Point_7576(i,j));
			}
		}
		
		bfs(); // 토마도 익히기
		
		// 배열값 중 최댓값이 토마토가 익는데 소요된 시간
		int max=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) { // 익지 않은 토마토가 있는 경우
					System.out.println(-1);
					System.exit(0);
				}
				max=Math.max(max,map[i][j]);
			}
		}
		
		// 처음 토마토의 배열 값이 1이었기 때문에 해당 값을 빼준다.
		System.out.println(max-1);
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			Point_7576 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<n&&ny<m) {
					if(map[nx][ny]==0) {
						// 일수 계산을 위해 이전 배열 값 +1을 해준다.
						map[nx][ny]=map[p.x][p.y]+1; 
						q.offer(new Point_7576(nx,ny));
					}
				}
			}
		}
	}
}
