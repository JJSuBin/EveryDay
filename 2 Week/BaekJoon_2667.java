import java.util.*;

class Point_2667 {
	int x,y;
	
	public Point_2667(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

/*
 * 단지 번호 붙이기 문제이지만 각 단지마다 번호를 붙일 필요 없다. 
 * 방문처리 배열을 사용해 이미 다른 단지에 포함된 지역이면 배열 값이 true로 변해있기 떄문
 * 즉, 배열 값이 1이면서 방문처리 배열의 값이 false인 집은 아직 단지번호가 붙지 않은 집!
 */
public class BaekJoon_2667 {
	static int[][] map;
	static ArrayList<Integer> result=new ArrayList<>();
	static int n,total=0; 
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static boolean[][] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		map=new int[n][n];
		visited=new boolean[n][n];
		
		// 단지 정보 입력받기
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<n;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				// 아직 단지의 번호가 붙여지지 않은 곳 탐색(방문하지 않았으면서 배열 값이 1인 곳)
				if(!visited[i][j]&&map[i][j]==1) {
					int answer=bfs(i,j); // 단지에 포함된 집의 개수 반환
					result.add(answer); // 단지별 집 개수 저장
					total+=answer; // 전체 집의 개수 누적
				}
			}
		}
		
		Collections.sort(result); // 정렬
		System.out.println(result.size()); // 단지 갯수 
		// 각 단지내 집의 수 출력
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
	}
	
	static int bfs(int x, int y) {
		Queue<Point_2667> q=new LinkedList<>();
		q.offer(new Point_2667(x,y));
		visited[x][y]=true; 
		int count=1; // 한 단지내 집의 개수를 count하는 변수, 시작 집을 포함하므로 1로 초기화
		
		while(!q.isEmpty()) {
			Point_2667 p=q.poll();
			x=p.x;
			y=p.y;
			
			// 상,하,좌,우 탐색
			for(int i=0;i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				// 배열 범위 벗어난 경우 제외
				if(nx>=n||ny>=n||nx<0||ny<0)
					continue;
				// 이미 단지 번호가 붙은 곳이거나 집이 없는 곳도 제외
				if(map[nx][ny]==0||visited[nx][ny])
					continue;
				
				// 아직 단지 번호가 붙지 않은 지역이면서 집이 있는 곳
				if(map[nx][ny]==1) {
					visited[nx][ny]=true; // 방문처리
					count+=1; // 집 개수 count
					q.offer(new Point_2667(nx,ny));
				}
			}
		}
		return count; // 한 단지내 포함된 집의 개수 return
	}
}
