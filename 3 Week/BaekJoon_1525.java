import java.util.*;

public class BaekJoon_1525 {
	/*
	 * String : 2차원 배열의 퍼즐을 한 줄의 문자열로 표현한 값
	 * Integer : 해당 퍼즐의 모양을 만드는데 움직인 횟수
	 */
	static Map<String,Integer> map=new HashMap<>(); // 해당 자료구조를 사용하는 것이 핵심!
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String str="";
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int temp=sc.nextInt();
				
				// 비어있는 곳은 9로 대체
				if(temp==0)
					temp=9;
				
				str+=temp;
			}
		}
		
		map.put(str,0);
		bfs(str);
		
		// map에 옳바른 배치인 "123456789"가 있다면 이동횟수 출력
		if(map.containsKey("123456789"))
			System.out.println(map.get("123456789"));
		else
			System.out.println(-1);
	}
	
	static void bfs(String start) {
		Queue<String> q=new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			String str=q.poll();
			int nine_index=str.indexOf("9"); // 비어있는 곳(9가 있는 자리) 위치
			
			// 9의 위치를 3*3 크기의 2차원 배열 위치로 바꾼 값
			int x=nine_index/3;
			int y=nine_index%3;
			
			// 상,하,좌,우로 퍼즐 이동
			for(int d=0;d<4;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				
				// 퍼즐 틀을 벗어나는 경우 무시
				if(nx<0||ny<0||nx>=3||ny>=3) continue;
				
				// (nx,ny)에 있던 숫자와 9가 있는 위치 swap
				int move=nx*3+ny; // 다시 1차원으로 인덱스 변경
				StringBuilder next_str=new StringBuilder(str);
				char temp=next_str.charAt(move);
				next_str.setCharAt(move,'9');
				next_str.setCharAt(nine_index,temp);
				
				// 새로운 퍼즐 배열이 이전에 나온적 없던 것이라면
				if(!map.containsKey(next_str.toString())) {
					map.put(next_str.toString(),map.get(str)+1); // 이동횟수 +1 늘리고 저장
					q.offer(next_str.toString());
				}
			}
		}
	}
}
