import java.util.*;

public class BaekJoon_1759 {
	static char[] string;
	static boolean[] visited;
	static int l,c;
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		l=sc.nextInt();
		c=sc.nextInt();
		
		string=new char[c];
		visited=new boolean[c];
		
		for(int i=0;i<c;i++) 
			string[i]=sc.next().charAt(0);
		
		Arrays.sort(string); // 알파벳이 증가하는 순서대로 암호를 만들기 위해 정렬
		dfs(0,0);
		
		System.out.println(sb);
	}
	static void dfs(int depth, int start) {
		if(depth==l) {
			int conc=0; // 자음 갯수
			int vow=0; // 모음 갯수
			
			String temp="";
			for(int i=0;i<c;i++) {
				if(visited[i]) {
					if(string[i]=='a'||string[i]=='e'||
							string[i]=='i'||string[i]=='o'||string[i]=='u')
						vow++; // 모음 갯수 count
					else 
						conc++; // 자음 갯수 count
					
					temp+=string[i];
				}
			}
			
			// 최소 한 개의 모음과 두 개의 자음으로 이루어져야 하는 조건을 만족했다면 추가
			if(vow>=1&&conc>=2)
				sb.append(temp).append('\n');
			
			return;
		}
		
		// 백트래킹을 사용하여 c개의 문자중 l개를 선택해서 만들 수 있는 모든 경우의 수 탐색
		for(int i=start;i<c;i++) {
			visited[i]=true;
			dfs(depth+1,i+1);
			visited[i]=false;
		}
	}
}
