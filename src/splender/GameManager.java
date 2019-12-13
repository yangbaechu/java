package splender;

public class GameManager {
	public static void main(String arvg[]) {

		user user1 = new user(1);
		user user2 = new user(2);
		boolean r1=true, r2=true;
		System.out.println("스플렌더에 오신 것을 환영합니다!");
		System.out.println("스플렌더는 각각의 자원을 이용해서 카드를 구매해 점수를 얻어 승리하는 게임입니다.");
		System.out.println("카드는 해당 카드 제공하는 효과를 통해 다른 카드의 구매비용을 줄여줍니다.");
		System.out.println();
		while(r1&&r2) {
			System.out.println("사용자 1의 턴입니다.");
			r1 = user1.turn();
			System.out.println("사용자 2의 턴입니다.");
			r2 = user2.turn();
			
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


