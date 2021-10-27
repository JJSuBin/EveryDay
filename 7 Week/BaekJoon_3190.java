import java.util.*;

class Snake {
	int x,y;
	
	public Snake(int x, int y) {
		this.x=x;
		this.y=y;
	}
}
	
public class BaekJoon_3190 {
	static int n,k,l;
	static int[][] map;
	static int[][] move;
	static int[] dx= {0,1,0,-1}; // 동,남,서,북 순
	static int[] dy= {1,0,-1,0};
	// 현재 뱀이 바라보고 있는 방향은 동쪽이기 때문에 dx,dy 인덱스에서 동쪽인 0으로 초기화
	static int nowdir=0; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		
		map=new int[n+1][n+1]; // 인덱스는 1~n
		for(int i=0;i<k;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			
			map[x][y]=1; // 사과가 있는 자리는 1
		}
		
		l=sc.nextInt(); // 뱀의 이동 횟수
		move=new int[l][2];
		for(int i=0;i<l;i++) {
			move[i][0]=sc.nextInt(); // 이동 횟수
			char temp=sc.next().charAt(0); // 다음 이동 방향
			
			// 왼쪽이면 -1, 오른쪽이면 1을 저장
			move[i][1]=(temp=='L')?-1:1;
		}
		
		System.out.println(Dummy());
	}
	
	static void nextdir(int dir) {
		if(dir==-1) // 왼쪽으로 회전
			nowdir=(nowdir==0)?3:nowdir-1;
		else // 오른쪽으로 회전
			nowdir=(nowdir+1)%4;
	}
	
	static int Dummy() {
		int x=1, y=1; // 뱀의 초기 위치
		int time=0; // 총 이동 시간 
		int turn=0; // 입력받은 l개의 이동횟수 중 수행중인 단계(수행한 방향 변환 횟수)
		
		/*
		 * 뱀의 위치가 들어있는 큐 -> 늘어나는 몸통이 차례로 들어가기 때문에
		 * 큐의 맨 앞에는 맨 처음에 삽입한 꼬리의 위치가 들어있다.
		 */
		Queue<Snake> q=new LinkedList<>();
		q.offer(new Snake(x,y)); // 처음 위치 삽입
		
		while(true) {
			time++; // 시간 증가
			int nx=x+dx[nowdir]; // 바라보는 방향으로 이동
			int ny=y+dy[nowdir];
			
			if(nx<=0||ny<=0|nx>n||ny>n) // 보드의 범위를 넘어가면 종료
				break;
			if(map[nx][ny]==2) // 이동한 곳에 뱀이 있다면 종료
				break;
			
			// 이동한 곳에 사과가 있다면
			if(map[nx][ny]==1) {
				map[nx][ny]=2; // 이동한 곳에 뱀 표시
				q.offer(new Snake(nx,ny));
			}
			// 이동한 곳에 사과가 없다면
			else {
				map[nx][ny]=2;
				q.offer(new Snake(nx,ny));
				Snake tail=q.poll(); // 꼬리 삭제
				map[tail.x][tail.y]=0;
			}
			
			// 현재 위치 갱신
			x=nx;
			y=ny;
			
			// 아직 입력받은 방향 변환 횟수 l를 채우지 못했고
			if(turn<l) {
				// 회전해야 하는 시간을 채웠다면 
				if(time==move[turn][0]) {
					nextdir(move[turn][1]); // move[turn][1] 방향으로 회전
					turn++; // 수행한 방향 변환 횟수 증가
				}
			}
		}
		return time;
	}
}
