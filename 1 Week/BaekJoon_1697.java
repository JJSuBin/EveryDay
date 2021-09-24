mport java.util.*;

public class BaekJoon_1697 {
	static int[] subin=new int[100001]; // 각 배열값은 수빈이가 해당 인덱스로 가기까지 소요된 시간이 저장된다. 
	static int n,k,time=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		
		if(n==k)
			System.out.println(0);
		else
			bfs(n);
	}
	
	static void bfs(int start) {
		Queue<Integer> q=new LinkedList<>();
		q.offer(start);
		subin[start]=0;
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			// 수빈이가 이동할 수 있는 한칸 전, 한칸 후, X2 탐색
			for(int i=0;i<3;i++) {
				int next;
				
				if(i==0)
					next=now+1; // 한칸 앞
				else if(i==1)
					next=now-1; // 한칸 전
				else
					next=now*2; // X2
				
				// 다음 위치가 동생의 위치라면 이전 시간의 +1 해준 값을 출력하고 종료
				if(next==k) {
					System.out.println(subin[now]+1);
					return;
				}
				
				/*
				 * 수빈이의 다음 이동 위치가 배열 범위내에 있고 이전에 가본적이 없는 곳이라면
				 * 배열 값을 갱신하고 next 위치로 재탐색하기 위해 큐에 삽입한다. 
				 */
				if(next>=0&&next<subin.length&&subin[next]==0) {
					subin[next]=subin[now]+1;
					q.offer(next);
				}
			}
		}
	}
}
