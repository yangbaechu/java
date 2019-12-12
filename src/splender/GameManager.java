package splender;

import java.util.Scanner;
import java.util.StringTokenizer;


public class GameManager {
	public static void main(String arvg[]) {
		Scanner scanner = new Scanner(System.in);
		String query = "1 1 3 40000\r\n" + 
				"1 0 3 11011\r\n" + 
				"1 0 3 13100\r\n" + 
				"1 0 3 00021";
		StringTokenizer st = new StringTokenizer(query, "\r\n ");
		while(st.hasMoreTokens()) {
			String token = st.nextToken(); // 토큰 얻기
			System.out.println(token); // 토큰 출력
		}
	}	
}

