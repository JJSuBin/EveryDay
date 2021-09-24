import java.util.*;

public class BaekJoon_1747 {
	static boolean[] Prime=new boolean[1000001];
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		// n이 1일 경우 예외처리!
		if(n==1) {
			System.out.println(2);
			System.exit(0);
		}
		
		// n보다 큰 수 중 소수이면서 팰린드롬을 만족하는 수 찾기
		for(int i=n;;i++) {
			if(isPrime(i)&&isPalind(i)) {
				System.out.println(i);
				System.exit(0);
			}
		}	
	}
	
	// n이 소수인지 판별하는 함수 -> 에라토스테네스의 채 알고리즘
	static boolean isPrime(int num) {
		for(int i=2;i<=Math.sqrt(num);i++)
			if(num%i==0)
				return false;
		
		return true;
	}
	
	// n이 팰린드롬 수 인지 판별하는 함수
	static boolean isPalind(int num) {
		String str=Integer.toString(num);
		int len=str.length();
		
		for(int i=0;i<=len/2;i++) {
			if(str.charAt(i)!=str.charAt(len-1-i))
				return false;
		}
		
		return true;
	}
}
