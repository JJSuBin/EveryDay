import java.util.*;

public class BaekJoon_1405 {
	static int n;
	static double answer=0;
	static boolean[][] visited=new boolean[30][30];
	static double[] percent=new double[4];
	static int[] dx= {0,0,1,-1}; // 동,서,남,북
	static int[] dy= {1,-1,0,0};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		// 소수점의 형태로 바꿔 저장
		for(int i=0;i<4;i++)
			percent[i]=sc.nextDouble()*0.01;
		
		/*
		 * n은 14보다 작은 작거나 같은 자연수이기 때문에 
		 * 30*30 배열을 사용하고, 그 중 중앙에서부터 탐색을 시작한다.(배열 범위 초과 문제때문) 
		 */
		visited[15][15]=true;
		dfs(15,15,0,1);
		
		System.out.println(answer);
	}
	static void dfs(int x, int y, int depth, double result) {
		// 이동횟수 n만큼 이동했다면 해당 경로가 나올 확률 result를 answer에 누적하고 재귀호출을 종료한다.  
		if(depth==n) {
			answer+=result;
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			/*
			 * n은 14보다 작거나 같은 자연수이기 때문에 (15,15)에서 시작하면 
			 * 아래와 같은 경우가 배열 범위를 초과한 경우와 이미 방문한 곳은 무시
			 */
			if(nx<=0||ny<=0||nx>=30||ny>=30||visited[nx][ny])
				continue;
			
			// 방문하지 않은 지점에서 백트래킹
			if(!visited[nx][ny]) {
				visited[nx][ny]=true;
				dfs(nx,ny,depth+1,result*percent[i]);
				visited[nx][ny]=false;
			}
		}
	}
}
