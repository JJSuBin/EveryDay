import java.util.*;

public class BaekJoon_11053 {
	static int[] dp,num; // dp[i]에는 i번째의 수의 가장 긴 증가하는 부분수열이 저장된다. 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		dp=new int[n];
		num=new int[n];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		for(int i=0;i<n;i++) {
			dp[i]=1; // 초기값은 자신을 포함한 수열의 길이 1
			
			/*
			 * i번째 이전 값들을 탐색
			 * 증가하는 수열이 되기 위해서는 i번째 값 보다 작은 숫자이면서
			 * dp값 중 가장 큰 값에 +1 해준 값으로 갱신한다.
			 */
			for(int j=0;j<i;j++) {
				if(num[i]>num[j]&&dp[i]<=dp[j]) {
					dp[i]=dp[j]+1; // j번째의 증가하는 부분수열 + i번째 숫자
				}
			}
		}

		int max=0;
		for(int i=0;i<dp.length;i++)
			max=Math.max(max, dp[i]);
		
		System.out.println(max);
	}

}
