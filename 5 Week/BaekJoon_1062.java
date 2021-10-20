import java.util.*;

public class BaekJoon_1062 {
	static boolean[] visited=new boolean[26];
	static String[] word;
	static int n,k,max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

		n=sc.nextInt();
		k=sc.nextInt();
		
		word=new String[n];
		// 입력받은 단어에서 anta와 tica는 제외하고 저장
		for(int i=0;i<n;i++) {
			String str=sc.next();
			str=str.replace("anta","");
			str=str.replace("tica","");
			word[i]=str;
		}
		
		/*
		 * 기본적으로 배워야만 하는 a,c,i,n,t의 개수가 5개이므로 
		 * 배울수 있는 단어의 개수가 5개 미만이라면 읽을 수 있는 단어의 개수는 0개이다
		 */
		if(k<5) {
			System.out.println(0);
			return;
		}
		// 배울 수 있는 단어의 개수가 알파벳의 개수 26개라면 모든 단어를 읽을 수 있다.
		else if(k==26) {
			System.out.println(n);
			return;
		}
		
		visited['a'-'a']=true;
		visited['c'-'a']=true;
		visited['i'-'a']=true;
		visited['n'-'a']=true;
		visited['t'-'a']=true;
		
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int depth, int start) {
		// 배워야하는 5개의 알파벳을 제외하고 배울 알파벳을 선택했다면
		if(depth==k-5) {
			// 읽을 수 있는 단어의 개수 갱신
			max=Math.max(max,check());
			return;
		}
		
		// 26개의 알파벳을 중복없이 선택
		for(int i=start;i<26;i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(depth+1,i+1);
				visited[i]=false;
			}
		}
	}
	
	// 현재 선택한 알파벳으로 읽을 수 있는 단어의 개수 count
	static int check() {
		int count=0;
		
		for(int i=0;i<n;i++) {
			boolean read=true;
			for(int j=0;j<word[i].length();j++) {
				// 배우지 않은 알파벳이 포함된 단어라면
				if(!visited[word[i].charAt(j)-'a']) {
					read=false; // 읽을 수 없음 처리
					break;
				}
			}
			if(read) count++;
		}
		return count;
	}
}
