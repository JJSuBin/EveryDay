import java.util.*;

public class BaekJoon_5014 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int f=sc.nextInt(); // f층으로 이루어진 건물
		int s=sc.nextInt(); // 강호의 현재 위치
		int g=sc.nextInt(); // 스타트링크가 있는 곳
		int u=sc.nextInt(); // u층 위로 갈 수 있음
		int d=sc.nextInt(); // d층 아래로 갈 수 있음
		
		// BFS 알고리즘 사용
		boolean check=false; // g 층에 도달했는지 체크하는 변수
		int[] visited=new int[f+1]; // 각 층에 도달하기까지의 횟수 저장 & 방문여부 체크 배열
		Queue<Integer> q=new LinkedList<>();
		q.add(s); 
		visited[s]=1; // 시작하는 층은 1로 방문처리 -> 마지막 값에서 1 빼주기
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			// g 층에 도달했다면
			if(now==g) {
				check=true; // check 변수 변경
				break;
			}
			
			// u층 올라간 층이 건물 층수 내에 있으며 아직 방문하지 않았다면
			if(now+u<=f&&visited[now+u]==0) {
				visited[now+u]=visited[now]+1; // 버튼 횟수 저장
				q.offer(now+u);
			}
			
			// d층 내려간 층이 건물 층수 내에 있으며 아직 방문하지 않았다면
			if(now-d>0&&visited[now-d]==0) {
				visited[now-d]=visited[now]+1; // 버튼 횟수 저장
				q.offer(now-d);
			}
		}
		
		if(check) // g 층에 도달한 경우
			System.out.println(visited[g]-1);
		else // 도달하지 못한 경우
			System.out.println("use the stairs");
	}

}
