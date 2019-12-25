package splender;
import java.util.Scanner;
import java.util.Vector;

public class User {
	String Color[] = {"빨강","초록","파랑","검정", "하양"};
	int color[] = {0, 0, 0, 0, 0};// 빨강, 초록, 파랑, 검정, 하양 순으로 사용자 객체의 자원 숫자를 저장
	int card[] = {0, 0, 0, 0, 0};//같은 순서로 사용자 객체의 카드 숫자를 저장
	int option, rank=0, number=0, user_n= 0, score = 0, total_card = 0;
	int color_input, color_input_1, color_input_2, color_input_3; // 사용자가 선택한 자원
	card getcard;
	noble_card getnoble_card;
	cardManager cm = new cardManager();
	Scanner scanner = new Scanner(System.in);
	public User(int n) { user_n = n; }
	public boolean turn() {
		
		/*현재 오픈되어 있는 카드, 자신의 자원과 카드를 보여줌 */
		cm.open();
		show();
		System.out.println();
		System.out.print("행동을 고르세요 1)자원 획득  2)카드 구매  3)게임 종료>>");
		
		option = scanner.nextInt();
		
		/* 자원을 얻을 경우 */
		if(option == 1) {
			System.out.println("같은 자원 2개 혹은 서로 다른 자원 3개를 가져갈 수 있습니다.");
			System.out.print("첫번째 자원을 고르세요>>");
			color_input_1 = get_color();
			System.out.print("두번째 자원을 고르세요");
			color_input_2 = get_color();
			if(color_input_1 == color_input_2) {//같은 종류의 자원 2개 얻을 경우
				System.out.println("턴이 종료되었습니다.");
			}
			else {//각각 다른 자원을 3개 얻을 경우
				color_input_3 = get_color();
				if(color_input_1 == color_input_3 || color_input_2 == color_input_3) {
					do {
						System.out.println("조건에 맞지 않는 행동압니다. 같은 자원 2개 혹은 서로 다른 자원 3개를 가져갈 수 있습니다.");
						return_color(color_input_3);
						System.out.println("세번째 자원을 고르세요");
						color_input_3 = get_color();
					}while(color_input_1 == color_input_3 || color_input_2 == color_input_3);
				}
				System.out.println("턴이 종료되었습니다.");
			}
			show();
			System.out.println();
			return true;
		}
		
		/* 카드를 얻는 선택을 할 경우 */
		else if(option == 2) {
			System.out.print("선택할 카드의 중요도를 입력하시오>>");
			rank = scanner.nextInt();
			System.out.print("선택할 카드의 번호를 입력하시오>>");
			number = scanner.nextInt();
			
			/*사용자가 선택한 카드의 정보를 읽어옴 */
			getcard = ((Vector<card>)cardManager.open.get(rank-1)).get(number-1);

			/*카드를 구매하기에 자원이 부족한지 확인 */
			if(getcard.need[0] <= (this.card[0]+this.color[0]) && getcard.need[1] <= (this.card[1]+this.color[1])  
					&& getcard.need[2] <= (this.card[2]+this.color[2]) && getcard.need[3] <= (this.card[3]+this.color[3])
					&& getcard.need[4] <= (this.card[4]+this.color[4])) {
				
				/* 사용자가 선택한 카드를 사용자의 덱으로 이동 시킴 */
				cm.move2user((Vector<card>)cardManager.hide.get(rank-1), (Vector<card>)
				cardManager.open.get(rank-1), (Vector<card>)cardManager.user.get(user_n), number);

				/*카드 비용 지불*/
				for(int i=0; i<5; i++) {
					color[i] = pay(getcard.need[i], this.color[i], this.card[i], i);
				}
				
				/* 얻은 카드의 효과 사용자에게 추가 */
				card[getcard.effect-1] += 1;
				
				/* 얻은 카드의 점수 사용자에게 추가 */
				if(getcard.score>0) {
					while(getcard.score!=0) {
						score++;
						getcard.score--;
						System.out.println("");
					}
				}
				
				total_card += 1;// 사용자 객체의 카드 수를 증가시킴
				System.out.println(getcard.Effect[getcard.effect-1]+ " 효과의 카드를 구매하셨습니다.");
				for(int i=0; i<3; i++) {
					check_noble(i);
				}
				System.out.println("턴이 종료되었습니다.");
				System.out.println();
			}
			else {
				GameManager.retry = true;//턴이 다시 진행되도록 변수 설정
				System.out.println("해당 카드를 구매하기 위한 자원이 부족합니다. 다시  선택하세요");
				System.out.println();
			}
		}
		
		/* 게임을 종료하는 경우*/
		else if(option == 3) {
			return false;
		}
		return true;
	}
	
	/*얻은 카드의 가격을 지불하는 메소드 */
	public int pay(int price, int color, int color_c, int i) {
		if(price>0) {//지불할 가격이 남아 있을 동안 반복
			while(color_c > 0 && price > 0) {//해당 자원의 카드가 있을 경우
				color_c--;	price--;
			}
			while(color > 0  && price > 0) {//해당 자원의 카드가 부족하고 자원이 있을 경우
				color--;	price--;
			}
			if (price != 0) {// 해당 자원과 카드가 모두 없는 경우
				System.out.print((i+1) + "번 자원이 " + price + "만큼 부족합니다. ");
			}
		}
		return color;
	}
	
	/* 자원 카드를 얻는 메소드 */
	public int get_color() {
		System.out.println("빨강: 1, 초록: 2, 파랑: 3, 검정: 4, 하양: 5");
		color_input = scanner.nextInt();
		color[color_input-1] += 1;
		System.out.println(Color[color_input-1] + "을 획득했습니다"); 
		System.out.println();
		return color_input;
	}

	/* 자원 카드를 되돌려 주는 메소드 */
	public int return_color(int color_input) {
		color[color_input-1] -= 1;
		System.out.println("자원을 반환합니다.");
		return color_input;
	}
	
	/* 사용자의 현재 정보를 출력해주는 메소드 */
	public void show() {
		System.out.println("현재 자원의 개수는 빨강: " + color[0] + ", "+ "초록: " + color[1] + ", "+"파랑: " + color[2] 
				+ ", "+"검정: " + color[3] + ", "+"하양: " + color[4] + " 입니다.");
		System.out.println("현재 카드의 개수는 빨강: " + card[0] + ", "+ "초록: " + card[1] + ", "+"파랑: " 
				+ card[2] + ", "+"검정: " + card[3] + ", "+"하양: " + card[4] + " 입니다.");
	}
	
	/* 귀족카드를 얻을 수 있는지 확인하는 메소드 */
	public void check_noble(int i) {
		getnoble_card = cardManager.noble_open.get(i);
		if(getnoble_card.red <= this.card[0] && getnoble_card.green <= this.card[1]  
			&& getnoble_card.blue <= this.card[2] && getnoble_card.black <= this.card[3]
			&& getnoble_card.white <= this.card[4]) {
			this.score += 3;
			cm.move2user_noble(cardManager.noble_hide, cardManager.noble_open, 
					(Vector<noble_card>)cardManager.user_noble.get(user_n), i);
			System.out.println(getnoble_card.name + "카드를 획득했습니다.");
			System.out.println("현재 점수는 "+ score + "점 입니다.");
		}
		else {}
	}
}