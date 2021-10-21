import java.util.Scanner;
/* 
 * 연산자 끼워넣기
 * 각 연산자의 개수를 입력받아 배열에 삽입한다
 * 이후 배열의 값이 0이 넘는다면 값을 줄이면서 재귀함수 호출 
 * 배열 값이 0이 된다면 다음 인덱스(연산자)로 넘어간다
 * 이 과정이 끝난다면 백트래킹을 위해 재귀호출을 종료하고 해당 연산자 개수를 늘려준다
 */
public class BeakJoon_14888 {
	public static int n;
	public static int[] operator=new int[4];
	public static int[] num;
	public static int max=Integer.MIN_VALUE;
	public static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		num=new int[n];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		for(int i=0;i<4;i++)
			operator[i]=sc.nextInt();
		
		/* 
		 * num의 첫 번쨰 원소가 매개변수로 주어졌기 때문에 
		 * depth은 1부터 시작하여 다음 숫자부터 계산하도록 한다.
		 */
		dfs(num[0],1);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int number,int depth) {
		// 모든 숫자를 사용하여 계산이 끝난 경우 최소, 최댓값 계산
		if(depth==n) {
			max=Math.max(max, number);
			min=Math.min(min, number);
			return;
		}
		
		/*
		 * 해당 for문은 어떤 연산을 가장 먼저 수행할 것인지 결정해주는 역할을 한다.
		 * 그래야 모든 연산의 경우를 계산할 수 있다. 
		 */
		for(int i=0;i<4;i++) {
			// 연산자의 개수가 1개 이상인 경우
			if(operator[i]>0) {
				operator[i]-=1;
				
				switch(i) {
				case 0: dfs(number+num[depth],depth+1); break;
				case 1: dfs(number-num[depth],depth+1); break;
				case 2: dfs(number*num[depth],depth+1); break;
				case 3: dfs(number/num[depth],depth+1); break;
				}
				// 백트래킹을 위해 감소했던 연산자의 값 올려주기
				operator[i]+=1;
			}
		}
	}
}
