import java.util.*;

public class BaekJoon_1676 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int count=0;
		
		// n! 결과는 n이 5일때 부터 끝자리에 0이 생기며 5 단위로 0이 추가된다. 
		while(n>=5) {
			count+=n/5;
			n/=5; 
		}
		System.out.println(count);
		
	}
}
