import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  17406. 배열 돌리기 4
 *  https://www.acmicpc.net/problem/17406
 */
public class Main {
	static int dir[][]= {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, K, ans, map[][], tmp[][],op[][],sel[];
	static boolean check[];
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tmp = new int[N][M];
		op = new int[K][3];
		sel=new int [K];
		check = new boolean[K];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				op[i][j] = Integer.parseInt(st.nextToken());
		}
		ans = Integer.MAX_VALUE;
		perm(0);
		System.out.println(ans);
	}

	static void perm(int idx) {
		if(idx==K) {
			for(int i=0;i<N;i++)
				tmp[i]=map[i].clone();
			for(int i=0;i<K;i++)
				rotate(sel[i]);
			calculate();
			return;
		}
		for(int i=0;i<K;i++) {
			if(!check[i]) {
				sel[idx]=i;
				check[i]=true;
				perm(idx+1);
				check[i]=false;
			}
		}
	}
	static void rotate(int idx) {
		int r = op[idx][0]-1;
		int c = op[idx][1]-1;
		int s = op[idx][2];
		
		for(int i =1;i<=s;i++) {
			int x=r-i;
			int y=c-i;
			int d = 0;
			int v = tmp[x][y];
			while(true) {
				int nx = x+dir[d][0];
				int ny = y+dir[d][1];
				if(nx>r+i||ny>c+i||nx<r-i||ny<c-i) {
					d++;
					nx=x+dir[d][0];
					ny=y+dir[d][1];
				}
				tmp[x][y]=tmp[nx][ny];
				x=nx;
				y=ny;
				if(y==c-i+1&&x==r-i)
					break;
			}			
			tmp[x][y]=v;
		}
	}
	static void calculate() {
		int cnt, ret = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			cnt = 0;
			for (int j = 0; j < M; j++)
				cnt += tmp[i][j];
			ret = Math.min(ret, cnt);
		}
		ans=Math.min(ret,ans);
	}
}