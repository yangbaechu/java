package splender;
import java.util.Scanner;

public class user {
	int red =0, green = 0, blue = 0, black = 0, white = 0;
	int red_card =0, green_card = 0, blue_card = 0, black_card = 0, white_card = 0, total_card = 0;
	int input1, input2, i, rank=0, number=0, user_n= 0, score;
	card getcard;
	cardManager cm = new cardManager();
	Scanner scanner = new Scanner(System.in);
	public user(int n) { user_n = n; }
	public boolean turn() {
		
		/*현재 오픈되어 있는 카드, 자신의 자원과 카드를 보여줌 */
		cm.open();
		System.out.println("현재 자원의 개수는 빨강: " + red + ", "+
		"초록: " + green + ", "+"파랑: " + blue + ", "+"검정: " + 
				black + ", "+"하양: " + white + " 입니다.");
		System.out.println("현재 카드의 개수는 빨강: " + red_card + ", "+
				"초록: " + green_card + ", "+"파랑: " + blue_card + ", "+"검정: " + 
						black_card + ", "+"하양: " + white_card + " 입니다.");
		System.out.println("행동을 고르시오 1)자원 획득  2)카드 구매  3) 게임 종료");
		System.out.println();
		
		input1 = scanner.nextInt();
		
		/* 자원을 얻을 경우 */
		if(input1 == 1) {
			for(i = 1; i<4; i++) {
				System.out.println(i + "번째 자원을 고르시오");
				System.out.println("빨강: 1, 초록: 2, 파랑: 3, 검정: 4, 하양: 5");
				input2 = scanner.nextInt();
				if(input2 == 1) {
					System.out.println("빨강을 획득했습니다"); 
					red++;
				}
				else if(input2 == 2) {
					System.out.println("초록을 획득했습니다"); 
					green++;
				}
				else if(input2 == 3) {
					System.out.println("파랑을 획득했습니다"); 
					blue++;
				}
				else if(input2 == 4) {
					System.out.println("검정을 획득했습니다"); 
					black++;
				}
				else if(input2 == 5) {
					System.out.println("하양을 획득했습니다"); 
					white++;
				}
				System.out.println();
			}
			System.out.println("턴이 종료되었습니다.");
			System.out.println("현재 자원의 개수는 빨강: " + red + ", "+
			"초록: " + green + ", "+"파랑: " + blue + ", "+"검정: " + 
					black + ", "+"하양: " + white + " 입니다.");
			System.out.println();
			return true;
		}
		
		/* 카드를 얻는 선택을 할 경우 */
		else if(input1 == 2) {
			System.out.println("선택할 카드의 중요도를 입력하시오");
			rank = scanner.nextInt();
			System.out.println("선택할 카드의 번호를 입력하시오");
			number = scanner.nextInt();
			
			/* 사용자가 선택한 카드를 사용자의 덱으로 이동 시킴 */
			if(user_n == 1) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_1, total_card);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_1, total_card);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_1, total_card);
				}
				getcard = cardManager.user_1.get(total_card);
			}
				
			else if(user_n == 2) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_2, total_card);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_2, total_card);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_2, total_card);
				}
				getcard = cardManager.user_2.get(total_card);
			}
			
			/* 얻은 카드의 효과 사용자에게 추가 */
			if (getcard.effect == 1) { red_card ++; }
			else if (getcard.effect == 1) { green_card ++; }
			else if (getcard.effect == 1) { blue_card ++; }
			else if (getcard.effect == 1) { black_card ++; }
			else { white_card ++; }
			
			/* 얻은 카드의 점수 사용자에게 추가 */
			while(getcard.score==0) {
				score++;
				getcard.score--;
			}
			
			pay();//카드 비용 지불
			
			total_card += 1;
			System.out.println("턴이 종료되었습니다.");
			System.out.println();
			return true;
		}
		
		/* 게임을 종료하는 경우*/
		else if(input1 == 3) {
			System.out.println("게임을 종료합니다...");
			return false;
		}
		return true;
	}
	
	/*얻은 카드의 가격을 지불하는 메소드 */
	public void pay() {
		int red_pay, green_pay, blue_pay, black_pay, white_pay; // 카드의 가격 
		int red_card_pay = red_card, green_card_pay = green_card, blue_card_pay = blue_card,
				black_card_pay = black_card, white_card_pay = white_card;//지불에 사용할 카드 개수
		red_pay = getcard.red;		green_pay = getcard.green;		blue_pay = getcard.blue;
		black_pay = getcard.black;	white_pay = getcard.white;
		
		/* 빨강 자원 비용 지불 */
		while(red_pay>0) {
			if(red_card_pay>0) {
				red_card_pay--;
				red_pay--;
			}
			else if(red>0) {
				red--;
				red_pay--;
			}
			else {
				System.out.println("필요한 자원이 부족합니다.");
				break;
			}
		}
		
		/* 초록 자원 비용 지불 */
		while(green_pay>0) {
			if(green_card_pay>0) {
				green_card_pay--;
				green_pay--;
			}
			else if(green>0) {
				green--;
				green_pay--;
			}
			else {
				System.out.println("필요한 자원이 부족합니다.");
				break;
			}
		}
		
		/* 파랑 자원 비용 지불 */
		while(blue_pay>0) {
			if(blue_card_pay>0) {
				blue_card_pay--;
				blue_pay--;
			}
			else if(blue>0) {
				blue--;
				blue_pay--;
			}
			else {
				System.out.println("필요한 자원이 부족합니다.");
				break;
			}
		}
		
		/* 검정 자원 비용 지불 */
		while(black_pay>0) {
			if(black_card_pay>0) {
				black_card_pay--;
				black_pay--;
			}
			else if(black>0) {
				black--;
				black_pay--;
			}
			else {
				System.out.println("필요한 자원이 부족합니다.");
				break;
			}
		}
		
		/*하양 자원 비용 지불 */
		while(white_pay>0) {
			if(white_card_pay>0) {
				white_card_pay--;
				white_pay--;
			}
			else if(white>0) {
				white--;
				white_pay--;
			}
			else {
				System.out.println("필요한 자원이 부족합니다.");
				break;
			}
		}
	}
}
