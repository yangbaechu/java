package splender;
import java.util.Scanner;

public class user {
	int red =0, green = 0, blue = 0, black = 0, white = 0;
	int red_card =0, green_card = 0, blue_card = 0, black_card = 0, white_card = 0, total_card = 0;
	int input1, input2, i, rank=0, number=0, user_n= 0, score = 0;
	card getcard;
	boolean retry = false;
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
		System.out.println();
		System.out.print("행동을 고르세요 1)자원 획득  2)카드 구매  3)게임 종료>>");
		
		input1 = scanner.nextInt();
		
		/* 자원을 얻을 경우 */
		if(input1 == 1) {
			for(i = 1; i<4; i++) {
				System.out.println(i + "번째 자원을 고르세요");
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
			retry = false;
			System.out.println("선택할 카드의 중요도를 입력하시오");
			rank = scanner.nextInt();
			System.out.println("선택할 카드의 번호를 입력하시오");
			number = scanner.nextInt();
			
			/* 사용자가 선택한 카드를 사용자의 덱으로 이동 시킴 */
			if(user_n == 1) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_1, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_1, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_1, number);
				}
				getcard = cardManager.user_1.get(total_card);
			}
				
			else if(user_n == 2) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_2, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_2, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_2, number);
				}
				getcard = cardManager.user_2.get(total_card);
			}
			
			/*카드 비용 지불*/
			red = pay(getcard.red, this.red, this.red_card, 0);
			green = pay(getcard.green, this.green, this.green_card, 1);
			blue = pay(getcard.blue, this.blue, this.blue_card, 2);
			black = pay(getcard.black, this.black, this.black_card, 3);
			white = pay(getcard.white, this.white, this.white_card, 4);
			
			for(i=0; i<5; i++) {
				if (GameManager.miss[i] == true) {
					retry = true;
				}
			}
			
			/*카드를 구매하기에 자원이 부족한지 확인 */
			if(!retry) {
				
				/* 얻은 카드의 효과 사용자에게 추가 */
				if (getcard.effect == 1) { red_card ++; }
				else if (getcard.effect == 1) { green_card ++; }
				else if (getcard.effect == 1) { blue_card ++; }
				else if (getcard.effect == 1) { black_card ++; }
				else { white_card ++; }
				
				/* 얻은 카드의 점수 사용자에게 추가 */
				if(getcard.score>0) {
					while(getcard.score!=0) {
						score++;
						getcard.score--;
						System.out.println("");
					}
				}
				
				total_card += 1;
				System.out.println(getcard.Effect+ " 효과의 카드를 구매하셨습니다.");
				System.out.println("턴이 종료되었습니다.");
				System.out.println();
			}
			else {
				/* 사용자가 가져왔던 카드를 반납 */
				if(user_n == 1) {
					if(rank == 1) {
						cm.moveuser2open(cardManager.dec_1, cardManager.user_1, cardManager.open_1, total_card, number);
					}
					else if(rank == 2) {
						cm.moveuser2open(cardManager.dec_2, cardManager.user_1, cardManager.open_2, total_card, number);
					}
					else if(rank == 3) {
						cm.moveuser2open(cardManager.dec_3, cardManager.user_1, cardManager.open_3, total_card, number);
					}
				}
					
				else if(user_n == 2) {
					if(rank == 1) {
						cm.moveuser2open(cardManager.dec_1, cardManager.user_2, cardManager.open_1, total_card, number);
					}
					else if(rank == 2) {
						cm.moveuser2open(cardManager.dec_2, cardManager.user_2, cardManager.open_2, total_card, number);
					}
					else if(rank == 3) {
						cm.moveuser2open(cardManager.dec_3, cardManager.user_2, cardManager.open_3, total_card, number);
					}
				}
				//System.out.println("자원이 부족합니다.다시 선택하세요");
			}
		}
		
		/* 게임을 종료하는 경우*/
		else if(input1 == 3) {
			return false;
		}
		return true;
	}
	
	/*얻은 카드의 가격을 지불하는 메소드 */
	public int pay(int price, int color, int color_c, int i) {
		if(price>0) {//지불할 가격이 남아 있을 동안 반복
			while(color_c > 0 && price > 0) {//해당 자원의 카드가 있을 경우
				color_c--;
				price--;
			}
			while(color > 0  && price > 0) {//해당 자원의 카드가 부족하고 자원이 있을 경우
				color--;
				price--;
			}
			if (price != 0) {// 해당 자원과 카드가 모두 없는 경우
				GameManager.miss[i] = true;
				System.out.print(i + "번 자원이 " + price + "만큼 부족합니다. ");
			}
			else {
				GameManager.miss[i] = false;
			}
		}
		else {//해당 자원이 필요 없는 경우
			GameManager.miss[i] = false;
		}
		return color;
	}
}
