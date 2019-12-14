package splender;

import java.util.Arrays;

public class GameManager {
	static boolean miss[] = {false,false,false,false,false};//사용자가 구매에 사용할 자원이 충분한지 확인한 결과를 저장
	static boolean retry = false;
	public static void main(String arvg[]) {
		user user1 = new user(1);
		user user2 = new user(2);
		Arrays.fill(miss, true);
		int i;
		boolean r1=true, r2=true, retry = false;
		System.out.println("스플렌더에 오신 것을 환영합니다!");
		System.out.println("스플렌더는 각각의 자원을 이용해서 카드를 구매해 점수를 얻어 승리하는 게임입니다.");
		System.out.println("카드는 해당 카드 제공하는 효과를 통해 다른 카드의 구매비용을 줄여줍니다.");
		System.out.println();
		
		while(r1&&r2) {
			System.out.println("사용자 1의 턴입니다.");
			/* 사용자 1의 턴 진행 */
			do {
				retry = false;
				r1 = user1.turn();
				for(i=0; i<5; i++) {
					if (miss[i] == true) {//필요한 자원중 한 종류라도 사용자가 없다면 턴을 다시 진행
						retry = true;
					}
				}
			}while(retry);
			if(r1 == false) { System.out.println("사용자 2의 턴까지 마치고 게임을 종료합니다."); }
			
			/*사용자 2의 턴 진행*/
			System.out.println("사용자 2의 턴입니다.");
			do {
				retry = false;
				r2 = user2.turn();
				for(i=0; i<5; i++) {
					if (miss[i] == true) {
						retry = true;
					}
				}
			}while(retry);
			if(r2 == false) { System.out.println("게임을 종료합니다."); }
			
			/* 승리 조건 달성시 */
			if(user1.score >= 15) {
				System.out.println("축하합니다!" + user1.score + "점으로  사용자 1의 승리입니다.");
				break;
			}
			else if(user2.score >= 15) {
				System.out.println("축하합니다!" + user2.score + "점으로 사용자 2의 승리입니다.");
				break;
			}
		}
	}
}



