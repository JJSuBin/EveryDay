import java.util.*;

public class BaekJoon_9663 {
	static int[] map; // 인덱스는 열, 값은 행
	static int n,result=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		map=new int[n];
		
		dfs(0);
		System.out.println(result);
	}
	static void dfs(int depth) {
		// n개의 퀸을 모두 배치했다면 경우의 수 증가
		if(depth==n) {
			result++;
			return;
		}
		
		// 체스판의 각 행에 퀸 배치
		for(int i=0;i<n;i++) {
			map[depth]=i; // i번째 행에 퀸 배치
			// 해당 위치에 퀸을 배치할 수 있는지 검사
			if(nQueen(depth))
				dfs(depth+1); // 다음 열로 넘어간다.
		}
	}
	
	static boolean nQueen(int col){
		// col 이전 행 검사
		for(int i=0;i<col;i++) {
			// 같은 행에 놓여있는 경우 배치 불가
			if(map[i]==map[col])
				return false;
			
			// 대각선에 놓여있는 경우 배치 불가 = 열의 차(col-i)와 행의 차(map[col]-map[i])가 같은 경우
			else if(Math.abs(col-i)==Math.abs(map[col]-map[i]))
				return false;
		}
		
		return true;
	}
}
