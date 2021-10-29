import java.util.*;

class Point_13460 {
	int rx, ry, bx, by, count;
	
	public Point_13460(int rx, int ry, int bx, int by, int count) {
		this.rx=rx;
		this.ry=ry;
		this.bx=bx;
		this.by=by;
		this.count=count;
	}
}

public class BaekJoon_13460 {
	static char[][] map;
	static boolean[][][][] visited;
	static int n,m,holeX,holeY;
	static int[] dx = {-1,0,1,0}; // 상,우,하,좌 순
	static int[] dy = {0,1,0,-1};
	static Point_13460 red,blue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new char[n][m];
		visited=new boolean[n][m][n][m];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j);
				
				if(map[i][j]=='O') {
					holeX=i; 
					holeY=j;
				}
				
				else if(map[i][j]=='R')
					red=new Point_13460(i,j,0,0,0);
				else if(map[i][j]=='B')
					blue=new Point_13460(0,0,i,j,0);
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Point_13460> q=new LinkedList<>();
		q.offer(new Point_13460(red.rx,red.ry,blue.bx,blue.by,1)); // 처음 빨간색, 파란색 구슬 위치 저장
		visited[red.rx][red.ry][blue.bx][blue.by]=true;
		
		while(!q.isEmpty()) {
			Point_13460 p=q.poll();
			
			int cur_rx=p.rx;
			int cur_ry=p.ry;
			int cur_bx=p.bx;
			int cur_by=p.by;
			int count=p.count;
			
			if(count>10) // 이동횟수가 10 초과시 실패
				return -1;
			
			// 상, 우, 하, 좌 순으로 탐색
			for(int i=0;i<4;i++) {
				int nrx=cur_rx;
				int nry=cur_ry;
				int nbx=cur_bx;
				int nby=cur_by;
				
				boolean isRedHole=false; // 공이 구멍에 빠졌는지 체크하는 변수
				boolean isBlueHole=false;
				
				// 빨간 구슬을 벽을 만나기 전까지 i방향으로 이동
				while(map[nrx+dx[i]][nry+dy[i]]!='#') {
					nrx+=dx[i];
					nry+=dy[i];
					
					// 이동시키다 벽을 만난 경우 체크 변수를 변경하고 종료
					if(nrx==holeX&&nry==holeY) {
						isRedHole=true;
						break;
					}
				}
				
				// 파란 구슬도 벽을 만나기 전까지 i방향으로 이동
				while(map[nbx+dx[i]][nby+dy[i]]!='#') {
					nbx+=dx[i];
					nby+=dy[i];
					
					// 이동시키가 벽을 만난 경우 체크 변수를 변경하고 종료
					if(nbx==holeX&&nby==holeY) {
						isBlueHole=true;
						break;
					}
				}
				
				if(isBlueHole) // 파란 구슬이 빠진 경우 게임 종료
					/*
					 * 여기서 break 하면 안됌! 
					 * 큐에 남은 다른 좌표들도 확인해야 하기 때문에 break가 아닌 continue 
					 */
					continue; 
				
				if(isRedHole&&!isBlueHole) // 빨간 구슬만 빠진 경우 이동횟수 반환후 종료
					return count;
				
				// 구슬이 빠지진 않았지만 두 구슬이 같은 곳으로 이동할 경우
				if(nrx==nbx&&nry==nby) {
					if(i==0) { // 위쪽으로 구슬을 굴린 경우
						if(cur_rx>cur_bx) // 이동전 위치에서 더 아랫쪽에 있던 구슬이 한칸 밑으로 내려온다.
							nrx-=dx[i]; // 위쪽 이동은 -1 연산이기 때문에 +1을 하기 위해서는 -연산 수행
						else
							nbx-=dx[i];
					}
					
					else if(i==1) { // 오른쪽으로 구슬을 굴린 경우
						if(cur_ry>cur_by) // 이동전 위치에서 좀 더 왼쪽에 있던 구슬이 한칸 왼쪽으로 이동한다.
							nby-=dy[i];
						else
							nry-=dy[i];
							
					}
					
					else if(i==2) { // 아래쪽으로 구슬을 굴린 경우
						if(cur_rx>cur_bx) // 이동전 위치에서 좀 더 위쪽에 있던 구슬이 한칸 위로 이동한다.
							nbx-=dx[i];
						else
							nrx-=dx[i];
					}
					
					else { // 왼쪽으로 구슬을 굴린 경우
						if(cur_ry>cur_by) // 이동전 위치에서 좀 더 오른쪽에 있던 구슬이 한칸 오른쪽으로 이동한다.
							nry-=dy[i]; // 오른쪽 이동은 -1 연산이기 때문에 +1을 하기 위해서는 -연산 수행
						else
							nby-=dy[i];
					}
				}
				
				// 이전에 방문하지 않았던 곳만 방문처리, 큐에 새로운 위치를 삽입한다.
				if(!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby]=true;
					q.offer(new Point_13460(nrx,nry,nbx,nby,count+1));
				}
			}
		}
		return -1;
	}
}
