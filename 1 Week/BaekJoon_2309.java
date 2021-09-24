import java.util.*;

public class BaekJoon_2309 {
	static boolean[] visited=new boolean[9];
	static int[] dwarf=new int[9];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<9;i++)
			dwarf[i]=sc.nextInt();
		
		Arrays.sort(dwarf); // 키를 오름차순으로 출력하기 위해 정렬
		dfs(0,0);
	}
	
	static void dfs(int depth, int start) {
		if(depth==7) {
			int sum=0; 
			StringBuilder sb=new StringBuilder();
			
			// 9명의 난쟁이 중 방문처리 된 7명의 난쟁이의 키의 합을 구한다.
			for(int i=0;i<9;i++) {
				if(visited[i]) {
					sb.append(dwarf[i]).append("\n");
					sum+=dwarf[i];
				}
			}
			
			/*
			 * 키의 합이 100이 된 경우가 나올 경우 
			 * 저장한 난쟁이들의 키를 출력한 후 시스템을 종료한다.
			 */
			if(sum==100) {
				System.out.println(sb.toString());
				System.exit(0);
			}
		}
		
		// 백트래킹을 사용하여 9명의 난쟁이 중 7명 선택
		for(int i=start;i<9;i++) {
			visited[i]=true;
			dfs(depth+1,i+1);
			visited[i]=false;
		}
	}
}
