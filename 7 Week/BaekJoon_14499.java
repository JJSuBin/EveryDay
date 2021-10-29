import java.util.*;

public class BaekJoon_14499 {
	// 0:위, 1:동, 2:남, 3:서, 4:북, 5:아래
	static int[] dice=new int[6];
	static int n,m,x,y,k;
	static int[][] map;
	static int[] dx= {0,0,0,-1,1}; // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
	static int[] dy= {0,1,-1,0,0};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		x=sc.nextInt();
		y=sc.nextInt();
		k=sc.nextInt();
		
		map=new int[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		
		for(int i=0;i<k;i++) {
			int direction=sc.nextInt();
			
			bfs(direction);
			
			/*
			 * 배열 범위를 벗어나는 경우는 주사위 윗면의 수를 출력하면 안됌
			 * 따라서 이 부분에 출력문을 써주면 범위를 넘어가는 경우도 출력되기 때문에 
			 * 출력문은 아래 bfs 함수에서 써주어야 함
			 */
		}
		
	}
	
	static void bfs(int dir) {
		
		int nx=x+dx[dir];
		int ny=y+dy[dir];
		
		// 범위를 벗어나는 경우는 고려하지 않음
		if(nx<0||ny<0||nx>=n||ny>=m) 
			return;
		
		move_dice(dir); // 주사위 굴리기
		
		if(map[nx][ny]==0) // 이동한 칸에 씌여진 값이 0인 경우
			map[nx][ny]=dice[5];
		
		else { // 이동한 칸에 숫자가 씌여진 경우
			dice[5]=map[nx][ny];
			map[nx][ny]=0;
		}
		
		// 현재 위치 갱신
		x=nx;
		y=ny;
		
		// 범위를 초과하는 경우는 중간에 return 되기 때문에 출력되지 않음
		System.out.println(dice[0]); 
	}
	
	/*
	 * dir 방향으로 주사위를 굴리는 함수, 각 배열의 인덱스는 주사위의 어떤 위치를 의미하므로 
	 * 배열 값을 바꿔주는 형식으로 주사위를 굴린다. 
	 */
	static void move_dice(int dir) {
		int[] temp=dice.clone();
		
		// 0:위, 1:동, 2:남, 3:서, 4:북, 5:아래
		switch(dir) {
		case 1: // 동
			dice[1]=temp[0]; // 위가 동으로
			dice[5]=temp[1]; // 동이 아래로
			dice[3]=temp[5]; // 아래가 서로
			dice[0]=temp[3]; // 서가 위로
			break;
		case 2: // 서
			dice[3]=temp[0]; // 위가 서로
			dice[0]=temp[1]; // 동이 위로
			dice[1]=temp[5]; // 아래가 동으로
			dice[5]=temp[3]; // 어가 아래로
			break;
		case 3: // 북
			dice[2]=temp[0]; // 위가 남으로
			dice[0]=temp[4]; // 북이 위로
			dice[4]=temp[5]; // 아래가 북으로
			dice[5]=temp[2]; // 남이 아래로
			break;
		case 4: // 남
			dice[4]=temp[0]; // 위가 북으로
			dice[0]=temp[2]; // 남이 위로
			dice[5]=temp[4]; // 북이 아래로
			dice[2]=temp[5]; // 아래가 남으로
			break;
		}
	}
}
