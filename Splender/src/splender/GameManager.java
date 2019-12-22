package splender;

import java.util.Arrays;

public class GameManager {
	static boolean miss[] = {false,false,false,false,false};//사용자가 구매에 사용할 자원이 충분한지 확인한 결과를 저장
	static boolean retry = false;
	
	public static void main(String arvg[]) {
		User user1 = new User(1);
		User user2 = new User(2);
		Arrays.fill(miss, true);
		int i;
		boolean r1=true, r2=true, retry = false;
		System.out.println("스플렌더에 오신 것을 환영합니다!");
		System.out.println("스플렌더는 각각의 자원을 이용해서 카드를 구매해 점수를 얻어 승리하는 게임입니다.");
		System.out.println("카드는 해당 카드 제공하는 효과를 통해 다른 카드의 구매비용을 줄여줍니다.");
		System.out.println("자원은 서로 다른 자원 3개 또는 같은 자원 2개를 가져올 수 있습니다.");
		System.out.println();
		
		while(r1&&r2) {

			r1 = check_retry(user1, 1);
			if(r1 == false) { System.out.println("사용자 2의 턴까지 마치고 게임을 종료합니다."); }
			
			r2 = check_retry(user2, 2);
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
	
	/* 사용자의 턴을 진행하고 턴이 정상적으로 완료 될때까지 턴을 진행하는 메소드 */
	public static boolean check_retry(User user, int num) {
		boolean r1;
		System.out.println("사용자 " + num + "의 턴입니다.");
		do {
			retry = false;
			for(int i=0;i<5;i++) {//매 턴이 진행되기 전에  변수 초기화
				miss[i] = false;
			}
			r1 = user.turn();
			for(int i=0; i<5; i++) {
				if (miss[i] == true) {//필요한 자원중 한 종류라도 사용자가 없다면 턴을 다시 진행
					retry = true;
				}
			}
		}while(retry);
		return r1;
	}
}